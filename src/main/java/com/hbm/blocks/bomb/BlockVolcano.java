package com.hbm.blocks.bomb;
import com.hbm.util.ItemStackUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.entity.projectile.EntityShrapnel;
import com.hbm.explosion.ExplosionNT;
import com.hbm.explosion.ExplosionNT.ExAttrib;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class BlockVolcano extends Block {

	public static final PropertyInteger META = BlockDummyable.META;
	
	public BlockVolcano(final String s) {
		super(Material.IRON);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public void getSubBlocks(final CreativeTabs tab, final NonNullList<ItemStack> items){
		if(tab == CreativeTabs.SEARCH || tab == this.getCreativeTab())
			for(int i = 0; i < 4; ++i) {
				items.add(ItemStackUtil.itemStackFrom(this, 1, i));
			}
	}
	
	@Override
	public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced){
		final int meta = stack.getItemDamage();

		tooltip.add(BlockVolcano.isGrowing(meta) ? (TextFormatting.RED + "DOES GROW") : (TextFormatting.DARK_GRAY + "DOES NOT GROW"));
		tooltip.add(BlockVolcano.isExtinguishing(meta) ? (TextFormatting.RED + "DOES EXTINGUISH") : (TextFormatting.DARK_GRAY + "DOES NOT EXTINGUISH"));
	}
	
	@Override
	public int tickRate(final World world) {
		return 5;
	}

	@Override
	public void onBlockAdded(final World world, final BlockPos pos, final IBlockState state){
		if(!world.isRemote)
			world.scheduleUpdate(pos, this, this.tickRate(world));
	}
	
	@Override
	public void updateTick(final World world, final BlockPos pos, final IBlockState state, final Random rand){
		if(!world.isRemote) {
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			
			final int meta = world.getBlockState(pos).getValue(META);
			blastMagmaChannel(world, x, y, z, rand);
			raiseMagma(world, x, y, z, rand);
			spawnBlobs(world, x, y, z, rand);
			spawnSmoke(world, x, y, z, rand);
			
			updateVolcano(world, x, y, z, rand, meta);
		}
	}

	private void blastMagmaChannel(final World world, final int x, final int y, final int z, final Random rand) {
		
		final List<ExAttrib> attribs = Arrays.asList(ExAttrib.NODROP, ExAttrib.LAVA_V, ExAttrib.NOSOUND, ExAttrib.ALLMOD, ExAttrib.NOHURT);
		
		final ExplosionNT explosion = new ExplosionNT(world, null, x + 0.5, y + rand.nextInt(15) + 1.5, z + 0.5, 7);
		explosion.addAllAttrib(attribs);
		explosion.explode();
		
		final ExplosionNT explosion2 = new ExplosionNT(world, null, x + 0.5 + rand.nextGaussian() * 3, rand.nextInt(y + 1), z + 0.5 + rand.nextGaussian() * 3, 10);
		explosion2.addAllAttrib(attribs);
		explosion2.explode();
	}
	
	private void raiseMagma(final World world, final int x, final int y, final int z, final Random rand) {

		final int rX = x - 10 + rand.nextInt(21);
		final int rY = y + rand.nextInt(11);
		final int rZ = z - 10 + rand.nextInt(21);
		final BlockPos pos = new BlockPos(rX, rY, rZ);
		
		if(world.getBlockState(pos).getBlock() == Blocks.AIR && world.getBlockState(pos.down()).getBlock() == ModBlocks.volcanic_lava_block)
			world.setBlockState(pos, ModBlocks.volcanic_lava_block.getDefaultState());
	}
	
	private void spawnBlobs(final World world, final int x, final int y, final int z, final Random rand) {
		
		for(int i = 0; i < 3; i++) {
			final EntityShrapnel frag = new EntityShrapnel(world);
			frag.setLocationAndAngles(x + 0.5, y + 1.5, z + 0.5, 0.0F, 0.0F);
			frag.motionY = 1D + rand.nextDouble();
			frag.motionX = rand.nextGaussian() * 0.2D;
			frag.motionZ = rand.nextGaussian() * 0.2D;
			frag.setVolcano(true);
			world.spawnEntity(frag);
		}
	}
	
	/*
	 * I SEE SMOKE, AND WHERE THERE'S SMOKE THERE'S FIRE!
	 */
	private void spawnSmoke(final World world, final int x, final int y, final int z, final Random rand) {
		final NBTTagCompound dPart = new NBTTagCompound();
		dPart.setString("type", "vanillaExt");
		dPart.setString("mode", "volcano");
		PacketDispatcher.wrapper.sendToAllAround(new AuxParticlePacketNT(dPart, x + 0.5, y + 10, z + 0.5), new TargetPoint(world.provider.getDimension(), x + 0.5, y + 10, z + 0.5, 250));
	}
	
	private void updateVolcano(final World world, final int x, int y, final int z, final Random rand, final int meta) {
		final BlockPos pos = new BlockPos(x, y, z);
		if(rand.nextDouble() < this.getProgressChance(world, x, y, z, rand, meta)) {
			
			//if there's progress, check if the volcano can grow or not
			if(shouldGrow(world, x, y, z, rand, meta)) {
				
				//raise the level for growing volcanos, spawn lava, schedule update at the new position
				y++;
				world.scheduleUpdate(pos, this, this.tickRate(world));
				
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j <= 1; j++) {
						for(int k = -1; k <= 1; k++) {
							
							if(i + j + k == 0) {
								world.setBlockState(pos, this.getDefaultState().withProperty(META, meta), 3);
							} else {
								world.setBlockState(pos.add(i, j, k), ModBlocks.volcanic_lava_block.getDefaultState());
							}
						}
					}
				}
				
			//a progressing volcano that can't grow will extinguish
			} else if(isExtinguishing(meta)) {
				world.setBlockState(pos, ModBlocks.volcanic_lava_block.getDefaultState());
			}
			
		//if there's no progress, schedule an update on the current position
		}
		
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	public static final int META_STATIC_ACTIVE = 0;
	public static final int META_STATIC_EXTINGUISHING = 1;
	public static final int META_GROWING_ACTIVE = 2;
	public static final int META_GROWING_EXTINGUISHING = 3;
	
	public static boolean isGrowing(final int meta) {
		return meta == META_GROWING_ACTIVE || meta == META_GROWING_EXTINGUISHING;
	}
	
	public static boolean isExtinguishing(final int meta) {
		return meta == META_STATIC_EXTINGUISHING || meta == META_GROWING_EXTINGUISHING;
	}
	
	private boolean shouldGrow(final World world, final int x, final int y, final int z, final Random rand, final int meta) {
		
		//non-growing volcanoes should extinguish
		if(!isGrowing(meta))
			return false;
		
		//growing volcanoes extinguish when exceeding 200 blocks
		return y < 200;
	}
	
	private double getProgressChance(final World world, final int x, final int y, final int z, final Random rand, final int meta) {

		if(meta == META_STATIC_EXTINGUISHING)
			return 0.00003D; //about once every hour
		
		if(isGrowing(meta)) {
			
			if(meta != META_GROWING_ACTIVE || y < 199)
				return 0.007D; //about 250x an hour
		}
		
		return 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, META);
	}
	
	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(final int meta){
		return this.getDefaultState().withProperty(META, meta);
	}
}