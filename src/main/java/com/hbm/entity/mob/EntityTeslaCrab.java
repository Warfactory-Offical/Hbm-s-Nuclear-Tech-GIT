package com.hbm.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.hbm.items.ModItems;
import com.hbm.items.meta.materials.MaterialMineral;
import com.hbm.tileentity.machine.TileEntityTesla;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTeslaCrab extends EntityCyberCrab {
	
	public List<double[]> targets = new ArrayList<double[]>();

	public EntityTeslaCrab(final World p_i1733_1_) {
		super(p_i1733_1_);
        this.setSize(0.75F, 1.25F);
        this.ignoreFrustumCheck = true;
	}

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5F);
    }
    
    public void onLivingUpdate() {
    	
    	targets = TileEntityTesla.zap(world, posX, posY + 1, posZ, 3, this);
    	
        super.onLivingUpdate();
    }

	protected ItemStack getDropItemStack(){
        return ModItems.wire.getItemStack(MaterialMineral.ADVANCED_ALLOY);
    }

    protected void dropRareDrop(final int p_70600_1_) {
    	this.dropItem(ModItems.coil_copper, 1);
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        ItemStack item = this.getDropItemStack();

        if (item != null)
        {
            int i = this.rand.nextInt(3);

            if (lootingModifier > 0)
            {
                i += this.rand.nextInt(lootingModifier + 1);
            }

            for (int j = 0; j < i; ++j)
            {
                this.dropItem(item, 1);
            }
        }
    }

    public EntityItem dropItem(ItemStack itemIn, int size)
    {
        return this.entityDropItem(new ItemStack(itemIn.getItem(), itemIn.getMetadata(), size), 0);
    }
}