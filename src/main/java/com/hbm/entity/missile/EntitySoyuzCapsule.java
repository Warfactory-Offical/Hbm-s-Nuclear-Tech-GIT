package com.hbm.entity.missile;
import com.hbm.util.ItemStackUtil;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.tileentity.machine.TileEntitySoyuzCapsule;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySoyuzCapsule extends EntityThrowable {

	public int soyuz;
	public ItemStack[] payload = new ItemStack[18];

	public EntitySoyuzCapsule(final World p_i1582_1_) {
		super(p_i1582_1_);
		this.ignoreFrustumCheck = true;
		this.isImmuneToFire = true;
	}
	
	@Override
	public void onUpdate() {

		this.lastTickPosX = this.prevPosX = posX;
		this.lastTickPosY = this.prevPosY = posY;
		this.lastTickPosZ = this.prevPosZ = posZ;
		this.setPosition(posX + this.motionX, posY + this.motionY, posZ + this.motionZ);
		
		if(this.motionY > -0.2)
			this.motionY -= 0.02;
		
		if(posY > 600)
			posY = 600;
        
        if(this.world.getBlockState(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ)).getBlock() != Blocks.AIR) {
        	
    		this.setDead();
    		
    		if(!world.isRemote) {
    			world.setBlockState(new BlockPos((int)(this.posX), (int)(this.posY + 1), (int)(this.posZ)), ModBlocks.soyuz_capsule.getDefaultState());
    			
    			final TileEntitySoyuzCapsule capsule = (TileEntitySoyuzCapsule)world.getTileEntity(new BlockPos((int)(this.posX), (int)(this.posY + 1), (int)(this.posZ)));
    			if(capsule != null) {
    				
    				for(int i = 0; i < payload.length; i++) {
    					capsule.inventory.setStackInSlot(i, payload[i] == null ? ItemStack.EMPTY : payload[i]);
    				}
    			}
    			ItemStack stack = ItemStack.EMPTY;
    			switch(soyuz){
    			case 0:
    				stack = ItemStackUtil.itemStackFrom(ModItems.missile_soyuz0);
    				break;
    			case 1:
    				stack = ItemStackUtil.itemStackFrom(ModItems.missile_soyuz1);
    				break;
    			case 2:
    				stack = ItemStackUtil.itemStackFrom(ModItems.missile_soyuz2);
    				break;
    			}
    			capsule.inventory.setStackInSlot(18, stack);
    		}
        }
    }

	@Override
	protected void onImpact(final RayTraceResult p_70184_1_) {
		
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(final double distance)
    {
        return distance < 500000;
    }

	@Override
	public void readEntityFromNBT(final NBTTagCompound nbt) {

		final NBTTagList list = nbt.getTagList("items", 10);
		
		soyuz = nbt.getInteger("soyuz");

		for (int i = 0; i < list.tagCount(); i++) {
			final NBTTagCompound nbt1 = list.getCompoundTagAt(i);
			final byte b0 = nbt1.getByte("slot");
			if (b0 >= 0 && b0 < payload.length) {
				payload[b0] = ItemStackUtil.itemStackFrom(nbt1);
			}
		}
	}

	@Override
	public void writeEntityToNBT(final NBTTagCompound nbt) {

		final NBTTagList list = new NBTTagList();
		
		nbt.setInteger("soyuz", soyuz);

		for (int i = 0; i < payload.length; i++) {
			if (payload[i] != null) {
				final NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("slot", (byte) i);
				payload[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		nbt.setTag("items", list);
	}
}
