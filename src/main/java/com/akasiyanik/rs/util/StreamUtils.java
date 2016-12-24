package com.akasiyanik.rs.util;

import com.akasiyanik.rs.weight.WeightedItem;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author akasiyanik
 *         12/23/16
 */
public final class StreamUtils {

    public static List<Integer> generateIntStream(int n) {
        List<Integer> stream = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            stream.add(i);
        }
        Collections.shuffle(stream, new Random(System.nanoTime()));
        return stream;
    }

    public static List<WeightedItem<Integer>> generateWeightedIntStream(int n, int pow) {
        List<WeightedItem<Integer>> stream = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            stream.add(new WeightedItem<>(i, Math.pow((double) i + 1, pow)));
        }
        Collections.shuffle(stream, new Random(System.nanoTime()));
        return stream;
    }

    public static void shuffleStream(List<?> stream) {
        Collections.shuffle(stream, new Random(System.nanoTime()));
    }

    public static void printStream(List<?> l) {
        System.out.println(l);
    }

    public static <T> List<T> sampleWithoutWeights(List<WeightedItem<T>> l) {
        return l.stream().map(WeightedItem::getItem).collect(Collectors.toList());
    }
}
