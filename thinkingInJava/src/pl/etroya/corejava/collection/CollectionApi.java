package pl.etroya.corejava.collection;

public class CollectionApi {
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    String label, value;

    public CollectionApi(String label, String value){
        //assign label and value to member fields
    }

    public boolean equals(Object o){
        CollectionApi other = (CollectionApi) o;
        return value.equalsIgnoreCase(other.value);

    }
}
