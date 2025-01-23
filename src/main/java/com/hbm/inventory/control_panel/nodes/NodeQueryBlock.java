package com.hbm.inventory.control_panel.nodes;

import com.hbm.inventory.control_panel.*;
import com.hbm.main.MainRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Map;

public class NodeQueryBlock extends Node {

    public Control ctrl;

    public String blockPos = "";
    public String dataName = "";
    public NodeDropdown dataSelector;

    public NodeQueryBlock(final float x, final float y, final Control ctrl) {
        super(x, y);
        this.ctrl = ctrl;

        this.outputs.add(new NodeConnection("Output", this, outputs.size(), false, DataValue.DataType.GENERIC, new DataValueFloat(0)));

        final NodeDropdown blockPosSelector = new NodeDropdown(this, otherElements.size(), s -> {
            blockPos = s;
            dataName = "";
            setDataSelector();
            return null;
        }, () -> blockPos);
        this.otherElements.add(blockPosSelector);
        for (final BlockPos pos : ctrl.connectedSet) {
            blockPosSelector.list.addItems(pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
        }

        dataSelector = new NodeDropdown(this, otherElements.size(), s -> {
            dataName = s;
            this.outputs.get(0).type = evaluate(0).getType();
            return null;
        }, () -> dataName);
        setDataSelector();
        this.otherElements.add(dataSelector);

        dataName = "";
        recalcSize();
    }

    private BlockPos getPos(final String pos) {
        final String[] coords = pos.split(", ");
        return new BlockPos(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]));
    }

    private void setDataSelector() {
        dataSelector.list.itemNames.clear();
        if (blockPos != null && !blockPos.isEmpty()) {
            final TileEntity tile = ctrl.panel.parent.getControlWorld().getTileEntity(getPos(blockPos));
            if (tile instanceof IControllable te) {
                for (final Map.Entry<String, DataValue> var : te.getQueryData().entrySet()) {
                    dataSelector.list.addItems(var.getKey());
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(final NBTTagCompound tag, final NodeSystem sys) {
        tag.setString("nodeType", "queryBlock");
        tag.setInteger("controlIdx", sys.parent.panel.controls.indexOf(sys.parent));
        tag.setString("blockPos", blockPos);
        tag.setString("dataName", dataName);

        return super.writeToNBT(tag, sys);
    }

    @Override
    public void readFromNBT(final NBTTagCompound tag, final NodeSystem sys) {
        blockPos = tag.getString("blockPos");
        dataName = tag.getString("dataName");
        super.readFromNBT(tag, sys);
    }

    @Override
    public DataValue evaluate(final int inx) {
        if (!dataName.isEmpty()) {
            final TileEntity tile = ctrl.panel.parent.getControlWorld().getTileEntity(getPos(blockPos));

            if (tile instanceof IControllable te) {
                if (te.getQueryData().containsKey(dataName)) {
                    return te.getQueryData().get(dataName);
                }
            }
            setDataSelector();

        }
        return new DataValueFloat(0);
    }

    public NodeQueryBlock setData(final String blockPos) {
        this.blockPos = blockPos;
        return this;
    }

    @Override
    public NodeType getType() {
        return NodeType.INPUT;
    }

    @Override
    public String getDisplayName() {
        return "Query Block";
    }
}
