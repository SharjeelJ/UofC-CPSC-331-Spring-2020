package Exercise1;

/**
 * Wrapper class that implements binary search for an integer
 * array.
 *
 * @author Usman Alim (for CPSC 331)
 */

public class BinarySearch {

    public static void fillWithIndex(final int[] array) {
        for (int i = 0; i < array.length; ++i)
            array[i] = i;
    }

    public static int binarySearch(final int[] array, final int key) {
        return binarySearch(array, key, 0, array.length - 1);
    }

    public static int binarySearch(final int[] array, final int key, int lo, int hi) {
        // Out of bounds check
        if (lo < 0 || hi >= array.length)
            return -2;

        while (lo <= hi) {
            final int mid = (lo + hi) >> 1;
            if (key == array[mid])
                return mid;
            else if (key > array[mid])
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        return -1;
    }

}
