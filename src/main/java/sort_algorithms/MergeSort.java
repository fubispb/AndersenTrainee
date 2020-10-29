package sort_algorithms;

import java.util.Arrays;
import java.util.Objects;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = sortArrAY(new int[]{10, 2, 10, 3, 1, 2, 5});
        System.out.println(Arrays.toString(arr));
    }

    // O(n log n)
    public static int[] sortArrAY(int[] array) {
        if (Objects.isNull(array)) {
            return null;
        }
        if (array.length < 2){
            return array;
        }
        int[] arrayA = new int[array.length / 2];
        int[] arrayB = new int[(array.length - (array.length / 2))];
        System.arraycopy(array, 0, arrayA, 0, array.length / 2);
        System.arraycopy(array, array.length / 2, arrayB, 0, (array.length - (array.length / 2)));
        arrayA = sortArrAY(arrayA);
        arrayB = sortArrAY(arrayB);
        return mergeArrays(arrayA, arrayB);
    }

    public static int[] mergeArrays(int[] arrayA, int[] arrayB) {
        int[] resultArray = new int[arrayA.length + arrayB.length];
        int firstMassIteration = 0;
        int secondMassIteration = 0;
        for (int i = 0; i < resultArray.length; i++) {
            if (firstMassIteration == arrayA.length) {
                resultArray[i] = arrayB[secondMassIteration++];
                continue;
            }
            if (secondMassIteration == arrayB.length){
                resultArray[i] = arrayA[firstMassIteration++];
                continue;
            }
            if(arrayA[firstMassIteration] < arrayB[secondMassIteration]) {
                resultArray[i] = arrayA[firstMassIteration++];
            }
            else resultArray[i] = arrayB[secondMassIteration++];
        }
        return resultArray;
    }
}
