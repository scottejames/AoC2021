package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {
    public static int  sumListOfInteger(List<Integer> list){
        int result = 0;
        for (int number: list) result += number;
        return result;
    }

    public static List<Integer> getRange(int x, int y){

        List<Integer> result = new ArrayList<>();


        if (y>x){
            for (int c=x; c<=y;c++){
                result.add(c);
            }
        } else if (x>y) {
            for (int c=x; c>=y; c--){
                result.add(c);
            }
        } else if (x==y){
            result.add(x);
        }
        return result;

    };
}
