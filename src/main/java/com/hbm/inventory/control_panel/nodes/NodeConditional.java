package com.hbm.inventory.control_panel.nodes;

import com.hbm.inventory.control_panel.*;
import net.minecraft.nbt.NBTTagCompound;

public class NodeConditional extends Node {

    public NodeConditional(final float x, final float y) {
        super(x, y);

        this.inputs.add(new NodeConnection("Enable", this, inputs.size(), true, DataValue.DataType.NUMBER, new DataValueFloat(0)));
        this.inputs.add(new NodeConnection("Input", this, inputs.size(), true, DataValue.DataType.GENERIC, new DataValueFloat(0)));
        this.inputs.add(new NodeConnection("Default", this, inputs.size(), true, DataValue.DataType.GENERIC, new DataValueFloat(0)));
        this.outputs.add(new NodeConnection("Output", this, outputs.size(), false, DataValue.DataType.GENERIC, new DataValueFloat(0)));
        evalCache = new DataValue[1];
        recalcSize();
    }

    @Override
    public NodeType getType() {
        return NodeType.LOGIC;
    }

    @Override
    public String getDisplayName() {
        return "Conditional";
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound tag, final NodeSystem sys) {
        tag.setString("nodeType", "conditional");
        return super.writeToNBT(tag, sys);
    }

    @Override
    public DataValue evaluate(final int idx) {
        if (cacheValid)
            return evalCache[0];
        cacheValid = true;

        final DataValue enable = inputs.get(0).evaluate();
        final DataValue in = inputs.get(1).evaluate();
        final DataValue defaul = inputs.get(2).evaluate();
        if (enable == null || in == null || defaul == null)
            return null;

        if (enable.getBoolean()) {
            return evalCache[0] = in;
        }

        return evalCache[0] = defaul;
    }
}
