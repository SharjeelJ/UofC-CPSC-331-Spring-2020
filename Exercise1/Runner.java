package Exercise1;

import Exercise1.BinarySearch;

import java.io.IOException;

/*
 * A simple program that runs binary search several times
 * for an input of size 2^k to determine the total search
 * time (by searching for all possible inputs within an array of
 * size 2^k).
 *
 * @author Usman Alim (for CPSC 331)
 *
 */

public class Runner {

    // Default
    static final int POWER = 10;
    static final int RUNS = 1000000;

    // 5
//    static final int POWER = 5;
//    static final int RUNS = 75000000;
    // 10
//    static final int POWER = 10;
//    static final int RUNS = 750000;

    // 15
//    static final int POWER = 15;
//    static final int RUNS = 15000;

    // 20
//    static final int POWER = 20;
//    static final int RUNS = 400;

    // 25
//    static final int POWER = 25;
//    static final int RUNS = 10;

    static final int[] testArray = new int[1 << POWER];


    /**
     * Time for all possible inputs in the range 0 to
     * N (inclusive)
     */

    public static void doSearchAll(final int N) {
        final int trials = N+1;
        for (int i = 0; i < trials; ++i) {
            BinarySearch.binarySearch(testArray, i, 0, N - 1);
        }
    }


    public static void main(final String[] args) {

        // Fill array with integers from 0 to N-1
        BinarySearch.fillWithIndex(testArray);

        // Some output
        System.out.println("Power: " + POWER + ", # Runs: " + RUNS);

        System.out.println("Press Enter when ready for profiling...");

        try {
 			System.in.read();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
        // Wait for user to signal the start

        System.out.println("Running");


        // Time all the runs
        int N = 1 << POWER;
        for( int i = 0; i < RUNS; i++ )
          doSearchAll(N);
        System.out.println( "Done" );
    }

}
