package api.hbm.material;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NTMMaterialFlag {

    private final String name;

    private final Set<NTMMaterialFlag> requiredFlags;
    private final Set<PropertyKey<?>> requiredProperties;

    private NTMMaterialFlag(String name, Set<NTMMaterialFlag> requiredFlags, Set<PropertyKey<?>> requiredProperties) {
        this.name = name;
        this.requiredFlags = requiredFlags;
        this.requiredProperties = requiredProperties;
        FLAG_REGISTRY.add(this);
    }

    public static NTMMaterialFlag getByName(String name) {
        return FLAG_REGISTRY.stream().filter(f -> f.toString().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    protected Set<NTMMaterialFlag> verifyFlag(Material material) {
        requiredProperties.forEach(key -> {
            if (!material.hasProperty(key)) {
                GTLog.logger.warn("Material {} does not have required property {} for flag {}!",
                        material.getUnlocalizedName(), key.toString(), this.name);
            }
        });

        Set<NTMMaterialFlag> thisAndDependencies = new HashSet<>(requiredFlags);
        thisAndDependencies.addAll(requiredFlags.stream()
                .map(f -> f.verifyFlag(material))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet()));

        return thisAndDependencies;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NTMMaterialFlag that = (NTMMaterialFlag) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static class Builder {

        final String name;

        final Set<NTMMaterialFlag> requiredFlags = new ObjectOpenHashSet<>();
        final Set<PropertyKey<?>> requiredProperties = new ObjectOpenHashSet<>();

        public Builder(String name) {
            this.name = name;
        }

        public Builder requireFlags(NTMMaterialFlag... flags) {
            requiredFlags.addAll(Arrays.asList(flags));
            return this;
        }

        public Builder requireProps(PropertyKey<?>... propertyKeys) {
            requiredProperties.addAll(Arrays.asList(propertyKeys));
            return this;
        }

        public NTMMaterialFlag build() {
            return new NTMMaterialFlag(name, requiredFlags, requiredProperties);
        }
    }
}