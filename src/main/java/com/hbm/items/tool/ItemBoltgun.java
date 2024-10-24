package com.hbm.items.tool;

import com.hbm.blocks.ModBlocks;
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

import api.hbm.block.IToolable.ToolType;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
            boltMap.put(ModBlocks.watz_casing, ModBlocks.watz_casing_bolted);
        }

        @Override
        public @NotNull EnumActionResult onItemUse(@NotNull EntityPlayer player, World world, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing side, float fX, float fY, float fZ) {
            Block block = world.getBlockState(pos).getBlock();

            if (!boltMap.containsKey(block)) return EnumActionResult.PASS;

            ItemStack[] bolts = { // TODO: fix
                    ModItems.bolt_tungsten,
                    ModItems.bolt_dura_steel
            };

            for (ItemStack bolt : bolts) {
                if (consumeBolt(player, bolt)) {
                    world.setBlockState(pos, boltMap.get(block).getDefaultState());
                    handleBoltUse(world, player, pos, side, fX, fY, fZ);
                    return EnumActionResult.PASS;
                }
            }

            return EnumActionResult.PASS;

        }



    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull EntityPlayer player, Entity entity) {
        if (!entity.isEntityAlive()) return false;
        World world = player.world;

        ItemStack[] bolts = {
                // new ItemStack(ModItems.bolt_spike), // TODO
                // Mats.MAT_STEEL.make(MaterialShapes.BOLT),
                // Mats.MAT_TUNGSTEN.make(MaterialShapes.BOLT),
                // Mats.MAT_DURASTEELSTEEL.make(MaterialShapes.BOLT)
                ModItems.bolt_tungsten,
                ModItems.bolt_dura_steel
        };

        for (ItemStack bolt : bolts) {
            if (consumeBolt(player, bolt)) {
                handleBoltAttack(player, entity, world);
                return true;
            }
        }
        return false;
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

            if (player instanceof EntityPlayerMP playerMP) {
                NBTTagCompound animationData = new NBTTagCompound();
                animationData.setString("type", "anim");
                animationData.setString("mode", "generic");
                PacketDispatcher.wrapper.sendTo(new AuxParticlePacketNT(animationData, 0, 0, 0), playerMP);
            } else {
                // TODO: handle that
                MainRegistry.logger.warn("Player {} not an instance of EntityPlayerMP, not sending packet", player.getName());
            }
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


