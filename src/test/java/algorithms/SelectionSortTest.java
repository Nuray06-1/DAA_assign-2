package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void nullArray() {
        assertThrows(IllegalArgumentException.class,
                () -> new SelectionSort(new PerformanceTracker(), true).sort(null));
    }

    @Test
    void empty() {
        int[] a = {};
        new SelectionSort(new PerformanceTracker(), true).sort(a);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void single() {
        int[] a = {7};
        new SelectionSort(new PerformanceTracker(), true).sort(a);
        assertArrayEquals(new int[]{7}, a);
    }

    @Test
    void duplicates() {
        int[] a = {3, 1, 2, 1, 3};
        new SelectionSort(new PerformanceTracker(), true).sort(a);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, a);
    }

    @Test
    void reverse() {
        int[] a = {5, 4, 3, 2, 1};
        new SelectionSort(new PerformanceTracker(), true).sort(a);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }
}
