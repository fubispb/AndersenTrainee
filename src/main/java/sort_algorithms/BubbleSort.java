package sort_algorithms;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        // O(n^2)
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                SwapIntArray.swap(array, i, i - 1);
                i = 0;
            }
        }
        System.out.println(Arrays.toString(array));

    }
}
