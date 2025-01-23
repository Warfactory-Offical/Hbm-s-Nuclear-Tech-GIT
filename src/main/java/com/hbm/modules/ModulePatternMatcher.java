package com.hbm.modules;

import com.hbm.util.ItemStackUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ModulePatternMatcher {
    public static final String MODE_EXACT = "exact";
    public static final String MODE_WILDCARD = "wildcard";
    public String[] modes;

    public ModulePatternMatcher() {
        this.modes = new String[1];
    }

    public ModulePatternMatcher(final int count) {
        this.modes = new String[count];
    }

    public void initPatternSmart(final World world, final ItemStack stack, final int i) {

        if(world.isRemote) return;

        if(stack == null || stack.isEmpty()) {
            modes[i] = null;
            return;
        }

        final List<String> names = ItemStackUtil.getOreDictNames(stack);

        if(iterateAndCheck(names, i ,"ingot")) return;
        if(iterateAndCheck(names, i ,"block")) return;
        if(iterateAndCheck(names, i ,"dust")) return;
        if(iterateAndCheck(names, i ,"nugget")) return;
        if(iterateAndCheck(names, i ,"plate")) return;

        if(stack.getHasSubtypes()) {
            modes[i] = MODE_EXACT;
        } else {
            modes[i] = MODE_WILDCARD;
        }
    }

    private boolean iterateAndCheck(final List<String> names, final int i, final String prefix) {

        for(final String s : names) {
            if(s.startsWith(prefix)) {
                modes[i] = s;
                return true;
            }
        }

        return false;
    }

    public void initPatternStandard(final World world, final ItemStack stack, final int i) {

        if(world.isRemote) return;

        if(stack == null || stack.isEmpty()) {
            modes[i] = null;
            return;
        }

        if(stack.getHasSubtypes()) {
            modes[i] = MODE_EXACT;
        } else {
            modes[i] = MODE_WILDCARD;
        }
    }

    public void nextMode(final World world, final ItemStack pattern, final int i) {

        if(world.isRemote) return;

        if(pattern == null) {
            modes[i] = null;
            return;
        }

        if(modes[i] == null) {
            modes[i] = MODE_EXACT;
        } else if(MODE_EXACT.equals(modes[i])) {
            modes[i] = MODE_WILDCARD;
        } else if(MODE_WILDCARD.equals(modes[i])) {

            final List<String> names = ItemStackUtil.getOreDictNames(pattern);

            if(names.isEmpty()) {
                modes[i] = MODE_EXACT;
            } else {
                modes[i] = names.get(0);
            }
        } else {

            final List<String> names = ItemStackUtil.getOreDictNames(pattern);

            if(names.size() < 2 || modes[i].equals(names.get(names.size() - 1))) {
                modes[i] = MODE_EXACT;
            } else {

                for(int j = 0; j < names.size() - 1; j++) {

                    if(modes[i].equals(names.get(j))) {
                        modes[i] = names.get(j + 1);
                        return;
                    }
                }
            }
        }
    }

    public boolean isValidForFilter(final ItemStack f, final int index, final ItemStack i) {

        String mode = modes[index];
        final ItemStack filter = f.copy();
        filter.setCount(1);
        final ItemStack input = i.copy();
        input.setCount(1);
        
        if(mode == null) {
            modes[index] = mode = MODE_EXACT;
        }

        switch(mode) {
            case MODE_EXACT: return input.isItemEqual(filter) && ItemStack.areItemStackTagsEqual(input, filter);
            case MODE_WILDCARD: return input.getItem() == filter.getItem() && ItemStack.areItemStackTagsEqual(input, filter);
            default:
                final List<String> keys = ItemStackUtil.getOreDictNames(input);
                return keys.contains(mode);
        }
    }

    public void readFromNBT(final NBTTagCompound nbt) {

        for(int i = 0; i < modes.length; i++) {
            if(nbt.hasKey("mode" + i)) {
                modes[i] = nbt.getString("mode" + i);
            } else {
                modes[i] = null;
            }
        }
    }

    public void writeToNBT(final NBTTagCompound nbt) {

        for(int i = 0; i < modes.length; i++) {
            if(modes[i] != null) {
                nbt.setString("mode" + i, modes[i]);
            }
        }
    }
}
