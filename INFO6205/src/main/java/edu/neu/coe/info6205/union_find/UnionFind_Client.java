package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UnionFind_Client {

    public static int count(int n) {

        int pairs = 0;
        UF_HWQUPC uf = new UF_HWQUPC(n, true);
        Random rand = new Random();

        while (uf.components() > 1) {
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);

            if(!uf.connected(x,y)) {
                uf.union(x,y);
            }
            pairs++;

        }

        return pairs;
    }

    public static void main(String args[]) {

        for(int i=10000; i<20000; i+=1000) {
            int pairsCount = count(i);
            System.out.println("For " + i + " objects, the number of pairs is: " + pairsCount);
        }
    }
}
