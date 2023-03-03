package com.feng;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// 1 2  3  4  5
public class test {
@Test
    void tet(){
          int  min= Integer.MAX_VALUE;
    int[] prices= new int[]{7,1,5,3,6,4};
        for (int i=0;i<prices.length;i++){
           min=  Math.min(min,prices[i]);
        }
    System.out.println(min);
    }
}

