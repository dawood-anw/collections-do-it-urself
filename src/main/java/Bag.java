import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Bag<T> {

    public static void main(String[] args) {

    }

    private Map<T, Integer> backingMap = new HashMap<>();
    private int size = 0;

    public Bag(T... elements) {
        for(T t : elements) {
            this.backingMap.merge(t, 1, (existingCount, e) -> existingCount + 1);
            this.size++;
        }
    }

    public int getOccurences(T element) {
        return this.backingMap.getOrDefault(element, 0);
    }

    public int addOccurence(T element) {
        return addOccurence(element, 1);
    }

    public int addOccurence(T element, int occurences) {
        backingMap.merge(element, occurences, (existingCount, e) -> existingCount + occurences);
        this.size = this.size + occurences;
        return this.getOccurences(element);
    }

    public boolean removeOccurences(T element) {
        return removeOccurences(element, 1);
    }

    public boolean removeOccurences(T element, int occurences) {
        int existing = this.getOccurences(element);
        if(existing > 0) {
            if(existing > occurences) {
                this.backingMap.put(element, existing - occurences);
                this.size = this.size - occurences;
                return true;
            } else {
                this.backingMap.remove(element);
                this.size = this.size - existing;
            }
        }
        return false;
    }

    public void forEachWithOccurences(BiConsumer<T, Integer> biConsumer) {
        this.backingMap.forEach(biConsumer);
    }

    public void forEach(Consumer<T> consumer) {
        this.backingMap.forEach((element, count) -> {
            consumer.accept(element);
        });
    }
}
