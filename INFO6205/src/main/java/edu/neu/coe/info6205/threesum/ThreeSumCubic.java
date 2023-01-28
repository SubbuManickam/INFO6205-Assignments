package edu.neu.coe.info6205.threesum;

import edu.neu.coe.info6205.util.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the brute-force approach of
 * testing every candidate in the solution-space.
 * The array provided in the constructor may be randomly ordered.
 * <p>
 * This algorithm runs in O(N^3) time.
 */
class ThreeSumCubic implements ThreeSum {
    /**
     * Construct a ThreeSumCubic on a.
     * @param a an array.
     */
    public ThreeSumCubic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                        triples.add(new Triple(a[i], a[j], a[k]));
                }
            }
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    private final int[] a;
    private final int length;

    public static void main(String[] args) {
        int j = -15000;
        int n = 16000;
        int arr[] = new int[n];
        for(int i=0; i<n; i++) {

            arr[i] = j;
            j += 20;
        }
        ThreeSumCubic tsq = new ThreeSumCubic(arr);
        Stopwatch stopwatch = new Stopwatch();
        Triple[] out = tsq.getTriples();
        long time = stopwatch.lap();
        System.out.println("Time for ThreeSumCubic for n = "+n+" is: "+time);

    }
}
