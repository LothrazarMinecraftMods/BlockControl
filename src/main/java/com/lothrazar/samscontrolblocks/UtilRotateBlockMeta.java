package com.lothrazar.samscontrolblocks;

import net.minecraft.block.Block; 
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class UtilRotateBlockMeta 
{  
	private static int INVALID = -1;
	public static void transformBlock(EntityPlayer player, World world, BlockPos pos)
	{
		IBlockState blockState = player.world.getBlockState(pos);
		Block block = blockState.getBlock();
		int metaCurrent, metaNew = INVALID;
		IBlockState blockStateNew = null;
		
		if(block == Blocks.RED_MUSHROOM_BLOCK)
		{
			metaCurrent = Blocks.RED_MUSHROOM_BLOCK.getMetaFromState(blockState);
			
			//from wiki we know that [11-13] are unused
			//meta 14 is only vanilla used one 	//OLD one was meta 0, all pores
			// http://minecraft.gamepedia.com/Data_values#Brown_and_red_mushroom_blocks
			if(0 <= metaCurrent && metaCurrent <= 9)
				metaNew = metaCurrent + 1;
			else if(metaCurrent == 10)
				metaNew = 14;
			else if(metaCurrent == 14)
				metaNew = 15;
			else if(metaCurrent == 15)
				metaNew = 0;

			if(metaNew > INVALID)
				blockStateNew =  Blocks.RED_MUSHROOM_BLOCK.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.STONEBRICK)
		{

			metaCurrent = Blocks.STONEBRICK.getMetaFromState(blockState);

			if(metaCurrent == 0)//0 is regular, 3 is chiseled
				metaNew = 3;
			else if(metaCurrent == 3)
				metaNew = 0; 
			//Not doing mossy or cracked here is deliberate, it costs vines or smelting time to make those
			
			if(metaNew > INVALID)
				blockStateNew =  Blocks.STONEBRICK.getStateFromMeta(metaNew);
		} 
		else if(block == Blocks.STONE)
		{

			metaCurrent = Blocks.STONE.getMetaFromState(blockState);

			//skip 0 which is regular stone
			//granite regular/polish
			if(metaCurrent == 1)
				metaNew = 2;
			else if(metaCurrent == 2)
				metaNew = 1; 
			//diorite regular/polish
			if(metaCurrent == 3)
				metaNew = 4;
			else if(metaCurrent == 4)
				metaNew = 3; 
			//andesite regular/polish
			if(metaCurrent == 5)
				metaNew = 6;
			else if(metaCurrent == 6)
				metaNew = 5; 

			if(metaNew > INVALID)
				blockStateNew =  Blocks.STONE.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.BROWN_MUSHROOM_BLOCK)
		{
			metaCurrent = Blocks.BROWN_MUSHROOM_BLOCK.getMetaFromState(blockState);

			if(0 <= metaCurrent && metaCurrent <= 9)
				metaNew = metaCurrent+1;
			else if(metaCurrent == 10)
				metaNew = 14;
			else if(metaCurrent == 14)
				metaNew = 15;
			else if(metaCurrent == 15)
				metaNew = 0;
			
			if(metaNew > INVALID)
				blockStateNew =  Blocks.BROWN_MUSHROOM_BLOCK.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.DOUBLE_STONE_SLAB)
		{
			metaCurrent = Blocks.DOUBLE_STONE_SLAB.getMetaFromState(blockState);

			if(metaCurrent == 0)//smoothstone slabs
				metaNew = 8;
			else if(metaCurrent == 8)
				metaNew = 0;

			if(metaCurrent == 1)//samdstpme slabs
				metaNew = 9;
			else if(metaCurrent == 9)
				metaNew = 1;

			if(metaNew > INVALID)
				blockStateNew =  Blocks.DOUBLE_STONE_SLAB.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.DOUBLE_STONE_SLAB2)
		{ 
			metaCurrent = Blocks.DOUBLE_STONE_SLAB2.getMetaFromState(blockState);

			if(metaCurrent == 0)//RED sandstone slabs
				metaNew = 8;
			else if(metaCurrent == 8)
				metaNew = 0;
  
			if(metaNew > INVALID)
				blockStateNew =  Blocks.DOUBLE_STONE_SLAB2.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.LOG2)
		{ 
			metaCurrent = Blocks.LOG2.getMetaFromState(blockState);


			int acaciaVert = 0;
			int darkVert=1;
			int acaciaEast=4;
			int darkEast=5;
			int acaciaNorth=8;
			int darkNorth=9;
			int acaciaMagic=12;
			int darkMagic=13;
			

			if(metaCurrent == acaciaVert)
				metaNew = acaciaEast;
			else if(metaCurrent == acaciaEast)
				metaNew = acaciaNorth;
			else if(metaCurrent == acaciaNorth)
				metaNew = acaciaMagic;
			else if(metaCurrent == acaciaMagic)
				metaNew = acaciaVert;

			if(metaCurrent == darkVert)
				metaNew = darkEast;
			else if(metaCurrent == darkEast)
				metaNew = darkNorth;
			else if(metaCurrent == darkNorth)
				metaNew = darkMagic;
			else if(metaCurrent == darkMagic)
				metaNew = darkVert;
  
			if(metaNew > INVALID)
				blockStateNew =  Blocks.LOG2.getStateFromMeta(metaNew);
		}
		else if(block == Blocks.LOG)
		{
			metaCurrent = Blocks.LOG.getMetaFromState(blockState);

			int oakVert = 0;
			int spruceVert=1;
			int birchVert=2;
			int jungleVert=3;
			int oakEast=4;
			int spruceEast=5;
			int birchEast=6;
			int jungleEast=7;
			int oakNorth=8;
			int spruceNorth=9;
			int birchNorth=10;
			int jungleNorth=11;
			int oakMagic=12;
			int spruceMagic=13;
			int birchMagic=14;
			int jungleMagic=15;//http://minecraft.gamepedia.com/Data_values#Wood
			
			if(metaCurrent == oakVert)
				metaNew = oakEast;
			else if(metaCurrent == oakEast)
				metaNew = oakNorth;
			else if(metaCurrent == oakNorth)
				metaNew = oakMagic;
			else if(metaCurrent == oakMagic)
				metaNew = oakVert;
			
			else if(metaCurrent == birchVert)
				metaNew = birchEast;
			else if(metaCurrent == birchEast)
				metaNew = birchNorth;
			else if(metaCurrent == birchNorth)
				metaNew = birchMagic;
			else if(metaCurrent == birchMagic)
				metaNew = birchVert;
			
			else if(metaCurrent == spruceVert)
				metaNew = spruceEast;
			else if(metaCurrent == spruceEast)
				metaNew = spruceNorth;
			else if(metaCurrent == spruceNorth)
				metaNew = spruceMagic;
			else if(metaCurrent == spruceMagic)
				metaNew = spruceVert;
			
			else if(metaCurrent == jungleVert)
				metaNew = jungleEast;
			else if(metaCurrent == jungleEast)
				metaNew = jungleNorth;
			else if(metaCurrent == jungleNorth)
				metaNew = jungleMagic;
			else if(metaCurrent == jungleMagic)
				metaNew = jungleVert;
  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.LOG.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STONE_SLAB)
		{
			metaCurrent = Blocks.STONE_SLAB.getMetaFromState(blockState);
			 
			if(metaCurrent <= 7) 
				metaNew = metaCurrent + 8;
			else
				metaNew = metaCurrent - 8; 
			 
			
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STONE_SLAB.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STONE_SLAB2)
		{
			metaCurrent = Blocks.STONE_SLAB2.getMetaFromState(blockState);
			 
			if(metaCurrent <= 7) 
				metaNew = metaCurrent + 8;
			else
				metaNew = metaCurrent - 8; 
			 
			
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STONE_SLAB2.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.WOODEN_SLAB)
		{
			metaCurrent = Blocks.WOODEN_SLAB.getMetaFromState(blockState);
			 
			if(metaCurrent <= 7) 
				metaNew = metaCurrent + 8;
			else
				metaNew = metaCurrent - 8; 
			 
			
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.WOODEN_SLAB.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.SANDSTONE)
		{
			metaCurrent = Blocks.SANDSTONE.getMetaFromState(blockState);
			 
			if(metaCurrent == 2) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
			 
			
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.SANDSTONE.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.RED_SANDSTONE)
		{
			metaCurrent = Blocks.RED_SANDSTONE.getMetaFromState(blockState);
			 
			if(metaCurrent == 2) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
			 
			
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.RED_SANDSTONE.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.SANDSTONE_STAIRS)
		{
			metaCurrent = Blocks.SANDSTONE_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.SANDSTONE_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.RED_SANDSTONE_STAIRS)
		{
			metaCurrent = Blocks.RED_SANDSTONE_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.RED_SANDSTONE_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STONE_STAIRS)
		{
			metaCurrent = Blocks.STONE_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STONE_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STONE_BRICK_STAIRS)
		{
			metaCurrent = Blocks.STONE_BRICK_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STONE_BRICK_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.QUARTZ_STAIRS)
		{
			metaCurrent = Blocks.QUARTZ_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.QUARTZ_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.BRICK_STAIRS)
		{
			metaCurrent = Blocks.BRICK_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.BRICK_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.SPRUCE_STAIRS)
		{
			metaCurrent = Blocks.SPRUCE_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.SPRUCE_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.BIRCH_STAIRS)
		{
			metaCurrent = Blocks.BIRCH_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.BIRCH_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.OAK_STAIRS)
		{
			metaCurrent = Blocks.OAK_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.OAK_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.JUNGLE_STAIRS)
		{
			metaCurrent = Blocks.JUNGLE_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.JUNGLE_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.ACACIA_STAIRS)
		{
			metaCurrent = Blocks.ACACIA_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.ACACIA_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.DARK_OAK_STAIRS)
		{
			metaCurrent = Blocks.DARK_OAK_STAIRS.getMetaFromState(blockState);

			if(metaCurrent == 8) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; 
		 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.DARK_OAK_STAIRS.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.QUARTZ_BLOCK)
		{
			metaCurrent = Blocks.QUARTZ_BLOCK.getMetaFromState(blockState);
			 
			if(metaCurrent == 4) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate pillars, or change to pillared/smooth 
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.QUARTZ_BLOCK.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.PUMPKIN)
		{ 
			metaCurrent = Blocks.PUMPKIN.getMetaFromState(blockState);
			 
			if(metaCurrent == 4) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.PUMPKIN.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.LIT_PUMPKIN)
		{ 
			metaCurrent = Blocks.LIT_PUMPKIN.getMetaFromState(blockState);
			 
			if(metaCurrent == 4) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.LIT_PUMPKIN.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.HAY_BLOCK)
		{ 
			metaCurrent = Blocks.HAY_BLOCK.getMetaFromState(blockState);
			 
			if(metaCurrent == 0) 
				metaNew = 4;
			else if(metaCurrent == 4)
				metaNew = 8;
			else if(metaCurrent == 8)
				metaNew = 0;
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.HAY_BLOCK.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.RAIL)
		{
			metaCurrent = Blocks.RAIL.getMetaFromState(blockState);

			/*RAILS:
			 * 0	Straight rail connecting to the north and south.
	1	Straight rail connecting to the east and west.
	2	Sloped rail ascending to the east.
	3	Sloped rail ascending to the west.
	4	Sloped rail ascending to the north.
	5	Sloped rail ascending to the south.
	6	Curved rail connecting to the south and east.
	7	Curved rail connecting to the south and west.
	8	Curved rail connecting to the north and west.
	9	Curved rail connecting to the north and east.*/
			if(metaCurrent == 9) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.RAIL.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.DROPPER)
		{ 
			metaCurrent = Blocks.DROPPER.getMetaFromState(blockState);
			 
			if(metaCurrent == 5) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.DROPPER.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.DISPENSER)
		{ 
			metaCurrent = Blocks.DISPENSER.getMetaFromState(blockState);
			 
			if(metaCurrent == 5) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.DISPENSER.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.HOPPER)
		{ 
			metaCurrent = Blocks.HOPPER.getMetaFromState(blockState);
			 
			if(metaCurrent == 5) 
				metaNew = 0;
			else if(metaCurrent == 0) //1 is unused (cant point Up)
				metaNew = 2;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.HOPPER.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.FURNACE)
		{ 
			metaCurrent = Blocks.FURNACE.getMetaFromState(blockState);
			  
			if(metaCurrent == 5) //0,1 are down/up, but only 4 directions here
				metaNew = 2;
			else
				metaNew = metaCurrent + 1; //rotate  
			  
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.FURNACE.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.CHEST)
		{ 
			metaCurrent = Blocks.CHEST.getMetaFromState(blockState);
			  
			if(metaCurrent == 5) //0,1 are down/up, but only 4 directions here
				metaNew = 2;
			else
				metaNew = metaCurrent + 1; //rotate  
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.CHEST.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.TRAPPED_CHEST)
		{ 
			metaCurrent = Blocks.TRAPPED_CHEST.getMetaFromState(blockState);
			  
			if(metaCurrent == 5) //0,1 are down/up, but only 4 directions here
				metaNew = 2;
			else
				metaNew = metaCurrent + 1; //rotate  
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.TRAPPED_CHEST.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.PISTON)
		{ 
			metaCurrent = Blocks.PISTON.getMetaFromState(blockState);

			if(metaCurrent == 5) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate 
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.PISTON.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STICKY_PISTON)
		{ 
			metaCurrent = Blocks.STICKY_PISTON.getMetaFromState(blockState);

			if(metaCurrent == 5) 
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate 
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STICKY_PISTON.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.WALL_SIGN)
		{ 
			metaCurrent = Blocks.WALL_SIGN.getMetaFromState(blockState);

			if(metaCurrent == 5) //0,1 are down/up, but only 4 directions here
				metaNew = 2;
			else
				metaNew = metaCurrent + 1; //rotate 
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.WALL_SIGN.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STANDING_SIGN)
		{ 
			metaCurrent = Blocks.STANDING_SIGN.getMetaFromState(blockState);

			if(metaCurrent == 15)  
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate 
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STANDING_SIGN.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.STANDING_BANNER)
		{
			metaCurrent = Blocks.STANDING_BANNER.getMetaFromState(blockState);

			if(metaCurrent == 15)  
				metaNew = 0;
			else
				metaNew = metaCurrent + 1; //rotate 
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.STANDING_BANNER.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.TRAPDOOR)
		{
			metaCurrent = Blocks.TRAPDOOR.getMetaFromState(blockState); 

			if(metaCurrent >= 8)  
				metaNew = metaCurrent - 8; //move it from top to bottom and back. NOT spinning its side
			else
				metaNew = metaCurrent + 8;
 
			if(metaNew > INVALID)
			{  
				blockStateNew =  Blocks.TRAPDOOR.getStateFromMeta(metaNew);
			}
		}
		else if(block == Blocks.IRON_TRAPDOOR)
		{
			metaCurrent = Blocks.IRON_TRAPDOOR.getMetaFromState(blockState); 

			if(metaCurrent >= 8)  
				metaNew = metaCurrent - 8;
			else
				metaNew = metaCurrent + 8;
 
			if(metaNew > INVALID)
			{ 
				blockStateNew =  Blocks.IRON_TRAPDOOR.getStateFromMeta(metaNew);
			}
		}
		  
		if(blockStateNew != null)
		{
			player.swingArm(EnumHand.MAIN_HAND);
			 
			if(world.isRemote) // clientside
			{
				ModControlBlocks.spawnParticle(world, EnumParticleTypes.CRIT_MAGIC, pos); 
			}
			else
			{
				ModControlBlocks.playSoundAt(player, "random.wood_click");
 
				player.world.setBlockState(pos,blockStateNew);
				  
				 
			} 
		}
	}
	
 
}
