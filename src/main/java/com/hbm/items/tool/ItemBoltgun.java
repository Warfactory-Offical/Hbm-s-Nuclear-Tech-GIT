package com.hbm.items.tool;

import com.hbm.items.IAnimatedItem;
import com.hbm.items.ModItems;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationKeyframe;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.util.EntityDamageUtil;

import api.hbm.block.IToolable;
import api.hbm.block.IToolable.ToolType;
import crafttweaker.api.text.ITextComponent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.codehaus.plexus.util.CachedMap;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ItemBoltgun extends Item implements IAnimatedItem {

        public ItemBoltgun(String s) {
            this.setTranslationKey(s);
            this.setRegistryName(s);

            this.setMaxStackSize(1);
            this.setCreativeTab(MainRegistry.controlTab);
            ToolType.BOLT.register(new ItemStack(this));

            ModItems.ALL_ITEMS.add(this);
        }

        @Override
        public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull EntityPlayer player, Entity entity) {
            if (!entity.isEntityAlive()) return false;
            World world = player.world;

            ItemStack[] bolts = {
                    // TODO
//                    new ItemStack(ModItems.bolt_spike),
//                    Mats.MAT_STEEL.make(ModItems.bolt),
//                    Mats.MAT_TUNGSTEN.make(ModItems.bolt),
//                    Mats.MAT_DURA.make(ModItems.bolt)
                    ModItems.bolt_tungsten.getDefaultInstance(),
                    ModItems.bolt_dura_steel.getDefaultInstance()
            };

            for (ItemStack bolt : bolts) {
                if (consumeBolt(player, bolt)) {
                    handleBoltAttack(player, entity, world);
                    return true;
                }
            }
            return false;
        }

        private boolean consumeBolt(EntityPlayer player, ItemStack bolt) {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack slot = player.inventory.getStackInSlot(i);
                if (slot.getItem() == bolt.getItem() && slot.getItemDamage() == bolt.getItemDamage()) {
                    player.inventory.decrStackSize(i, 1);
                    player.inventoryContainer.detectAndSendChanges();
                    return true;
                }
            }
            return false;
        }

        private void handleBoltAttack(EntityPlayer player, Entity entity, World world) {
            if (!world.isRemote) {
                world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.boltGun, SoundCategory.PLAYERS, 1.0F, 1.0F);
                EntityDamageUtil.attackEntityFromIgnoreIFrame(entity, DamageSource.causePlayerDamage(player).setDamageBypassesArmor(), 10F);

                //Who cares about achievements
//                if (!entity.isEntityAlive() && entity instanceof EntityPlayer) {
//                    ((EntityPlayer) entity).addStat(MainRegistry.achGoFish);
//                }

                NBTTagCompound data = createExplosionPacketData();
                sendExplosionPacket(world, entity, data);
            } else {
                playClientAnimation();
            }
        }

        private NBTTagCompound createExplosionPacketData() {
            NBTTagCompound data = new NBTTagCompound();
            data.setString("type", "vanillaExt");
            data.setString("mode", "largeexplode");
            data.setFloat("size", 1F);
            data.setByte("count", (byte) 1);
            return data;
        }

        private void sendExplosionPacket(World world, Entity entity, NBTTagCompound data) {
            PacketDispatcher.wrapper.sendToAllAround(
                    new AuxParticlePacketNT(data, entity.posX, entity.posY + entity.height / 2 - entity.getYOffset(), entity.posZ),
                    new TargetPoint(world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, 50)
            );
        }

        private void playClientAnimation() {
            NBTTagCompound animationData = new NBTTagCompound();
            animationData.setString("type", "anim");
            animationData.setString("mode", "generic");
            MainRegistry.proxy.effectNT(animationData);
        }

        private static final Map<Block, Block> boltMap = new HashMap<>();

        static {
            boltMap.put(Blocks.IRON_BLOCK, Blocks.DIRT);
        }

        @Override
        public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World world, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing side, float fX, float fY, float fZ) {
            Block block = world.getBlockState(pos).getBlock();

            if (!boltMap.containsKey(block)) return EnumActionResult.PASS;

            ItemStack[] bolts = {
                    // TODO
//                    new ItemStack(ModItems.bolt_spike),
//                    Mats.MAT_STEEL.make(ModItems.bolt),
//                    Mats.MAT_TUNGSTEN.make(ModItems.bolt),
//                    Mats.MAT_DURA.make(ModItems.bolt)
                    ModItems.bolt_tungsten.getDefaultInstance(),
                    ModItems.bolt_dura_steel.getDefaultInstance()
            };

            for (ItemStack bolt : bolts) {
                if (consumeBolt(player, bolt)) {
                    if (!world.isRemote) {
                        world.setBlockState(pos, boltMap.get(block).getDefaultState());
                        return EnumActionResult.SUCCESS;
                    }
                }
            }

            return EnumActionResult.PASS;


//            if (block instanceof IToolable && ((IToolable) block).onScrew(world, player, pos.getX(), pos.getY(), pos.getZ(), side, fX, fY, fZ, hand, ToolType.BOLT)) {
//                if (!world.isRemote) {
//                    handleBoltUse(world, player, pos, side, fX, fY, fZ);
//                    return EnumActionResult.SUCCESS;
//                }
//                return EnumActionResult.PASS;
//            }
//
//            return EnumActionResult.FAIL;
        }

        private void handleBoltUse(World world, EntityPlayer player, BlockPos pos, EnumFacing side, float fX, float fY, float fZ) {
            world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.boltGun, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.inventoryContainer.detectAndSendChanges();

            double offset = 0.25;
            NBTTagCompound data = createExplosionPacketData();

            PacketDispatcher.wrapper.sendToAllAround(
                    new AuxParticlePacketNT(data, pos.getX() + fX + side.getXOffset() * offset, pos.getY() + fY + side.getYOffset() * offset, pos.getZ() + fZ + side.getZOffset() * offset),
                    new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50)
            );

            NBTTagCompound animationData = new NBTTagCompound();
            animationData.setString("type", "anim");
            animationData.setString("mode", "generic");
            PacketDispatcher.wrapper.sendTo(new AuxParticlePacketNT(animationData, 0, 0, 0), (EntityPlayerMP) player);
        }

        //TODO: Port the new animation class
        @Override
        @SideOnly(Side.CLIENT)
        public BusAnimation getAnimation(NBTTagCompound data, ItemStack stack) {
            return new BusAnimation()
                    .addBus("RECOIL", new BusAnimationSequence()
                            .addKeyframe(new BusAnimationKeyframe(1, 0, 1, 50))
                            .addKeyframe(new BusAnimationKeyframe(0, 0, 1, 100)));
        }
    }


