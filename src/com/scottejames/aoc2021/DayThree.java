package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.List;

public class DayThree {

    public static void main(String [] args) {
        FileUtils fh = new FileUtils("2021/DayThree.txt");

        List<String> data = fh.getFileAsList();

        StringBuffer gammaResult = new StringBuffer();
        StringBuffer epsilonResult = new StringBuffer();


        for (int i = 0; i < data.get(0).length(); i++) {
            int result = countBits(i, data);
            if (result == 1) {
                epsilonResult.append("0");
                gammaResult.append("1");
             } else {
                epsilonResult.append("1");
                gammaResult.append("0");
            }
        }

        System.out.println(epsilonResult + " " + gammaResult);

        int epsilon = Integer.parseInt(epsilonResult.toString(),2);
        int gamma = Integer.parseInt(gammaResult.toString(),2);

        System.out.println("Part One : " + epsilon + " x " + gamma + " = " + epsilon * gamma);
        List<String> oxData = new ArrayList<>(data);
        List<String> coData = new ArrayList<>(data);

        for (int i = 0; i < data.get(0).length(); i++) {
            int result = countBits(i, oxData);
            final int location = i;
            if (result == 1)
                oxData.removeIf(s -> s.charAt(location) == '0');
            else
                oxData.removeIf(s -> s.charAt(location) == '1');
        }

        int oxGenRating = Integer.parseInt(oxData.get(0),2);

        for (int i = 0; i < coData.get(0).length(); i++) {
            int result = countBits(i, coData);
            final int location = i;
            if (result == 0)
                coData.removeIf(s -> s.charAt(location) == '0');
            else
                coData.removeIf(s -> s.charAt(location) == '1');
            if (coData.size() == 1) break;
        }

        int coDataRating = Integer.parseInt(coData.get(0),2);
        System.out.println("PartTwo: " + oxGenRating + " x " + coDataRating +" = " + oxGenRating * coDataRating);

    }

    public static int countBits(int position, List<String> data) {
        int ones = 0;
        int zeros = 0;
        for (String line : data) {
            if (line.charAt(position) == '1')
                ones++;
            if (line.charAt(position) == '0')
                zeros++;
        }
        if (ones >= zeros)
            return 1;
        else
            return 0;
    }

}
