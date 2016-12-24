package com.akasiyanik.rs;

import com.akasiyanik.rs.simple.SimpleReservoirSampling;
import com.akasiyanik.rs.weight.WeightedItem;
import com.akasiyanik.rs.weight.WeightedReservoirSampling;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.akasiyanik.rs.util.StreamUtils.*;

/**
 * @author akasiyanik
 *         12/23/16
 */
public class ExperimentRunner {

    public static void main(String[] args) {
        new ExperimentRunner(100, 5, 100).run();
    }

    private int n;
    private int m;
    private int experimentCount;

    private static final String resultDir = "/Users/akasiyanik/FPMI/reservoir-sampling/src/main/resources/results";

    public ExperimentRunner(int n, int m, int experimentCount) {
        this.n = n;
        this.m = m;
        this.experimentCount = experimentCount;
    }

    public void run() {
        simpleSampleExperiment();
        weightedSampleExperiment(1);
        weightedSampleExperiment(2);
    }

    private void simpleSampleExperiment() {
        List<Integer> results = Stream.generate(() -> 0).limit(n).collect(Collectors.toList());
        List<Integer> stream = generateIntStream(n);
        for (int i = 0; i < experimentCount; i++) {
            shuffleStream(stream);
            ReservoirSampling<Integer> reservoirSampling = new SimpleReservoirSampling<>(m);
            stream.forEach(reservoirSampling::pushItem);
            collectResults(results, reservoirSampling.getSample());
        }
        writeResults(results, "simple-" + n + "-" + m + "-" + experimentCount + ".txt");
    }

    private void weightedSampleExperiment(int pow) {
        List<Integer> results = Stream.generate(() -> 0).limit(n).collect(Collectors.toList());
        List<WeightedItem<Integer>> stream = generateWeightedIntStream(n, pow);
        for (int i = 0; i < experimentCount; i++) {
            shuffleStream(stream);
            ReservoirSampling<WeightedItem<Integer>> reservoirSampling = new WeightedReservoirSampling<>(m);
            stream.forEach(reservoirSampling::pushItem);
            collectResults(results, sampleWithoutWeights(reservoirSampling.getSample()));
        }
        writeResults(results, "weight" + pow + "-" + n + "-" + m + "-" + experimentCount + ".txt");
    }

    private void collectResults(List<Integer> results, List<Integer> sample) {
        sample.forEach(s -> results.set(s, results.get(s) + 1));
    }

    private void writeResults(List<Integer> results, String filename) {
        try {
            Path file = Paths.get(resultDir, filename);
            Files.write(file, results.stream().map(String::valueOf).collect(Collectors.toList()), Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
