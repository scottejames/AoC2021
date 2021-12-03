package com.scottejames.aoc2021;

import java.util.List;

public class DayOne {

    public static void main(String [] args){
        FileUtils fh = new FileUtils("2021/DayOne.txt");

        List<Integer> data = fh.getFileAsIntegerList();
        Integer prior = Integer.MAX_VALUE;
        Integer count = 0;
        for(Integer i :data){
            if (i > prior) count++;
            prior = i;
        }
        System.out.println("Part 1: " + count);
        prior = Integer.MAX_VALUE;
        count = 0;

        for (int i = 0; i < data.size() - 2; i++){
            int curr = data.get(i) + data.get(i+1)+ data.get(i+2);

            if (curr > prior) count++;
            prior = curr;
        }
        System.out.println("Part 2: " + count);

        prior = Integer.MAX_VALUE;
        count = 0;

        int curr = data.get(0) + data.get(1)+ data.get(2);

        for (int i = 1; i < data.size()-1;i++){
            if (curr > prior) count++;
            prior = curr;
            curr = curr - data.get(i-1) + data.get(i+1);
        }

        System.out.println("Part 2 for james: " + count);

    }
}
