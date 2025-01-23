package com.hbm.blocks.network.energy;

import java.util.ArrayList;
import java.util.List;

import com.hbm.main.MainRegistry;
import com.hbm.lib.Library;
import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.ILookOverlay;
import com.hbm.blocks.ITooltipProvider;
import com.hbm.tileentity.INBTPacketReceiver;
import com.hbm.tileentity.network.energy.TileEntityCableBaseNT;
import com.hbm.util.I18nUtil;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.Optional;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;

public class BlockCableGauge extends BlockContainer implements ILookOverlay, ITooltipProvider {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockCableGauge(final Material materialIn, final String s) {
		super(materialIn);
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.blockTab);

		ModBlocks.ALL_BLOCKS.add(this);
	}

	@Override
	protected BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(final IBlockState state){
		return state.getValue(FACING).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(final int meta) {
		EnumFacing enumfacing = EnumFacing.byIndex(meta);
        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public IBlockState withRotation(final IBlockState state, final Rotation rot){
		return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(final IBlockState state, final Mirror mirrorIn){
		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}
	
	@Override
	public void onBlockPlacedBy(final World worldIn, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public IBlockState getStateForPlacement(final World world, final BlockPos pos, final EnumFacing facing, final float hitX, final float hitY, final float hitZ, final int meta, final EntityLivingBase placer, final EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int meta) {
		return new TileEntityCableGauge();
	}

	@Override
	public EnumBlockRenderType getRenderType(final IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack stack, final World player, final List<String> tooltip, final ITooltipFlag advanced) {
        this.addStandardInfo(tooltip);
        super.addInformation(stack, player, tooltip, advanced);
    }

    @SideOnly(Side.CLIENT)
	public void printHook(final Pre event, final World world, final int x, final int y, final int z){
		final TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		if(!(te instanceof TileEntityCableGauge diode))
			return;

        final List<String> text = new ArrayList();
		text.add(Library.getShortNumber(diode.deltaLastSecond) + "HE/s");
		
		ILookOverlay.printGeneric(event, I18nUtil.resolveKey(getTranslationKey() + ".name"), 0xffff00, 0x404000, text);
	}

	@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
	public static class TileEntityCableGauge extends TileEntityCableBaseNT implements INBTPacketReceiver, SimpleComponent {

		private long lastMeasurement = 10;
		private long deltaSecond = 0;
		public long deltaLastSecond = 0;
		
		@Override
		public void update() {
			super.update();

			if(!world.isRemote) {
				
				if(network != null) {
					final long total = network.getTotalTransfer();
					final long deltaTick = total - this.lastMeasurement;
					this.lastMeasurement = total;
					
					try {
						if(world.getTotalWorldTime() % 20 == 0) {
							this.deltaLastSecond = this.deltaSecond;
							this.deltaSecond = 0;
							final NBTTagCompound data = new NBTTagCompound();
							data.setLong("deltaS", deltaLastSecond);
							INBTPacketReceiver.networkPack(this, data, 25);
						}
						this.deltaSecond += deltaTick;
						
					} catch(final Exception ex) { }
				}
			}
		}

		@Override
		public void networkUnpack(final NBTTagCompound nbt) {
			this.deltaLastSecond = Math.max(nbt.getLong("deltaS"), 0);
		}
	
		@Override
		public String getComponentName() {
			return "power_gauge";
		}

		@Callback(doc = "getPowerPerS(); returns the power(long) per s traveling through the gauge.")
		public Object[] getPowerPerS(final Context context, final Arguments args) {
			return new Object[] {deltaLastSecond};
		}
	}
}
