package com.scottejames.aoc2021;

import java.util.ArrayList;

public class DaySix {
    /*
     * Cycle 6,5,4,3,2,1,0 _ NEW FISH _ back to 6
     * New fish starts at 8
     */
    //private static int[] data = {3, 4, 3, 1, 2};
    private static int[] data = {5,1,1,5,4,2,1,2,1,2,2,1,1,1,4,2,2,4,1,1,1,1,1,4,1,1,1,1,1,5,3,1,4,1,1,1,1,1,4,1,5,1,1,1,4,1,2,2,3,1,5,1,1,5,1,1,5,4,1,1,1,4,3,1,1,1,3,1,5,5,1,1,1,1,5,3,2,1,2,3,1,5,1,1,4,1,1,2,1,5,1,1,1,1,5,4,5,1,3,1,3,3,5,5,1,3,1,5,3,1,1,4,2,3,3,1,2,4,1,1,1,1,1,1,1,2,1,1,4,1,3,2,5,2,1,1,1,4,2,1,1,1,4,2,4,1,1,1,1,4,1,3,5,5,1,2,1,3,1,1,4,1,1,1,1,2,1,1,4,2,3,1,1,1,1,1,1,1,4,5,1,1,3,1,1,2,1,1,1,5,1,1,1,1,1,3,2,1,2,4,5,1,5,4,1,1,3,1,1,5,5,1,3,1,1,1,1,4,4,2,1,2,1,1,5,1,1,4,5,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,4,2,1,1,1,2,5,1,4,1,1,1,4,1,1,5,4,4,3,1,1,4,5,1,1,3,5,3,1,2,5,3,4,1,3,5,4,1,3,1,5,1,4,1,1,4,2,1,1,1,3,2,1,1,4};
    private static long[] school = new long[9];
    public static void main(String[] args) {

        for (int i = 0; i < data.length; i++){
            school[data[i]]++;
        }
        System.out.println(school);

        for (int day = 0; day < 256; day++){
            long newFish = school[0];
            school[0] = school[1];
            school[1] = school[2];
            school[2] = school[3] ;
            school[3] = school[4];
            school[4] = school[5];
            school[5] = school[6];
            school[6] = school[7] + newFish;
            school[7] = school[8];
            school[8] = newFish;
        }

        long schoolSize =  0;
        for (long l: school){
            schoolSize += l;
        }
        System.out.println("school size : " + schoolSize);
    }

}
