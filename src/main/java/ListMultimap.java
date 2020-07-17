import java.util.ArrayList;
import java.util.Collection;

public class ListMultimap<K, V> extends Multimap<K, V> {

    public ListMultimap() {}

    @Override
    protected Collection<V> getEmptyBackingCollection() {
        return new ArrayList<>();
    }
}
