package Exercise3;

/**
 * CPSC 331
 * <p>
 * Quicksort code adapted from:
 * Data Structures and Algorithms in Java, by Adam Drozdek, 4th edition
 * Available online at:
 * http://www.mathcs.duq.edu/drozdek/DSinJava/
 */

public class QuickSort
{

    public int swaps, comparisons, recursiveCalls;
    public double rMinimum = Double.MAX_VALUE, rMaximum = Double.MIN_VALUE, rAverage;

    public void swap(Object[] a, int e1, int e2)
    {
        swaps++;
        Object tmp = a[e1];
        a[e1] = a[e2];
        a[e2] = tmp;
    }

    private <T extends Comparable<? super T>> void quicksort(T[] data, int first, int last)
    {
        int lower = first + 1, upper = last;
        swap(data, first, (first + last) / 2);
        T bound = data[first];
        while (lower <= upper)
        {
            while (bound.compareTo(data[lower]) > 0)
            {
                comparisons++;
                lower++;
            }
            while (bound.compareTo(data[upper]) < 0)
            {
                comparisons++;
                upper--;
            }
            if (lower < upper) swap(data, lower++, upper--);
            else lower++;
        }
        swap(data, upper, first);
        double leftPartitionR = (double) (last - upper) / (double) (last - first + 1);
        double rightPartitionR = (double) (upper - first + 1) / (double) (last - first + 1);
        double currentR;
        if (first < upper - 1)
        {
            recursiveCalls++;

            if (leftPartitionR >= rightPartitionR) currentR = leftPartitionR;
            else currentR = rightPartitionR;
            rAverage += currentR;

            if (currentR < rMinimum) rMinimum = currentR;
            else if (currentR > rMaximum) rMaximum = currentR;

            quicksort(data, first, upper - 1);
        }
        if (upper + 1 < last)
        {
            recursiveCalls++;

            if (leftPartitionR >= rightPartitionR) currentR = leftPartitionR;
            else currentR = rightPartitionR;
            rAverage += currentR;

            if (currentR < rMinimum) rMinimum = currentR;
            else if (currentR > rMaximum) rMaximum = currentR;

            quicksort(data, upper + 1, last);
        }
    }

    public <T extends Comparable<? super T>> void quicksort(T[] data)
    {
        if (data.length < 2) return;
        int max = 0;
        // find the largest element and put it at the end of data;
        for (int i = 1; i < data.length; i++)
            if (data[max].compareTo(data[i]) < 0) max = i;
        swap(data, data.length - 1, max);    // largest el is now in its
        recursiveCalls++;
        quicksort(data, 0, data.length - 2); // final position;
        rAverage /= recursiveCalls;
    }
}
