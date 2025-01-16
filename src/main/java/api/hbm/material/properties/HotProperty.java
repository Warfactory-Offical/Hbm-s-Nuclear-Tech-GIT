package api.hbm.material.properties;

import com.hbm.hazard.*;

public class HotProperty implements IMaterialProperty {

    int time; //Time

    public HotProperty(int time) {
        this.time = time;
        HazardSystem.register(,  HazardRegistry.makedata(HazardRegistry.HOT,  time));
    }

    @Override

    public void verifyProperty(MaterialProperties properties) {

    }
}
