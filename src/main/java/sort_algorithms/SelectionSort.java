package sort_algorithms;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        // O(n^2)
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[minInd]) {
                    minInd = i;
                }
            }
            SwapIntArray.swap(array, left, minInd);
        }
        System.out.println(Arrays.toString(array));
    }
}
