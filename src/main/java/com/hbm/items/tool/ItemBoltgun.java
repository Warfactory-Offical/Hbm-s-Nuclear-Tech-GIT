package com.hbm.items.tool;


import com.hbm.items.IAnimatedItem;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.main.MainRegistry;
import com.hbm.packet.AuxParticlePacketNT;
import com.hbm.packet.PacketDispatcher;
import com.hbm.render.anim.BusAnimation;
import com.hbm.render.anim.BusAnimationKeyframe;
import com.hbm.render.anim.BusAnimationSequence;
import com.hbm.util.EntityDamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

    public class ItemBoltgun extends Item implements IAnimatedItem {

        public ItemBoltgun() {
            this.setMaxStackSize(1);
            this.setCreativeTab(MainRegistry.controlTab);
            //ToolType.BOLT.register(new ItemStack(this));
        }

//        @Override
//        public Item setUnlocalizedName(String unlocalizedName) {
//            super.setTranslationKey(unlocalizedName);
//            this.setRegistryName(RefStrings.MODID, unlocalizedName);
//            return this;
//        }

//        @Override
//        public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
//            if (!entity.isEntityAlive()) return false;
//            World world = player.world;
//
//            ItemStack[] bolts = {
//                    new ItemStack(ModItems.bolt_spike),
//                    Mats.MAT_STEEL.make(ModItems.bolt),
//                    Mats.MAT_TUNGSTEN.make(ModItems.bolt),
//                    Mats.MAT_DURA.make(ModItems.bolt)
//            };
//
//            for (ItemStack bolt : bolts) {
//                if (consumeBolt(player, bolt)) {
//                    handleBoltAttack(player, entity, world);
//                    return true;
//                }
//            }
//            return false;
//        }

        private boolean consumeBolt(final EntityPlayer player, final ItemStack bolt) {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                final ItemStack slot = player.inventory.getStackInSlot(i);
                if (slot != null && slot.getItem() == bolt.getItem() && slot.getItemDamage() == bolt.getItemDamage()) {
                    player.inventory.decrStackSize(i, 1);
                    player.inventoryContainer.detectAndSendChanges();
                    return true;
                }
            }
            return false;
        }

        private void handleBoltAttack(final EntityPlayer player, final Entity entity, final World world) {
            if (!world.isRemote) {
                world.playSound(null, entity.posX, entity.posY, entity.posZ, HBMSoundHandler.boltGun, SoundCategory.PLAYERS, 1.0F, 1.0F);
                EntityDamageUtil.attackEntityFromIgnoreIFrame(entity, DamageSource.causePlayerDamage(player).setDamageBypassesArmor(), 10F);

                //Who cares about achivements
//                if (!entity.isEntityAlive() && entity instanceof EntityPlayer) {
//                    ((EntityPlayer) entity).addStat(MainRegistry.achGoFish);
//                }

                final NBTTagCompound data = createExplosionPacketData();
                sendExplosionPacket(world, entity, data);
            } else {
                playClientAnimation();
            }
        }

        private NBTTagCompound createExplosionPacketData() {
            final NBTTagCompound data = new NBTTagCompound();
            data.setString("type", "vanillaExt");
            data.setString("mode", "largeexplode");
            data.setFloat("size", 1F);
            data.setByte("count", (byte) 1);
            return data;
        }

        private void sendExplosionPacket(final World world, final Entity entity, final NBTTagCompound data) {
            PacketDispatcher.wrapper.sendToAllAround(
                    new AuxParticlePacketNT(data, entity.posX, entity.posY + entity.height / 2 - entity.getYOffset(), entity.posZ),
                    new TargetPoint(world.provider.getDimension(), entity.posX, entity.posY, entity.posZ, 50)
            );
        }

        private void playClientAnimation() {
            final NBTTagCompound animationData = new NBTTagCompound();
            animationData.setString("type", "anim");
            animationData.setString("mode", "generic");
            MainRegistry.proxy.effectNT(animationData);
        }

//        @Override
//        public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float fX, float fY, float fZ) {
//            Block block = world.getBlockState(pos).getBlock();
//
//            if (block instanceof IToolable && ((IToolable) block).onScrew(world, player, pos, side, fX, fY, fZ, ToolType.BOLT)) {
//                if (!world.isRemote) {
//                    handleBoltUse(world, player, pos, side, fX, fY, fZ);
//                }
//                return false;
//            }
//
//            return false;
//        }

        private void handleBoltUse(final World world, final EntityPlayer player, final BlockPos pos, final EnumFacing side, final float fX, final float fY, final float fZ) {
            world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.boltGun, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.inventoryContainer.detectAndSendChanges();

            final double offset = 0.25;
            final NBTTagCompound data = createExplosionPacketData();

            PacketDispatcher.wrapper.sendToAllAround(
                    new AuxParticlePacketNT(data, pos.getX() + fX + side.getXOffset() * offset, pos.getY() + fY + side.getYOffset() * offset, pos.getZ() + fZ + side.getZOffset() * offset),
                    new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 50)
            );

            final NBTTagCompound animationData = new NBTTagCompound();
            animationData.setString("type", "anim");
            animationData.setString("mode", "generic");
            PacketDispatcher.wrapper.sendTo(new AuxParticlePacketNT(animationData, 0, 0, 0), (EntityPlayerMP) player);
        }

        //TODO: Port the new animation class
        @Override
        @SideOnly(Side.CLIENT)
        public BusAnimation getAnimation(final NBTTagCompound data, final ItemStack stack) {
            return new BusAnimation()
                    .addBus("RECOIL", new BusAnimationSequence()
                            .addKeyframe(new BusAnimationKeyframe(1, 0, 1, 50))
                            .addKeyframe(new BusAnimationKeyframe(0, 0, 1, 100)));
        }
    }


