package metrics;

public class PerformanceTracker {
    public long comparisons, swaps, reads, writes;
    public long startTimeNs, endTimeNs;

    public void reset() {
        comparisons = swaps = reads = writes = 0;
        startTimeNs = endTimeNs = 0;
    }

    public void start() {
        startTimeNs = System.nanoTime();
    }

    public void stop() {
        endTimeNs = System.nanoTime();
    }

    public long elapsedMicros() {
        return (endTimeNs - startTimeNs) / 1_000;
    }
}
