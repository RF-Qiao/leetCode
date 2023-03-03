package com.feng.leecode;

/**
 * 2023.04.24
 * feng
 */
public class climb_stairs_ {
    public int climbStairs(int n) {
        int[] ints = new int[n+2];
        ints[1]=1;
        ints[2]=2;
        for (int i=3;i<=n;i++){
            ints[i]=ints[i-1]+ints[i-2];
        }
        return ints[n];
    }
}
