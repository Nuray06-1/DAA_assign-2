package util;

import java.util.Random;
import java.util.stream.IntStream;

public class ArrayGenerators {

    public static int[] random(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt();
        }
        return a;
    }

    public static int[] sorted(int n) {
        return IntStream.range(0, n).toArray();
    }

    public static int[] reverse(int n) {
        int[] a = sorted(n);
        for (int i = 0; i < n / 2; i++) {
            int t = a[i];
            a[i] = a[n - 1 - i];
            a[n - 1 - i] = t;
        }
        return a;
    }

    public static int[] nearlySorted(int n, int swaps, long seed) {
        int[] a = sorted(n);
        Random r = new Random(seed);
        for (int k = 0; k < swaps; k++) {
            int i = r.nextInt(n);
            int j = r.nextInt(n);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        return a;
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
