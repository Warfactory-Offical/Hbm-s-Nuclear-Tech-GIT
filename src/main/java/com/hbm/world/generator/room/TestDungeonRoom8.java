package com.hbm.world.generator.room;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.crafting.handlers.MKUCraftingHandler;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntitySafe;
import com.hbm.world.generator.CellularDungeon;
import com.hbm.world.generator.CellularDungeonRoom;
import com.hbm.world.generator.DungeonToolbox;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class TestDungeonRoom8 extends CellularDungeonRoom {

	public TestDungeonRoom8(final CellularDungeon parent) {
		super(parent);
	}

	public void generateMain(final World world, final int x, final int y, final int z) {
		
		super.generateMain(world, x, y, z);
		DungeonToolbox.generateBox(world, x + parent.width / 2 - 3, y + 1, z + parent.width / 2 - 3, 1, parent.height - 2, 1, ModBlocks.meteor_pillar.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y));
		DungeonToolbox.generateBox(world, x + parent.width / 2 + 3, y + 1, z + parent.width / 2 - 3, 1, parent.height - 2, 1, ModBlocks.meteor_pillar.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y));
		DungeonToolbox.generateBox(world, x + parent.width / 2 + 3, y + 1, z + parent.width / 2 + 3, 1, parent.height - 2, 1, ModBlocks.meteor_pillar.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y));
		DungeonToolbox.generateBox(world, x + parent.width / 2 - 3, y + 1, z + parent.width / 2 + 3, 1, parent.height - 2, 1, ModBlocks.meteor_pillar.getDefaultState().withProperty(BlockRotatedPillar.AXIS, EnumFacing.Axis.Y));
		world.setBlockState(new BlockPos(x + parent.width / 2 - 3, y + 3, z + parent.width / 2 - 3), ModBlocks.meteor_brick_chiseled.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + parent.width / 2 + 3, y + 3, z + parent.width / 2 - 3), ModBlocks.meteor_brick_chiseled.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + parent.width / 2 + 3, y + 3, z + parent.width / 2 + 3), ModBlocks.meteor_brick_chiseled.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + parent.width / 2 - 3, y + 3, z + parent.width / 2 + 3), ModBlocks.meteor_brick_chiseled.getDefaultState(), 2);

		DungeonToolbox.generateBox(world, x + 4, y + 1, z + 4, parent.width - 8, 1, parent.width - 8, ModBlocks.meteor_polished.getDefaultState());
		
		final int i = world.rand.nextInt(8);
		
		switch(i) {
		case 0: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.meteor_brick_chiseled.getDefaultState(), 3); 
			world.setBlockState(new BlockPos(x + parent.width / 2, y + 1, z + parent.width / 2), ModBlocks.block_coltan.getDefaultState(), 3); 
			break;
		case 1: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.ntm_dirt.getDefaultState(), 3); break;
		case 2: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.block_starmetal.getDefaultState(), 3); break;
		case 3: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.statue_elb_f.getDefaultState(), 3); break;
		case 4: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.crate_red.getDefaultState(), 3); break;
		case 5: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.block_schrabidium_cluster.getStateFromMeta(0), 3); break;
		case 6: world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.block_meteor.getDefaultState(), 3); break;
		case 7:
			world.setBlockState(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2), ModBlocks.safe.getDefaultState(), 3);
			if(world.getTileEntity(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2)) instanceof TileEntitySafe) {

				final int r = world.rand.nextInt(10);
				
				if(r == 0)
					((TileEntitySafe)world.getTileEntity(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2))).inventory.setStackInSlot(7, ItemStackUtil.itemStackFrom(ModItems.book_of_));
				else if(r < 4)
					((TileEntitySafe)world.getTileEntity(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2))).inventory.setStackInSlot(7, genetateMKU(world));
				else
					((TileEntitySafe)world.getTileEntity(new BlockPos(x + parent.width / 2, y + 2, z + parent.width / 2))).inventory.setStackInSlot(7, ItemStackUtil.itemStackFrom(Items.BOOK));
			}
			break;
		}
	}
	
	private static final String[] bookIodine = new String[] {
			"Alright you will not believe this, but old man weathervane finally managed to show up again since he left two weeks ago and what's more surprising is the fact that he actually decided to spill the beans on what they were doing in the canyon:",
			"Apparently the morons form R&D discovered a compound that is mostly inorganic, pretty much like a toxin in nature, but get this: The dying cells will reproduce said toxin and excete it through the skin, creating an aerosol that is highly contageous.",
			"It's just like a virus, but not a virus. The composition is weird, you can mix it in any household bottle but you do have to get the order right. The doc told me that the first ingredient which is just §4powdered iodine§r crystals goes into slot §4"
	};
	private static final String[] bookPhosphorous = new String[] {
			"Heyo, it's me again. I assume you got my last memo, the doc wasn't too happy about it. I'll have to do this quick, the dunderheads from R&D are currently moaning again, probably over money. Again. Anyway, doc weathervane found that the second",
			"Ingredient is §4red phosphorous powder§r, which has to be mixed into slot §4"
	};
	private static final String[] bookDust = new String[] {
			"The doc was furious when he found out that the R&D dorks kept the one remaining sample, ranting about gross negligence this and a doomsday scenario that. I told him to chill for a minute, getting all worked up isn't good for his blood pressure, not",
			"that he has much blood left to begin with. One of the R&D morons slipped some more info into last week's circular, they call their little concoction \"§6§lMKU§r\" whatever that means, and that it contains actual household lint. Can you believe that?",
			"One of the most dangerous inventions of theirs and it contains §4dust§r. Strangely they also mentioned that it goes into slot §4"
	};
	private static final String[] bookMercury = new String[] {
			"Well that settles that. Not counting the vomitting blood part, the toxicological report mostly resembles that of mercury poisoning. Why? because our little mix also contains mercury! I just wonder where all that stuff comes from when being replicated by the body?", 
			"Whatever, the §4mercury drop§r goes into slot §4"
	};
	private static final String[] bookFlower = new String[] {
			"Remember when i mentioned in my first memo that the compound is mostly anorganic? Well guess what, the old man shared the fourth ingredient: Ipomoea nil, a genus of flower. Morning glory! It might be due to its low sulfur content, whatever might be the case,",
			"it does not work with other flowers. The §4morning glory§r goes into slot §4"
	};
	private static final String[] bookSyringe = new String[] {
			"A little addendum to my fifth message, obviously you have to store this MKU stuff in a container. The R&D nuts used regular metal syringes that they got from medical. Surplus ware i presume, they got thousands of needles just lying around.", 
			"The §4metal syringe§r goes into slot §4"
	};
	
	public static ItemStack genetateMKU(final World world) {
		final int r = world.rand.nextInt(6);

		final ItemStack book = ItemStackUtil.itemStackFrom(Items.WRITTEN_BOOK);
		book.setTagCompound(new NBTTagCompound());
		book.getTagCompound().setString("author", "Dave");
		book.getTagCompound().setString("title", "§6§lMKU§e Note "+(r+1));
		final NBTTagList nbt = new NBTTagList();
		
		final String[] pages = generatePages(r, world);
		for(final String s : pages) {
			nbt.appendTag(new NBTTagString(ITextComponent.Serializer.componentToJson(new TextComponentString(s))));
		}
		
		book.getTagCompound().setTag("pages", nbt);
		
		return book;
	}

	
	public static String[] generatePages(final int r, final World world) {
		
		final String[] orig;
		final ItemStack ingred;

		if(r == 0) {
			orig = bookIodine;
			ingred = ItemStackUtil.itemStackFrom(ModItems.powder_iodine);
		} else if(r == 1) {
			orig = bookPhosphorous;
			ingred = ItemStackUtil.itemStackFrom(ModItems.powder_fire);
		} else if(r == 2) {
			orig = bookDust;
			ingred = ItemStackUtil.itemStackFrom(ModItems.dust);
		} else if(r == 3) {
			orig = bookMercury;
			ingred = ItemStackUtil.itemStackFrom(ModItems.nugget.getItemStack(MaterialMineral.MERCURY));
		} else if(r == 4) {
			orig = bookFlower;
			ingred = ItemStackUtil.itemStackFrom(ModItems.morning_glory);
		} else {
			orig = bookSyringe;
			ingred = ItemStackUtil.itemStackFrom(ModItems.syringe_metal_empty);
		}
		
		final String[] copy = new String[orig.length];
		
		for(int i = 0; i < orig.length; i++) {
			copy[i] = orig[i]; //Strings are reference types and i'm really not counting on my luck here
		}
		
		copy[copy.length - 1] += getSlot(world, ingred);
		
		return copy;
	}
	
	public static int getSlot(final World world, final ItemStack stack) {
		
		MKUCraftingHandler.generateRecipe(world);
		final ItemStack[] recipe = MKUCraftingHandler.MKURecipe;
		
		if(recipe == null) //take no chances
			return -2;
		
		for(int i = 0; i < 9; i++) {
			
			if(recipe[i] != null && ItemStackUtil.isSameMetaItem(recipe[i], stack)) {
				return i + 1;
			}
		}
		
		return -1;
	}
	
}