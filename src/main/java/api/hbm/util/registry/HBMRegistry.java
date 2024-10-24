package api.hbm.util.registry;

import net.minecraft.util.registry.RegistryNamespaced;
import org.jetbrains.annotations.NotNull;

public class HBMRegistry<K, V> extends RegistryNamespaced<K, V> {
    protected boolean frozen = true;
    protected final int maxId;

    public HBMRegistry(int maxId) {
        this.maxId = maxId;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void freeze() {
        if (frozen) {
            throw new IllegalStateException("Registry is already frozen!");
        }

        this.frozen = true;
    }

    public void unfreeze() {
        if (!frozen) {
            throw new IllegalStateException("Registry is already unfrozen!");
        }

        this.frozen = false;
    }

    public void register(int id, @NotNull K key, @NotNull V value) {
        if (id < 0 || id >= maxId) {
            throw new IndexOutOfBoundsException("Id is out of range:" + id);
        }

        super.putObject(key, value);

        V objectWithId = getObjectById(id);
        if (objectWithId != null) {
            throw new IllegalArgumentException(String.format("Tried to assign id %d to %s (%s), but it is already assigned to %s (%s)!", id, value ,key, objectWithId, getNameForObject(value)));
        }
        underlyingIntegerMap.put(value, id);
    }

    @Override
    public void putObject(K key, V value) {
        throw new UnsupportedOperationException("Use #register(int, String, T)");
    }

    public int getIdByObjectName(K key) {
        V valueWithKey = getObject(key);
        return valueWithKey == null ? 0 : getIDForObject(valueWithKey);
    }
}
