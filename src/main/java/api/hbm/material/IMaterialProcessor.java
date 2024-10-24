package api.hbm.material;

@FunctionalInterface
public interface IMaterialProcessor {
    void processMaterial(NTMMaterial material, MaterialPrefix prefix);
}
