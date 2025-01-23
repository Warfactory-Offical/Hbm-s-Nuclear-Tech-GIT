package com.hbm.inventory.control_panel.controls;

public enum ControlType {
    BUTTON("Button"),
    SWITCH("Switch"),
    SLIDER("Slider"),
    KNOB("Knob"),
    DIAL("Dial"),
    DISPLAY("Display"),
    INDICATOR("Indicator"),
    METER("Meter"),
    LABEL("Label");

    public String name;
    ControlType(final String name){
        this.name = name;
    }

    public static ControlType getByName(final String name){
        for(final ControlType o : values()){
            if(o.name.equals(name)){
                return o;
            }
        }
        return null;
    }
}
