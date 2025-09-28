package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectionSortPropertyTest {

    @RepeatedTest(25)
    void randomArraysAreSorted() {
        Random r = new Random();
        int n = r.nextInt(300) + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt();
        }

        new SelectionSort(new PerformanceTracker(), true).sort(a);

        for (int i = 1; i < a.length; i++) {
            assertTrue(a[i - 1] <= a[i], "Массив не отсортирован на позиции " + i);
        }
    }
}
