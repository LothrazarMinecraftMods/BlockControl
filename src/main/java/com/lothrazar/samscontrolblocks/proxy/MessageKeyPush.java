package com.lothrazar.samscontrolblocks.proxy;
     
import com.lothrazar.samscontrolblocks.ModControlBlocks;
import com.lothrazar.samscontrolblocks.UtilMoveBlock;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

public class MessageKeyPush implements IMessage, IMessageHandler<MessageKeyPush, IMessage>
{
	private BlockPos pos;
	private String csv;
	public static final int ID = 0;
	public MessageKeyPush()
	{ 
	}
	
	public MessageKeyPush(BlockPos pm)
	{ 
		pos = pm;
		csv = ModControlBlocks.posToCSV(pos);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		//http://www.minecraftforge.net/forum/index.php?topic=20135.0

		csv = ByteBufUtils.readUTF8String(buf); 
        
		pos = ModControlBlocks.stringCSVToBlockPos(csv);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
        ByteBufUtils.writeUTF8String(buf, csv);
	}
	
	@Override
	public IMessage onMessage(MessageKeyPush message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().playerEntity; 		
		
		UtilMoveBlock.moveBlockTo(player.worldObj, player, message.pos, message.pos.offset(player.getHorizontalFacing()));
	
		return null;
	}
}
 
