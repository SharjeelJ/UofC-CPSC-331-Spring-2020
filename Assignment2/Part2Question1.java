package Assignment2;

import java.util.Arrays;

public class Part2Question1
{
    public static void main(String args[])
    {
        int[] array1 = new int[] {34, 8, 64, 51, 32, 21};
        int[] array2 = new int[] {34, 8, 64, 51, 32, 21};

        System.out.println(Arrays.toString(array1));
        selectionSort(array1);
        System.out.println(Arrays.toString(array1));
        System.out.println();
        System.out.println(Arrays.toString(array2));
        recursiveSelectionSort(array2, 0);
        System.out.println(Arrays.toString(array2));

    }

    private static void selectionSort(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        { // Find the least item in unsorted subarray
            int least = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[least]) least = j;

            // Swap items
            int temp = arr[least];
            arr[least] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Recursively performs selection sort on the passed in array in place
     *
     * @param inputArray    Input 1D integer array (can be empty)
     * @param unsortedIndex Integer that will denote the start index of the unsorted part of the array (default 0)
     */
    public static void recursiveSelectionSort(int[] inputArray, int unsortedIndex)
    {
        // Checks to see if there are elements that need to be sorted in the provided array based on the number of unsorted elements
        if (unsortedIndex < inputArray.length - 1)
        {
            // Will contain the index of the smallest value in the unsorted part of the array (right side of and including the unsortedIndex location)
            int smallestValueIndex = unsortedIndex;

            // Loops through the unsorted part of the array and stores the index of the smallest value
            for (int innerCounter = unsortedIndex + 1; innerCounter < inputArray.length; innerCounter++)
                if (inputArray[innerCounter] < inputArray[smallestValueIndex]) smallestValueIndex = innerCounter;

            // Swaps the smallest value to the start of the unsorted array part
            int tempValue = inputArray[smallestValueIndex];
            inputArray[smallestValueIndex] = inputArray[unsortedIndex];
            inputArray[unsortedIndex] = tempValue;

            // Recursively calls the selection sort method with the sortedUptoIndex incremented by 1
            recursiveSelectionSort(inputArray, unsortedIndex + 1);
        }
    }
}
