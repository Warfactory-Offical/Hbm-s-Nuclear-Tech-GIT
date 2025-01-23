package com.hbm.inventory.control_panel.controls.configs;

import com.hbm.inventory.control_panel.DataValue;
import com.hbm.inventory.control_panel.DataValueFloat;
import com.hbm.inventory.control_panel.DataValueString;
import com.hbm.inventory.control_panel.GuiControlEdit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

import java.util.HashMap;
import java.util.Map;

public class SubElementDialSquare extends SubElementBaseConfig {

    private String text;

    GuiTextField textField;

    public SubElementDialSquare(final GuiControlEdit gui, final Map<String, DataValue> map) {
        super(gui);
        this.text = map.get("label").toString();
    }

    @Override
    public Map<String, DataValue> getConfigs() {
        final Map<String, DataValue> m = new HashMap<>();
        m.put("label", new DataValueString(text));
        return m;
    }

    @Override
    public void initGui() {
        final int cX = gui.width/2;
        final int cY = gui.height/2;

        textField = new GuiTextField(gui.currentButtonId(), Minecraft.getMinecraft().fontRenderer, cX-10, gui.getGuiTop()+70, 120, 20);
        textField.setText(text);

        super.initGui();
    }

    @Override
    protected void update() {
        super.update();
        textField.updateCursorCounter();
        text = textField.getText();
    }

    @Override
    protected void drawScreen() {
        textField.drawTextBox();
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int button) {
        super.mouseClicked(mouseX, mouseY, button);
        this.textField.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void keyTyped(final char typedChar, final int keyCode) {
        super.keyTyped(typedChar, keyCode);
        this.textField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    public void enableButtons(final boolean enable) {
        textField.setEnabled(enable);
        textField.setVisible(enable);
    }
}
