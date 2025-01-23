package com.hbm.items.tool;

import com.hbm.blocks.turret.TurretBase;
import com.hbm.blocks.turret.TurretBaseNT;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.lib.Library;
import com.hbm.main.MainRegistry;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.tileentity.turret.TileEntityTurretBase;
import com.hbm.tileentity.turret.TileEntityTurretBaseNT;
import com.hbm.tileentity.turret.TileEntityTurretCheapo;
import com.hbm.util.I18nUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import java.util.List;

public class ItemTurretControl extends Item {

	public ItemTurretControl(final String s) {
		this.setTranslationKey(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.weaponTab);

		ModItems.ALL_ITEMS.add(this);
	}

	@Override
	public void onUpdate(final ItemStack stack, final World worldIn, final Entity entity, final int itemSlot, final boolean isSelected) {
		if (entity instanceof EntityPlayer player) {

            if (player.getHeldItem(EnumHand.MAIN_HAND).equals(stack) || player.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
				if (stack.hasTagCompound()) {
					final int x = stack.getTagCompound().getInteger("xCoord");
					final int y = stack.getTagCompound().getInteger("yCoord");
					final int z = stack.getTagCompound().getInteger("zCoord");
					final BlockPos pos = new BlockPos(x, y, z);
					
					final TileEntity te = worldIn.getTileEntity(pos);

					if (te != null && te instanceof TileEntityTurretBase turret) {

                        if (!turret.isAI) {
							turret.rotationYaw = player.rotationYaw;
							turret.rotationPitch = player.rotationPitch;
							if (turret.rotationPitch < -60)
								turret.rotationPitch = -60;
							if (turret.rotationPitch > 30)
								turret.rotationPitch = 30;

							if (turret instanceof TileEntityTurretCheapo) {
								if (turret.rotationPitch < -30)
									turret.rotationPitch = -30;
								if (turret.rotationPitch > 15)
									turret.rotationPitch = 15;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void addInformation(final ItemStack stack, final World worldIn, final List<String> list, final ITooltipFlag flagIn) {
		if (stack.getTagCompound() != null) {
			list.add(I18nUtil.resolveKey("desc.turrectcontrol"));
			list.add("X: " + stack.getTagCompound().getInteger("xCoord"));
			list.add("Y: " + stack.getTagCompound().getInteger("yCoord"));
			list.add("Z: " + stack.getTagCompound().getInteger("zCoord"));
		} else {
			list.add(I18nUtil.resolveKey("desc.turrectcontrol.noconnect"));
		}
	}

	@Override
	public EnumActionResult onItemUse(final EntityPlayer player, final World worldIn, final BlockPos pos, final EnumHand hand, final EnumFacing facing, final float hitX, final float hitY, final float hitZ) {
		boolean valid = false;
		int x, y, z;
		x = y = z = 0;
		if (worldIn.getBlockState(pos).getBlock() instanceof TurretBase) {
			valid = true;
			x = pos.getX();
			y = pos.getY();
			z = pos.getZ();
		}
		if(worldIn.getBlockState(pos).getBlock() instanceof TurretBaseNT){
			final int[] cPos = ((TurretBaseNT) worldIn.getBlockState(pos).getBlock()).findCore(worldIn, pos.getX(), pos.getY(), pos.getZ());
			if(pos != null){
				x = cPos[0];
				y = cPos[1];
				z = cPos[2];

				valid = true;
			}
		}

		if(valid){
			final ItemStack stack = player.getHeldItem(hand);
			if (stack.getTagCompound() != null) {
				stack.getTagCompound().setInteger("xCoord", x);
				stack.getTagCompound().setInteger("yCoord", y);
				stack.getTagCompound().setInteger("zCoord", z);
			} else {
				stack.setTagCompound(new NBTTagCompound());
				stack.getTagCompound().setInteger("xCoord", x);
				stack.getTagCompound().setInteger("yCoord", y);
				stack.getTagCompound().setInteger("zCoord", z);
			}
			if (worldIn.isRemote) {
				player.sendMessage(new TextComponentTranslation("chat.turretcontrol.linked"));
			}

			worldIn.playSound(player.posX, player.posY, player.posZ, HBMSoundHandler.techBleep, SoundCategory.PLAYERS, 1.0F, 1.0F, false);

			return EnumActionResult.SUCCESS;
		}

		return EnumActionResult.PASS;
	}

	@Override
	public EnumAction getItemUseAction(final ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public void onPlayerStoppedUsing(final ItemStack stack, final World worldIn, final EntityLivingBase entityLiving, final int timeLeft) {
		int j = this.getMaxItemUseDuration(stack) - timeLeft;
		if (entityLiving instanceof EntityPlayer) {
			final ArrowLooseEvent event = new ArrowLooseEvent((EntityPlayer) entityLiving, stack, worldIn, j, false);
			MinecraftForge.EVENT_BUS.post(event);

			j = event.getCharge();
		}
		if (stack.hasTagCompound()) {
			final int x = stack.getTagCompound().getInteger("xCoord");
			final int y = stack.getTagCompound().getInteger("yCoord");
			final int z = stack.getTagCompound().getInteger("zCoord");
			final BlockPos pos = new BlockPos(x, y, z);

			if (worldIn.getBlockState(pos).getBlock() instanceof TurretBase) {

				final TileEntity te = worldIn.getTileEntity(pos);

				if (te != null && te instanceof TileEntityTurretBase turret) {

                    if (!turret.isAI) {
						((TurretBase) worldIn.getBlockState(pos).getBlock()).executeReleaseAction(worldIn, j, entityLiving.rotationYaw, entityLiving.rotationPitch, pos);
					}
				}
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(final ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final EntityPlayer playerIn, final EnumHand handIn) {
		final ArrowNockEvent event = new ArrowNockEvent(playerIn, playerIn.getHeldItem(handIn), handIn, worldIn, false);
		MinecraftForge.EVENT_BUS.post(event);
		{
			playerIn.setActiveHand(handIn);
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void onUsingTick(final ItemStack stack, final EntityLivingBase mob, final int count) {
		final World world = mob.world;
		
		if (!world.isRemote && stack.hasTagCompound()) {
			final int x = stack.getTagCompound().getInteger("xCoord");
			final int y = stack.getTagCompound().getInteger("yCoord");
			final int z = stack.getTagCompound().getInteger("zCoord");
			final BlockPos pos = new BlockPos(x, y, z);
			if (world.getBlockState(pos).getBlock() instanceof TurretBase) {

				final TileEntity te = world.getTileEntity(pos);
				if (te != null && te instanceof TileEntityTurretBase turret) {
                    if (!turret.isAI && turret.ammo > 0) {
						if (((TurretBase) world.getBlockState(pos).getBlock()).executeHoldAction(world, stack.getMaxItemUseDuration() - count, mob.rotationYaw, mob.rotationPitch, pos))
							turret.ammo--;
					}
				}
			}

			if ((world.getBlockState(pos).getBlock() instanceof TurretBaseNT) && (mob instanceof EntityPlayer player)) {
                final TileEntity te = world.getTileEntity(pos);

				if(te != null && te instanceof TileEntityTurretBaseNT turret) {

                    RayTraceResult rpos = Library.rayTrace(player, 200, 1, true, true, false);
					
					if(pos == null)
						rpos = Library.rayTrace(player, 200, 1);
					
					if(pos != null && rpos != null) { 
						
						final Vec3 vecOrigin = Vec3.createVectorHelper(player.posX, player.posY + player.eyeHeight - player.getYOffset(), player.posZ);
						final Vec3 vecDestination = Vec3.createVectorHelper(rpos.getBlockPos().getX(), rpos.getBlockPos().getY(), rpos.getBlockPos().getZ());
						
						final List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(200, 200, 200));
						
						for(final Entity e : list) {

							if (e.canBeCollidedWith() && e != player) {
								
								final RayTraceResult mop = e.getEntityBoundingBox().expand(0.2, 0.2, 0.2).calculateIntercept(vecOrigin.toVec3d(), vecDestination.toVec3d());
								
								if(mop != null) {
									
									rpos = mop;
									rpos.typeOfHit = RayTraceResult.Type.ENTITY;
									rpos.entityHit = e;
								}
							}
						}
						
						if(rpos.typeOfHit == RayTraceResult.Type.ENTITY) {
							turret.target = rpos.entityHit;
							turret.turnTowards(turret.getEntityPos(rpos.entityHit));
							world.playSound(player.posX, player.posY, player.posZ, HBMSoundHandler.techBleep, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
						} else if(rpos.typeOfHit == RayTraceResult.Type.BLOCK){
							turret.tPos = vecDestination.toVec3d();
							turret.manualOverride = true;
							turret.turnTowards(Vec3.createVectorHelper(rpos.getBlockPos().getX() + 0.5, rpos.getBlockPos().getY() + 0.5, rpos.getBlockPos().getZ() + 0.5).toVec3d());
						}
					}
				}
			}
		}
	}
}
