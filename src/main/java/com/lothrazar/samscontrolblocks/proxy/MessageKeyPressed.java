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
		//TODO: convert  pos to/from bytes
		//http://www.minecraftforge.net/forum/index.php?topic=20135.0
		//this.keyPressed = buf.readByte();
		
		 
		 csv = ByteBufUtils.readUTF8String(buf); 
        System.out.println("toBytes   "+csv);
        
        String [] spl = csv.split(",");
        keyPressed = (byte) Integer.parseInt(spl[0]);
        pos = new BlockPos(Integer.parseInt(spl[1]),Integer.parseInt(spl[2]),Integer.parseInt(spl[3]));

		 //ByteBufUtils.readItemStack(from)
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
 
/*16:45:35] [Netty Server IO #2/ERROR] [FML]: There was a critical exception handling a packet on channel samscontrolblocks
java.lang.NoClassDefFoundError: net/minecraft/client/Minecraft
	at com.lothrazar.samscontrolblocks.proxy.MessageKeyPressed.onMessage(MessageKeyPressed.java:47) ~[MessageKeyPressed.class:?]
	at com.lothrazar.samscontrolblocks.proxy.MessageKeyPressed.onMessage(MessageKeyPressed.java:16) ~[MessageKeyPressed.class:?]
	at net.minecraftforge.fml.common.network.simpleimpl.SimpleChannelHandlerWrapper.channelRead0(SimpleChannelHandlerWrapper.java:37) ~[SimpleChannelHandlerWrapper.class:?]
	*/
		if( message.keyPressed == ClientProxy.keyPush.getKeyCode())
	 	{ 
			if(message.pos == null)
			{
				System.out.println("keyPressed: pos null, bad packet "+message.csv);
				return null;
			}
			UtilPistonSpell.moveBlockTo(player.worldObj, player, message.pos, message.pos.offset(player.getHorizontalFacing()));
	 	}
		else if( message.keyPressed == ClientProxy.keyPull.getKeyCode())
	 	{  
			if(message.pos == null)
			{
				System.out.println("keyPressed: pos null, bad packet "+message.csv);
				return null;
			}
			UtilPistonSpell.moveBlockTo(player.worldObj, player, message.pos, message.pos.offset(player.getHorizontalFacing().getOpposite()));
	 	}
		else if( message.keyPressed == ClientProxy.keyTransform.getKeyCode())
	 	{ 
			if(message.pos == null)
			{
				System.out.println("keyPressed: pos null, bad packet "+message.csv);
				return null;
			}
			UtilBlockTransform.transformBlock(player, player.worldObj,  message.pos);
	 	} 
		 
		return null;
	}

}
 
