package com.lothrazar.samscontrolblocks.proxy;
     
import com.lothrazar.samscontrolblocks.ModControlBlocks;
import com.lothrazar.samscontrolblocks.UtilMoveBlock;

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class MessageKeyPull implements IMessage, IMessageHandler<MessageKeyPull, IMessage>
{
	private BlockPos pos;
	private String csv;
	public static final int ID = 1;
	public MessageKeyPull()
	{ 
	}
	
	public MessageKeyPull(BlockPos pm)
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
	public IMessage onMessage(MessageKeyPull message, MessageContext ctx)
	{  
		EntityPlayer player = ctx.getServerHandler().player; //.playerEntity; 		
		
		UtilMoveBlock.moveBlockTo(player.world, player, message.pos, message.pos.offset(player.getHorizontalFacing().getOpposite()));
	
		return null;
	}
}
 
