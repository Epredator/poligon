package pl.etroya.corejava.collection;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ComparatorCollectionApi implements Comparator<ComparatorCollectionApi> {
    String label, value;

    public ComparatorCollectionApi(String s, String v) {
        this.label = s;
        this.value = v;
    }


    @Override
    public int compare(ComparatorCollectionApi o1, ComparatorCollectionApi o2) {
        return o1.label.compareToIgnoreCase(o2.label);
    }

    @Override
    public Comparator<ComparatorCollectionApi> reversed() {
        return null;
    }

    @Override
    public Comparator<ComparatorCollectionApi> thenComparing(Comparator<? super ComparatorCollectionApi> other) {
        return null;
    }

    @Override
    public <U> Comparator<ComparatorCollectionApi> thenComparing(Function<? super ComparatorCollectionApi, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<ComparatorCollectionApi> thenComparing(Function<? super ComparatorCollectionApi, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ComparatorCollectionApi> thenComparingInt(ToIntFunction<? super ComparatorCollectionApi> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ComparatorCollectionApi> thenComparingLong(ToLongFunction<? super ComparatorCollectionApi> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<ComparatorCollectionApi> thenComparingDouble(ToDoubleFunction<? super ComparatorCollectionApi> keyExtractor) {
        return null;
    }
}
