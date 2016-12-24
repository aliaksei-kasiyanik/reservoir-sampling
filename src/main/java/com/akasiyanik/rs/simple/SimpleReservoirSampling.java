package com.akasiyanik.rs.simple;

import com.akasiyanik.rs.ReservoirSampling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author akasiyanik
 *         12/23/16
 */
public class SimpleReservoirSampling<T> implements ReservoirSampling<T> {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private int m;

    private int i = 0;

    private List<T> sample;

    public SimpleReservoirSampling(int m) {
        this.m = m;
        this.sample = new ArrayList<>(m);
    }

    @Override
    public void pushItem(T item) {
        if (i < m) {
            sample.add(item);
        } else {
            int index = random.nextInt(0, i);
            if (index < m) {
                sample.set(index, item);
            }
        }
        i++;
    }

    @Override
    public List<T> getSample() {
        return sample;
    }

}
