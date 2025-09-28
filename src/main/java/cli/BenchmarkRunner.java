package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;
import util.ArrayGenerators;

import java.util.*;

public class BenchmarkRunner {

    public static void main(String[] args) {
        Map<String, List<String>> f = parse(args);
        boolean twoway = Boolean.parseBoolean(f.getOrDefault("--twoway", List.of("true")).get(0));
        List<Integer> ns = f.getOrDefault("--n", List.of("100", "1000", "10000", "100000"))
                .stream().map(Integer::parseInt).toList();
        List<String> dists = f.getOrDefault("--dist", List.of("random", "sorted", "reverse", "nearly-sorted"));
        long seed = Long.parseLong(f.getOrDefault("--seed", List.of("42")).get(0));

        System.out.println("algo,n,dist,time_us,comparisons,swaps,reads,writes,ok");

        for (int n : ns) {
            for (String d : dists) {
                int[] base = switch (d) {
                    case "sorted" -> ArrayGenerators.sorted(n);
                    case "reverse" -> ArrayGenerators.reverse(n);
                    case "nearly-sorted" -> ArrayGenerators.nearlySorted(n, Math.max(1, n / 100), seed);
                    default -> ArrayGenerators.random(n, seed);
                };

                int[] a = Arrays.copyOf(base, base.length);
                PerformanceTracker perf = new PerformanceTracker();
                new SelectionSort(perf, twoway).sort(a);
                boolean ok = ArrayGenerators.isSorted(a);

                System.out.printf(Locale.US,
                        "selection,%d,%s,%d,%d,%d,%d,%d,%b%n",
                        n, d, perf.elapsedMicros(), perf.comparisons, perf.swaps, perf.reads, perf.writes, ok);
            }
        }
    }

    private static Map<String, List<String>> parse(String[] args) {
        Map<String, List<String>> m = new HashMap<>();
        String key = null;
        for (String s : args) {
            if (s.startsWith("--")) {
                key = s;
                m.putIfAbsent(key, new ArrayList<>());
            } else if (key != null) {
                m.get(key).add(s);
            }
        }
        return m;
    }
}
