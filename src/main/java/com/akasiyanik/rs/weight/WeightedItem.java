package com.akasiyanik.rs.weight;

/**
 * @author akasiyanik
 *         12/23/16
 */
public class WeightedItem<K> implements Comparable<WeightedItem<K>> {

    private K item;

    private Double weight;

    public WeightedItem(K item, Double weight) {
        this.item = item;
        this.weight = weight;
    }

    public K getItem() {
        return item;
    }

    public void setItem(K item) {
        this.item = item;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "[" + item + ", " + weight + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WeightedItem<?> weightedItem = (WeightedItem<?>) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(item, weightedItem.item)
                .append(weight, weightedItem.weight)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(item)
                .append(weight)
                .toHashCode();
    }

    @Override
    public int compareTo(WeightedItem<K> other) {
        return this.weight.compareTo(other.getWeight());
    }

}
