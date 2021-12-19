package com.scottejames.aoc2021;

import java.math.BigInteger;

public class StringHelper {
    public static String hexToBin(String s) {
        return String.format("%"+(s.length()*4)+"s",
                new BigInteger(s, 16).toString(2)).replace(" ", "0");
    }
    public static int binToDec(String s) {
        return Integer.parseInt(new BigInteger(s, 2).toString(10));
    }
    public static long binToLong(String binary) {
        return Long.parseLong(binary, 2);
    }
}
