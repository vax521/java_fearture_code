package custom_concurrent_class.custom_stream_creator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class MySpliterator implements Spliterator<Item> {
    @Override
    public boolean tryAdvance(Consumer<? super Item> action) {
        return false;
    }

    @Override
    public Spliterator<Item> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
