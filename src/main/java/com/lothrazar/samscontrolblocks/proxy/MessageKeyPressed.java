package com.lothrazar.samscontrolblocks.proxy;
     
import com.lothrazar.samscontrolblocks.UtilBlockTransform;
import com.lothrazar.samscontrolblocks.UtilPistonSpell;

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
	  
	public static final int ID = 0;
	public MessageKeyPressed()
	{ 
	}
	
	public MessageKeyPressed(int keyCode)
	{ 
		this.keyPressed = (byte)keyCode;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.keyPressed = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(keyPressed);
	}
	
	@Override
	public IMessage onMessage(MessageKeyPressed message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 
 
		BlockPos posMouse = Minecraft.getMinecraft().objectMouseOver.getBlockPos();

		if( message.keyPressed == ClientProxy.keyPush.getKeyCode())
	 	{ 
			UtilPistonSpell.moveBlockTo(player.worldObj, player, posMouse, posMouse.offset(player.getHorizontalFacing()));
	 	}
		else if( message.keyPressed == ClientProxy.keyPull.getKeyCode())
	 	{  
			UtilPistonSpell.moveBlockTo(player.worldObj, player, posMouse, posMouse.offset(player.getHorizontalFacing().getOpposite()));
	 	}
		else if( message.keyPressed == ClientProxy.keyTransform.getKeyCode())
	 	{ 
			UtilBlockTransform.transformBlock(player, player.worldObj,  posMouse);
	 	} 
		 
		return null;
	}

}
 
