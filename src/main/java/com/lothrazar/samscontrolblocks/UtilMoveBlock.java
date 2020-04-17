package com.lothrazar.samscontrolblocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World; 

public class UtilMoveBlock 
{ 
	public static ArrayList<Block> ignoreList = new ArrayList<Block>();
	private static String ignoreListFromConfig = "";
	 
	private static void translateCSV()
	{
		//do this on the fly, could be items not around yet during config change
		if(ignoreList.size() == 0)
		{
			ignoreList = ModControlBlocks.getBlockListFromCSV(ignoreListFromConfig); 
		
			//ignoreList.add(Blocks.bedrock);
			ignoreList.add(Blocks.END_PORTAL_FRAME);
			ignoreList.add(Blocks.END_PORTAL);
			ignoreList.add(Blocks.PORTAL);
			ignoreList.add(Blocks.BED);
			ignoreList.add(Blocks.DARK_OAK_DOOR);
			ignoreList.add(Blocks.ACACIA_DOOR);
			ignoreList.add(Blocks.BIRCH_DOOR);
			ignoreList.add(Blocks.OAK_DOOR);
			ignoreList.add(Blocks.SPRUCE_DOOR);
			ignoreList.add(Blocks.JUNGLE_DOOR);
			ignoreList.add(Blocks.IRON_DOOR);
			ignoreList.add(Blocks.SKULL);
		}
	}
	
	public static void seIgnoreBlocksFromString(String csv)
	{ 
		ignoreListFromConfig = csv;
	} 
 
	public static void moveBlockTo(World world, EntityPlayer player,BlockPos pos, BlockPos posMoveToHere)
	{
		IBlockState hit = world.getBlockState(pos);
		translateCSV();

		if(hit == null || ignoreList.contains(hit.getBlock()))
		{
			return;
		}
		
		if(world.isAirBlock(posMoveToHere) && world.isBlockModifiable(player, pos)) 
		{
			if(world.isRemote) 
			{
				ModControlBlocks.spawnParticle(world, EnumParticleTypes.CRIT_MAGIC, pos); 
			}
			else
			{  
				ModControlBlocks.playSoundAt(player, "random.wood_click");

				//they swap places
				//world.destroyBlock(posMoveToHere, false);
				world.destroyBlock(pos, false);
				world.setBlockState(posMoveToHere, hit);//pulls the block towards the player
				player.swingArm(EnumHand.MAIN_HAND);
			} 
		} 
	}
}