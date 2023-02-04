package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerFunction {

    public static void sortArray(Integer[] arr, int n) {
        final Config config = Config.setupConfig("true", "0", "1", "", "");
        Helper<Integer> helper = HelperFactory.create("InsertionSort", n, config);
        SortWithHelper<Integer> sorter = new InsertionSort<Integer>(helper);
        Integer[] ys = sorter.sort(arr);
    }

    public static void main(String[] args) {

        int n = 8000;

        Consumer<Integer[]> randomFunc = randArr -> sortArray(randArr, n);
        Benchmark_Timer<Integer[]> randomTimer = new Benchmark_Timer<>("Sort random array of " + n + " elements", randomFunc);
        Supplier<Integer[]> random = () -> {
            Integer[] randArr = new Integer[n];                     //Random
            Random randI = new Random();
            for(int i=0; i<n; i++) {
                int randInt = randI.nextInt(n);
                randArr[i] = randInt+1;
            }
            return randArr;
        };
        randomFunc.accept(random.get());
        double randTime = randomTimer.run(random.get(), 100);
        System.out.println("Time for random array of " + n + " elements is: " + randTime);


        Consumer<Integer[]> orderedFunc = orderArr -> sortArray(orderArr, n);
        Benchmark_Timer<Integer[]> orderTimer = new Benchmark_Timer<>("Sort ordered array of " + n + " elements", orderedFunc);
        Supplier<Integer[]> order = () -> {
            Integer[] orderArr = new Integer[n];                     //Ordered
            for(int i=0; i<n; i++) {
                orderArr[i] = i;
            }
            return orderArr;
        };
        orderedFunc.accept(order.get());
        double orderTime = orderTimer.run(order.get(), n);
        System.out.println("Time for ordered array of " + n + " elements is: " + orderTime);


        Consumer<Integer[]> reverseOrderedFunc = reverseOrderArr -> sortArray(reverseOrderArr, n);
        Benchmark_Timer<Integer[]> reverseOrderTimer = new Benchmark_Timer<>("Sort Reverse ordered array of " + n + " elements", reverseOrderedFunc);
        Supplier<Integer[]> reverseOrder = () -> {
            Integer[] reverseOrderArr = new Integer[n];               //Reverse Ordered
            int j = n;
            for(int i=0; i<n; i++) {
                reverseOrderArr[i] = j;
                j--;
            }
            return reverseOrderArr;
        };
        reverseOrderedFunc.accept(reverseOrder.get());
        double reverseOrderTime = reverseOrderTimer.run(reverseOrder.get(), n);
        System.out.println("Time for Reverse ordered array of " + n + " elements is: " + reverseOrderTime);


        Consumer<Integer[]> partiallyOrderedFunc = partiallyOrderArr -> sortArray(partiallyOrderArr, n);
        Benchmark_Timer<Integer[]> partiallyOrderTimer = new Benchmark_Timer<>("Sort Partially ordered array of " + n + " elements", partiallyOrderedFunc);
        Supplier<Integer[]> partiallyOrder = () -> {
            Integer[] partiallyOrderArr = new Integer[n];              //Partially Ordered
            for(int i=0; i<n/2; i++) {
                partiallyOrderArr[i] = i;
            }
            Random randI = new Random();
            for(int i=n/2; i<n; i++) {
                int randInt = randI.nextInt(n - n/2);
                partiallyOrderArr[i] = randInt + n/2;
            }
            return partiallyOrderArr;
        };
        partiallyOrderedFunc.accept(partiallyOrder.get());
        double partiallyOrderTime = partiallyOrderTimer.run(partiallyOrder.get(), n);
        System.out.println("Time for Partially ordered array of " + n + " elements is: " + partiallyOrderTime);

    }
}
