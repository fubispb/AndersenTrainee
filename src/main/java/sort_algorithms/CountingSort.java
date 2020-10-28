package sort_algorithms;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        array = countingSort(array, 10);
        System.out.println(Arrays.toString(array));
    }
    // O(n+k)
    public static int[] countingSort(int[] theArray, int maxValue) {
        int[] numCounts = new int[maxValue + 1];
        for (int num : theArray) {
            numCounts[num]++;
        }
        int[] sortedArray = new int[theArray.length];
        int currentSortedIndex = 0;
        for (int n = 0; n < numCounts.length; n++) {
            int count = numCounts[n];
            for (int k = 0; k < count; k++) {
                sortedArray[currentSortedIndex] = n;
                currentSortedIndex++;
            }
        }
        return sortedArray;
    }
}
