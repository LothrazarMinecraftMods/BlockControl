package com.lothrazar.samscontrolblocks.proxy;
     
import com.lothrazar.samscontrolblocks.UtilBlockTransform;
import com.lothrazar.samscontrolblocks.UtilPistonSpell;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>
{
	private byte keyPressed;
	private BlockPos pos;
	private String csv;
	public static final int ID = 0;
	public MessageKeyPressed()
	{ 
	}
	
	public MessageKeyPressed(int keyCode,BlockPos pm)
	{ 
		this.keyPressed = (byte)keyCode;
		pos = pm;
		csv = keyCode +","+posToCSV(pos);
	}
	private String posToCSV(BlockPos pos)
	{
		return pos.getX()+","+pos.getY()+","+pos.getZ();
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		//http://www.minecraftforge.net/forum/index.php?topic=20135.0

		csv = ByteBufUtils.readUTF8String(buf); 
        
        String [] spl = csv.split(",");
        keyPressed = (byte) Integer.parseInt(spl[0]);
        pos = new BlockPos(Integer.parseInt(spl[1]),Integer.parseInt(spl[2]),Integer.parseInt(spl[3]));

	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
        ByteBufUtils.writeUTF8String(buf, csv);
	}
	
	@Override
	public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 

		if( message.keyPressed == ClientProxy.keyPush.getKeyCode() && message.pos != null)
	 	{ 
			UtilPistonSpell.moveBlockTo(player.worldObj, player, message.pos, message.pos.offset(player.getHorizontalFacing()));
	 	}
		else if( message.keyPressed == ClientProxy.keyPull.getKeyCode()&& message.pos != null)
	 	{  
			UtilPistonSpell.moveBlockTo(player.worldObj, player, message.pos, message.pos.offset(player.getHorizontalFacing().getOpposite()));
	 	}
		else if( message.keyPressed == ClientProxy.keyTransform.getKeyCode()&& message.pos != null)
	 	{ 
			UtilBlockTransform.transformBlock(player, player.worldObj,  message.pos);
	 	} 
		 
		return null;
	}
}
 
