package api.hbm.material.properties;

@FunctionalInterface
public interface IMaterialProperty {
    void verifyProperty(MaterialProperties properties);
}
