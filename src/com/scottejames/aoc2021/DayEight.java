package com.scottejames.aoc2021;

import java.util.*;
import java.util.stream.Collectors;

public class DayEight {

    public static void main(String [] args) {
        FileUtils fh = new FileUtils("2021/DayEight.txt");

        List<String> data = fh.getFileAsList();

        List<Pair<ArrayList<Set>, ArrayList<Set>>> line = new ArrayList<>();

        for(String element: data){
            String lhs = element.split("\\|")[0];
            String rhs = element.split("\\|")[1];
            ArrayList<Set> lhsList = new ArrayList<>();
            ArrayList<Set> rhsList = new ArrayList<>();

            for(String s: lhs.strip().split(" ")){
                Set<Character> set = s.chars().mapToObj(c->(char)c).collect(Collectors.toSet());
                lhsList.add(set);
            }
            for(String s: rhs.strip().split(" ")){
                Set<Character> set = s.chars().mapToObj(c->(char)c).collect(Collectors.toSet());
                rhsList.add(set);
            }
            line.add(new Pair<>(lhsList,rhsList));

        }
        //System.out.println(line);


        int partOneCount = 0;
        int partTwoCount = 0;
        for (var l: line) {
            // Part One
            for (Set word : l.rhs) {
                if ((word.size() == 2) || (word.size() == 3) || (word.size() == 4) || (word.size() == 7)) {
                    partOneCount++;
                }
            }
        //    System.out.println("Part one " + partOneCount);

            // Part Two, WHAT WE KNOW.
            Set one = l.lhs.stream().filter(s -> s.size()  ==2 ).findFirst().get();
            Set four = l.lhs.stream().filter(s -> s.size()  ==4 ).findFirst().get();
            Set seven = l.lhs.stream().filter(s -> s.size()  ==3 ).findFirst().get();
            Set eight = l.lhs.stream().filter(s -> s.size()  ==7 ).findFirst().get();

            Set[] values = {null, one, null, null, four, null, null, seven, eight, null};

            for (Set word:l.lhs){

                // Go a hunting!

                if (word.size() == 5) { // 2,3 or 5
                    if (word.containsAll(one)) {  // 3
                        values[3] = word;
                    } else { // 5
                        Set<Character> testForFive = new HashSet<>(four);
                        testForFive.removeAll(one);
                        if (word.containsAll(testForFive)){
                            values[5] = word;
                        } else {
                            values[2] = word;
                        }
                    }
                }
                if (word.size() == 6) { // 0 6 or 9
                    if (word.containsAll(four)){
                        values[9] = word;
                    } else if (!word.containsAll(one)){
                        values[6] = word;
                    } else {
                        values[0] = word;
                    }
                }
            }
            StringBuilder outputValue = new StringBuilder();
            for (Set word : l.rhs) {
                for (int i = 0; i <= 9; i++) {
                    if (values[i].equals(word)) {
                        outputValue.append(i);
                    }
                }
            }
            //System.out.println(outputValue.toString());
            partTwoCount+=Integer.parseInt(outputValue.toString());


        }
        System.out.println("Part Two: " + partTwoCount);
    }
    private static String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
    private static boolean containsString(String l, String r){
        Set<Character> rSet = r.chars().mapToObj(c->(char)c).collect(Collectors.toSet());
        Set<Character> lSet = l.chars().mapToObj(c->(char)c).collect(Collectors.toSet());

        return rSet.containsAll(lSet);
    }
1
}
