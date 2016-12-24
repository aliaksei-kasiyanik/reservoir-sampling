package com.akasiyanik.rs.weight;

import com.akasiyanik.rs.ReservoirSampling;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author akasiyanik
 *         12/23/16
 */
public class WeightedReservoirSampling<T> implements ReservoirSampling<WeightedItem<T>> {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private int m;

    private Queue<WeightedItem<T>> queue;

    public WeightedReservoirSampling(int m) {
        this(m, null);
    }

    public WeightedReservoirSampling(int m, Comparator<WeightedItem<T>> comparator) {
        this.m = m;
        this.queue = new PriorityQueue<>(m, comparator);
    }

    @Override
    public void pushItem(WeightedItem<T> item) {
        double r = Math.pow(random.nextDouble(), 1 / item.getWeight());
        if (queue.size() <= m) {
            queue.add(new WeightedItem<>(item.getItem(), r));
        } else if (queue.peek().getWeight() < r) {
            queue.poll();
            queue.add(new WeightedItem<>(item.getItem(), r));
        }
    }

    @Override
    public List<WeightedItem<T>> getSample() {
        return new LinkedList<>(queue);
    }
}
