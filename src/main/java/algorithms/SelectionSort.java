package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {
    private final PerformanceTracker perf;
    private final boolean twoWay;

    public SelectionSort(PerformanceTracker perf, boolean twoWay) {
        this.perf = perf;
        this.twoWay = twoWay;
    }

    public void sort(int[] a) {
        if (a == null) throw new IllegalArgumentException("array is null");

        perf.reset();
        perf.start();

        int n = a.length;
        if (n < 2) {
            perf.stop();
            return;
        }

        int left = 0, right = n - 1;
        while (left < right) {
            if (twoWay) {
                int minIdx = left, maxIdx = right;

                for (int i = left; i <= right; i++) {
                    perf.reads += 2;
                    perf.comparisons += 2;

                    if (a[i] < a[minIdx]) minIdx = i;
                    if (a[i] > a[maxIdx]) maxIdx = i;
                }

                swap(a, left, minIdx);

                if (maxIdx == left) maxIdx = minIdx;

                swap(a, right, maxIdx);

                left++;
                right--;

            } else {
                int minIdx = left;
                boolean changed = false;

                for (int i = left + 1; i <= right; i++) {
                    perf.reads += 2;
                    perf.comparisons++;
                    if (a[i] < a[minIdx]) minIdx = i;
                }

                changed = swap(a, left, minIdx);

                if (!changed && isSortedSuffix(a, left + 1)) break;
                left++;
            }
        }

        perf.stop();
    }

    private boolean isSortedSuffix(int[] a, int start) {
        for (int i = start + 1; i < a.length; i++) {
            perf.reads += 2;
            perf.comparisons++;
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }

    private boolean swap(int[] a, int i, int j) {
        if (i == j) return false;

        int t = a[i];
        perf.reads++;

        a[i] = a[j];
        perf.reads++;
        perf.writes++;

        a[j] = t;
        perf.writes++;

        perf.swaps++;
        return true;
    }
}
