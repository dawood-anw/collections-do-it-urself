import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Multimap<K, V> {
    protected Map<K, Collection<V>> backingMap = new HashMap<>();

    protected abstract Collection<V> getEmptyBackingCollection();

    public Collection<V> get(K key) {
        return this.backingMap.getOrDefault(key, this.getEmptyBackingCollection());
    }

    public boolean put(K key, V value) {
        Collection<V> existing = this.backingMap.get(key);
        if(existing == null) {
            existing = this.getEmptyBackingCollection();
            this.backingMap.put(key, existing);
        }
        boolean add = existing.add(value);
        return add;
    }

    public Collection<V> putAll(K key, Iterable<V> values) {
        for(V value : values) {
            this.put(key, value);
        }
        return this.get(key);
    }

    public boolean removeAll(K key, V value) {
        Collection<V> existing = this.backingMap.get(key);
        if(!existing.isEmpty()) {
            boolean remove = existing.remove(value);
            int size = existing.size();
            if(size == 0) {
                this.backingMap.remove(key);
            }
            return remove;
        }
        return false;
    }
}
