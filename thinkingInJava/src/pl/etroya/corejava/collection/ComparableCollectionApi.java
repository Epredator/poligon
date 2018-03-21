package pl.etroya.corejava.collection;

public class ComparableCollectionApi implements Comparable<ComparableCollectionApi> {
    String label, value;

    public ComparableCollectionApi(String s, String v) {
        this.label = s;
        this.value = v;
    }

    public String toString(){
        return label + " | " + value;
    }

    @Override
    public boolean equals(Object o){
        ComparableCollectionApi other = (ComparableCollectionApi) o;
        return value.equalsIgnoreCase(other.value);
    }

    @Override
    public int compareTo(ComparableCollectionApi other) {
        return value.compareToIgnoreCase(other.value);
    }
}
