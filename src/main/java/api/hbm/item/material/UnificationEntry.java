package api.hbm.item.material;

import api.hbm.material.MaterialPrefix;
import api.hbm.material.NTMMaterial;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UnificationEntry {
    public final MaterialPrefix materialPrefix;

    @Nullable
    public final NTMMaterial material;

    public UnificationEntry(MaterialPrefix prefix, @Nullable NTMMaterial material) {
        this.materialPrefix = prefix;
        this.material = material;
    }

    public UnificationEntry(MaterialPrefix prefix) {
        this.materialPrefix = prefix;
        this.material = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UnificationEntry that = (UnificationEntry) obj;

        if (materialPrefix != that.materialPrefix) return false;
        return Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        int result = materialPrefix.hashCode();
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return materialPrefix.getName() + (material != null ? material.toCamelCaseStrng() : "");
    }
}
