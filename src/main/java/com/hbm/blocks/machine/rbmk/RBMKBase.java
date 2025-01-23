package com.hbm.blocks.machine.rbmk;
import com.hbm.util.ItemStackUtil;

import javax.annotation.Nullable;
import java.util.List;

import com.hbm.blocks.BlockDummyable;
import com.hbm.handler.MultiblockHandlerXR;
import com.hbm.items.ModItems;
import com.hbm.items.machine.ItemRBMKLid;
import com.hbm.lib.ForgeDirection;
import com.hbm.lib.RefStrings;
import com.hbm.main.MainRegistry;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.tileentity.machine.rbmk.RBMKDials;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKBase;

import api.hbm.block.IToolable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public abstract class RBMKBase extends BlockDummyable implements IToolable, ITooltipProvider {

	public static boolean dropLids = true;
	public static boolean digamma = false;
	public ResourceLocation coverTexture;
	public ResourceLocation columnTexture;
	
	public RBMKBase(final String s, final String columnTexture){
		super(Material.IRON, s);
		coverTexture = new ResourceLocation(RefStrings.MODID, "textures/blocks/rbmk/" + s + ".png");
		this.columnTexture = new ResourceLocation(RefStrings.MODID, "textures/blocks/rbmk/" + columnTexture +".png");
	}
	
	@Override
	public int[] getDimensions() {
		return new int[] {3, 0, 0, 0, 0, 0};
	}

	@Override
	public int getOffset() {
		return 0;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, @Nullable final World player, final List<String> tooltip, final ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
    }
	
	public boolean openInv(final World world, final int x, final int y, final int z, final EntityPlayer player, final int gui, final EnumHand hand) {
		
		if(world.isRemote) {
			return true;
		}
		
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos == null)
			return false;
		
		final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
		
		if(!(te instanceof TileEntityRBMKBase rbmk))
			return false;

        if(player.getHeldItem(hand) != null && player.getHeldItem(hand).getItem() instanceof ItemRBMKLid) {
			
			if(!rbmk.hasLid())
				return false;
		}
		
		if(!player.isSneaking()) {
			FMLNetworkHandler.openGui(player, MainRegistry.instance, gui, world, pos[0], pos[1], pos[2]);
			return true;
		} else {
			return true;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess source, final BlockPos bpos){
		float height = 0.0F;
		
		final int[] pos = this.findCore(source, bpos.getX(), bpos.getY(), bpos.getZ());
		
		if(pos != null) {
			final TileEntity te = source.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			
			if(te instanceof TileEntityRBMKBase rbmk) {

                if(rbmk.hasLid()) {
					height += 0.25F;
				}
			}
		}
		return new AxisAlignedBB(0, 0, 0, 1, 1+height, 1);
	}
	
	/*
	 * NORTH: no cover
	 * EAST: concrete cover
	 * SOUTH: lead glass cover
	 * WEST: UNUSED
	 */

	public static final ForgeDirection DIR_NO_LID = ForgeDirection.NORTH;
	public static final ForgeDirection DIR_NORMAL_LID = ForgeDirection.EAST;
	public static final ForgeDirection DIR_GLASS_LID = ForgeDirection.SOUTH;
	
	@Override
	protected void fillSpace(final World world, final int x, final int y, final int z, final ForgeDirection dir, final int o){
		MultiblockHandlerXR.fillSpace(world, x + dir.offsetX * o, y + dir.offsetY * o, z + dir.offsetZ * o, getDimensions(world), this, dir);
		this.makeExtra(world, x, y + RBMKDials.getColumnHeight(world), z);
	}
	
	@Override
	protected ForgeDirection getDirModified(final ForgeDirection dir) {
		return DIR_NO_LID;
	}
	
	public int[] getDimensions(final World world) {
		return new int[] {RBMKDials.getColumnHeight(world), 0, 0, 0, 0, 0};
	}
	
	@Override
	public void breakBlock(final World world, final BlockPos pos, final IBlockState state){
		if(!world.isRemote && dropLids) {
			final int i = state.getValue(META);
			if(i == DIR_NORMAL_LID.ordinal() + offset) {
				world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5 + RBMKDials.getColumnHeight(world), pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(ModItems.rbmk_lid)));
			}
			if(i == DIR_GLASS_LID.ordinal() + offset) {
				world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5 + RBMKDials.getColumnHeight(world), pos.getZ() + 0.5, ItemStackUtil.itemStackFrom(ModItems.rbmk_lid_glass)));
			}
		}
		
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean onScrew(final World world, final EntityPlayer player, final int x, final int y, final int z, final EnumFacing side, final float fX, final float fY, final float fZ, final EnumHand hand, final ToolType tool){
		if(tool != ToolType.SCREWDRIVER)
			return false;
		
		final int[] pos = this.findCore(world, x, y, z);
		
		if(pos != null) {
			final TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
			
			if(te instanceof TileEntityRBMKBase rbmk) {

                final int i = rbmk.getBlockMetadata();
				
				if(rbmk.hasLid() && rbmk.isLidRemovable()) {
					
					if(!world.isRemote) {
						if(i == DIR_NORMAL_LID.ordinal() + offset) {
							world.spawnEntity(new EntityItem(world, pos[0] + 0.5, pos[1] + 0.5 + RBMKDials.getColumnHeight(world), pos[2] + 0.5, ItemStackUtil.itemStackFrom(ModItems.rbmk_lid)));
						}
						if(i == DIR_GLASS_LID.ordinal() + offset) {
							world.spawnEntity(new EntityItem(world, pos[0] + 0.5, pos[1] + 0.5 + RBMKDials.getColumnHeight(world), pos[2] + 0.5, ItemStackUtil.itemStackFrom(ModItems.rbmk_lid_glass)));
						}
						
						world.setBlockState(new BlockPos(pos[0], pos[1], pos[2]), this.getDefaultState().withProperty(META, DIR_NO_LID.ordinal() + BlockDummyable.offset), 3);
						final NBTTagCompound nbt = rbmk.writeToNBT(new NBTTagCompound());
						world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2])).readFromNBT(nbt);
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
}
