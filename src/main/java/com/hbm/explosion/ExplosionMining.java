package com.hbm.explosion;

import com.hbm.config.CompatibilityConfig;
import com.hbm.render.amlfrom1710.Vec3;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.*;
import java.util.stream.Collectors;

public class ExplosionMining extends ExplosionNT {

    public static final Set<Block> noDropList = new HashSet<>
            (Arrays.asList(Blocks.DIRT, Blocks.GRASS, Blocks.COBBLESTONE, Blocks.STONE, Blocks.LAVA, Blocks.WATER, Blocks.NETHERRACK, Blocks.END_STONE));
    public static final Set<Block> undergroundBlocks = new HashSet<>
            (Arrays.asList(Blocks.DIRT, Blocks.GRASS, Blocks.COBBLESTONE,
                    Blocks.STONE, Blocks.LAVA, Blocks.WATER, Blocks.SAND,
                    Blocks.CLAY, Blocks.NETHERRACK, Blocks.END_STONE,
                    Blocks.GLOWSTONE, Blocks.GLOWSTONE, Blocks.GRAVEL,
                    Blocks.SOUL_SAND, Blocks.MAGMA));
    private World worldObj;
    private Random explosionRNG = new Random();
    private int radius;
    private BlockPos center;

    public ExplosionMining(World world, Entity exploder, double x, double y, double z, float strength) {
        super(world, exploder, x, y, z, strength);
        worldObj = world;
    }


    @Override
    public void explode() {
        if (CompatibilityConfig.isWarDim(worldObj)) {
            doNTExplosionA();
            doNTExplosionB(false);
        }
    }

    private void doNTExplosionA() {
        float f = this.explosionSize;
        HashSet hashset = new HashSet();
        int i;
        int j;
        int k;
        double d5;
        double d6;
        double d7;
        this.radius = MathHelper.ceil(f);
        this.center = new BlockPos(this.explosionX, this.explosionY, this.explosionZ);

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    double distanceSquared = x * x + y * y + z * z;

                    if (distanceSquared <= (f * f)) {
                        BlockPos pos = center.add(x, y, z);
                        IBlockState blockState = this.worldObj.getBlockState(pos);
                        Block block = blockState.getBlock();

                        if (checkBlastablility(center, pos)) {
                            hashset.add(pos);
                        }
                    }
                }
            }
        }

        this.affectedBlockPositions.addAll(hashset);


        this.explosionSize *= 2.0F;
        i = MathHelper.floor(this.explosionX - (double) this.explosionSize - 1.0D);
        j = MathHelper.floor(this.explosionX + (double) this.explosionSize + 1.0D);
        k = MathHelper.floor(this.explosionY - (double) this.explosionSize - 1.0D);
        int i2 = MathHelper.floor(this.explosionY + (double) this.explosionSize + 1.0D);
        int l = MathHelper.floor(this.explosionZ - (double) this.explosionSize - 1.0D);
        int j2 = MathHelper.floor(this.explosionZ + (double) this.explosionSize + 1.0D);
        List<Entity> entityList = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB((double) i, (double) k, (double) l, (double) j, (double) i2, (double) j2));
        List<Entity> list = entityList.stream().filter(entity -> !(entity instanceof EntityItem)).collect(Collectors.toList());
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, this.explosionSize);
        Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);


        for (int i1 = 0; i1 < list.size(); ++i1) {
            Entity entity = (Entity) list.get(i1);
            double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double) this.explosionSize;

            if (d4 <= 1.0D) {
                d5 = entity.posX - this.explosionX;
                d6 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
                d7 = entity.posZ - this.explosionZ;
                double d9 = (double) MathHelper.sqrt(d5 * d5 + d6 * d6 + d7 * d7);

                if (d9 != 0.0D) {
                    d5 /= d9;
                    d6 /= d9;
                    d7 /= d9;
                    double d10 = (double) this.worldObj.getBlockDensity(new Vec3d(vec3.xCoord, vec3.yCoord, vec3.zCoord), entity.getEntityBoundingBox());
                    double d11 = (1.0D - d4) * d10;
                    entity.attackEntityFrom(DamageSource.causeExplosionDamage(this), (float) ((int) ((d11 * d11 + d11) / 2.0D * 8.0D * (double) this.explosionSize + 1.0D)));
                    double d8 = d11;
                    if (entity instanceof EntityLivingBase)
                        d8 = EnchantmentProtection.getBlastDamageReduction((EntityLivingBase) entity, d11);
                    entity.motionX += d5 * d8;
                    entity.motionY += d6 * d8;
                    entity.motionZ += d7 * d8;

                    if (entity instanceof EntityPlayer) {
                        this.affectedEntities.put((EntityPlayer) entity, Vec3.createVectorHelper(d5 * d11, d6 * d11, d7 * d11));
                    }
                }
            }
        }

        this.explosionSize = f;
    }

    private void doNTExplosionB(boolean p_77279_1_) {

        List<BlockPos> dropPoints = new ArrayList<>();
        int dropPointsCount = 3; // Hardcoding is fine for this
        double dropRadius = Math.ceil(radius / 2);

        // Generate random drop points within the explosion radius
        for (int n = 0; n < dropPointsCount; n++) {
            double dx = (Math.random() * 2.0 - 1.0) * dropRadius;
            double dy = (Math.random() * 2.0 - 1.0) * dropRadius;
            double dz = (Math.random() * 2.0 - 1.0) * dropRadius;
            BlockPos randomDropPos = center.add(dx, dy, dz);
            dropPoints.add(randomDropPos);
        }

        Map<BlockPos, List<ItemStack>> dropsMap = new HashMap<>();
        for (BlockPos dropPoint : dropPoints) {
            dropsMap.put(dropPoint, new ArrayList<>());
        }

        this.worldObj.playSound(null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

        if (this.explosionSize >= 2.0F) {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
        } else {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D);
        }


        Iterator<BlockPos> iterator;
        BlockPos chunkposition;
        int i;
        int j;
        int k;
        IBlockState block;

        iterator = this.affectedBlockPositions.iterator();

        while (iterator.hasNext()) {
            chunkposition = iterator.next();
            i = chunkposition.getX();
            j = chunkposition.getY();
            k = chunkposition.getZ();
            block = this.worldObj.getBlockState(chunkposition);

            double d0 = (double) ((float) i + this.worldObj.rand.nextFloat());
            double d1 = (double) ((float) j + this.worldObj.rand.nextFloat());
            double d2 = (double) ((float) k + this.worldObj.rand.nextFloat());
            double d3 = d0 - this.explosionX;
            double d4 = d1 - this.explosionY;
            double d5 = d2 - this.explosionZ;
            double d6 = (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            double d7 = 0.5D / (d6 / (double) this.explosionSize + 0.1D);
            d7 *= (double) (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
            d3 *= d7;
            d4 *= d7;
            d5 *= d7;
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.explosionX * 1.0D) / 2.0D, (d1 + this.explosionY * 1.0D) / 2.0D, (d2 + this.explosionZ * 1.0D) / 2.0D, d3, d4, d5);
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5);


            if (block.getMaterial() != Material.AIR) {
                if (block.getBlock().canDropFromExplosion(this) && !noDropList.contains(block.getBlock())) {


                    List<ItemStack> drops = block.getBlock().getDrops(worldObj, chunkposition, this.worldObj.getBlockState(chunkposition), 0);

                    // Assign drops to a random drop point
                    BlockPos randomDropPoint = dropPoints.get(worldObj.rand.nextInt(dropPoints.size()));
                    dropsMap.get(randomDropPoint).addAll(drops);  // Add drops to the selected drop point

                }

                block.getBlock().onBlockExploded(this.worldObj, chunkposition, this);
            }
        }

        for (Map.Entry<BlockPos, List<ItemStack>> entry : dropsMap.entrySet()) {
            BlockPos dropPos = entry.getKey();
            List<ItemStack> itemStacks = entry.getValue();

            for (ItemStack stack : itemStacks) {
                if (!stack.isEmpty()) {
                    EntityItem itemEntity = new EntityItem(worldObj, dropPos.getX() + 0.5, dropPos.getY() + 0.5, dropPos.getZ() + 0.5, stack);
                    worldObj.spawnEntity(itemEntity);
                }
            }
        }

    }

    public Map getAffectedEntites() {
        return this.affectedEntities;
    }

    public EntityLivingBase getExplosivePlacedBy() {
        return this.exploder == null ? null : (this.exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed) this.exploder).getTntPlacedBy() : (this.exploder instanceof EntityLivingBase ? (EntityLivingBase) this.exploder : null));
    }

    // unconventional name, sure, but it's short
    public boolean has(ExAttrib attrib) {
        return this.atttributes.contains(attrib);
    }


    private boolean checkBlastablility(BlockPos start, BlockPos target) {
        Vec3d startVec = new Vec3d(start.getX() + 0.5, start.getY() + 0.5, start.getZ() + 0.5);
        Vec3d targetVec = new Vec3d(target.getX() + 0.5, target.getY() + 0.5, target.getZ() + 0.5);
        Vec3d direction = targetVec.subtract(startVec).normalize();
        double maxDistance = startVec.distanceTo(targetVec);

        for (double d = 0; d <= maxDistance; d += 0.5) {
            Vec3d currentPosVec = startVec.add(direction.scale(d));
            BlockPos currentPos = new BlockPos(currentPosVec.x, currentPosVec.y, currentPosVec.z);
            IBlockState currentState = this.worldObj.getBlockState(currentPos);
            Block currentBlock = currentState.getBlock();

            // Checks if block is underground OR has translation key containing ore, will not apply checks for your random stone crap
            if (undergroundBlocks.contains(currentBlock) || currentBlock.getTranslationKey().contains("ore")) {
                continue;
            }

            if (currentState.getMaterial() != Material.AIR) {
                float resistance = this.exploder != null
                        ? this.exploder.getExplosionResistance(this, this.worldObj, currentPos, currentState)
                        : currentBlock.getExplosionResistance(this.worldObj, currentPos, (Entity) null, this);

                //Should keep bricks intact
                if (currentBlock.getTranslationKey().toLowerCase().contains("brick") || currentBlock.getTranslationKey().toLowerCase().contains("concrete"))
                    return false;
                // For some reason resistance is divided by 5...? idk why
                if ((resistance * 5f) >= 6.0F) {
                    return false;
                }
            }
        }

        return true;
    }


}