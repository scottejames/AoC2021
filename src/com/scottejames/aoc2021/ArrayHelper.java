package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {
    public static int  sumListOfInteger(List<Integer> list){
        int result = 0;
        for (int number: list) result += number;
        return result;
    }
}
