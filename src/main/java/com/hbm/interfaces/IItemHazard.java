package com.hbm.interfaces;

import com.hbm.modules.ItemHazardModule;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IItemHazard {
	
	public ItemHazardModule getModule();
	
	public default IItemHazard addRadiation(final float radiation) {
		this.getModule().addRadiation(radiation);
		return this;
	}
	
	public default IItemHazard addDigamma(final float digamma) {
		this.getModule().addDigamma(digamma);
		return this;
	}
	
	public default IItemHazard addFire(final int fire) {
		this.getModule().addFire(fire);
		return this;
	}

	public default IItemHazard addCryogenic(final int fire) {
		this.getModule().addCryogenic(fire);
		return this;
	}

	public default IItemHazard addToxic(final int fire) {
		this.getModule().addToxic(fire);
		return this;
	}
	
	public default IItemHazard addAsbestos(final int asbestos) {
		this.getModule().addAsbestos(asbestos);
		return this;
	}
	
	public default IItemHazard addCoal(final int coal) {
		this.getModule().addCoal(coal);
		return this;
	}
	
	public default IItemHazard addBlinding() {
		this.getModule().addBlinding();
		return this;
	}
	
	public default IItemHazard addHydroReactivity() {
		this.getModule().addHydroReactivity();
		return this;
	}
	
	public default IItemHazard addExplosive(final float bang) {
		this.getModule().addExplosive(bang);
		return this;
	}

	public default boolean isRadioactive(){
		return this.getModule().isRadioactive();
	}
	
	//the only ugly part of this entire system is the manual casting so that the rest of the daisychained setters work
	public default Item toItem() {
		return (Item)this;
	}
	
	public default Block toBlock() {
		return (Block)this;
	}
}