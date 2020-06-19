package Exercise3;

import java.util.Random;

public class Tester
{
    public static void main(String args[])
    {
        Random randomNumberGenerator = new Random();

        Integer[] kValues = new Integer[] {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", "K", "Comparisons", "Swaps", "R Minimum", "R Maximum", "R Average");

        for (int counter = 0; counter < kValues.length; counter++)
        {
            QuickSort quickSort = new QuickSort();

            Double[] array = new Double[(int) Math.pow(2, (double) kValues[counter])];
            for (int innerCounter = 0; innerCounter < array.length; innerCounter++)
                array[innerCounter] = randomNumberGenerator.nextDouble();
            quickSort.quicksort(array);

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", kValues[counter], quickSort.comparisons, quickSort.swaps, quickSort.rMinimum, quickSort.rMaximum, quickSort.rAverage);
        }
    }
}
