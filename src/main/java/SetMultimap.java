import java.util.Collection;
import java.util.HashSet;

public class SetMultimap <K, V> extends Multimap<K, V> {

    public SetMultimap() {}

    @Override
    protected Collection<V> getEmptyBackingCollection() {
        return new HashSet<>();
    }
}
