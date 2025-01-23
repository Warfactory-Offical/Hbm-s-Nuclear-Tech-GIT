package com.hbm.inventory.control_panel.controls.configs;

import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.inventory.control_panel.GuiControlEdit;
import net.minecraftforge.fml.client.config.GuiSlider;

import java.util.HashMap;
import java.util.Map;

public class SubElementKnobControl extends SubElementBaseConfig {

    private int positions;

    GuiSlider slidePositions;

    public SubElementKnobControl(final GuiControlEdit gui, final Map<String, DataValue> map) {
        super(gui);
        this.positions = (int) map.get("positions").getNumber();
    }

    @Override
    public void initGui() {
        final int cX = gui.width/2;
        slidePositions = gui.addButton(new GuiSlider(gui.currentButtonId(), cX-85, gui.getGuiTop()+70, 80, 15, "Positions ", "", 1, 11, positions, false, true));
        super.initGui();
    }

    @Override
    public Map<String, DataValue> getConfigs() {
        final Map<String, DataValue> m = new HashMap<>();
        m.put("positions", new DataValueFloat(positions));
        return m;
    }

    @Override
    public void mouseReleased(final int mX, final int mY, final int state) {
        positions = slidePositions.getValueInt();
    }

    @Override
    public void enableButtons(final boolean enable) {
        slidePositions.visible = enable;
        slidePositions.enabled = enable;
    }
}
