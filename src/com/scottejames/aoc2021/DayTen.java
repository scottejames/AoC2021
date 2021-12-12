package com.scottejames.aoc2021;

import java.util.*;

public class DayTen {

    private static Map<String, String> pairs = Map.of("(", ")", "{", "}", "[", "]", "<", ">");
    private static Map<String, Integer> score = Map.of(")", 3, "]", 57, "}", 1197, ">", 25137);
    private static Map<String, Integer> goodScore = Map.of(")", 1, "]", 2, "}", 3, ">", 4);

    public static void main(String [] args) {

        FileUtils fh = new FileUtils("2021/DayTen.txt");

        List<String> data = fh.getFileAsList();
        long pOne = 0l;
        List<Stack<String>> incompleteLines = new ArrayList<>();

        for (String line:data){
            Stack<String> stack = new Stack<>();
            boolean invalid = false;
            for (char c:line.toCharArray()){
                String bracket = "" + c;
                if (pairs.keySet().contains(bracket)){
                    // Open
                    stack.push(bracket);
                } else {
                    // Close
                    String openBracket = stack.pop();
                    if (!pairs.get(openBracket).equals(bracket)) {
                        int pts = score.get(bracket);
                        pOne += pts;
                       // System.out.println("Found : " + pts);
                        invalid = true;
                        break;
                    }
                }
            }
            if (invalid == false)
                incompleteLines.add(stack);
        }
        System.out.println("Part 1 : " + pOne);

        List<Long> scores = new ArrayList<>();
        for (Stack<String> stack: incompleteLines){
            Long pTwo = 0l;
            while (!stack.isEmpty()){
                String op = stack.pop();
                String cl = pairs.get(op);
                pTwo *=5;
                pTwo += goodScore.get(cl);
            }
            scores.add(pTwo);
        }
        Collections.sort(scores);
        System.out.println("Part 2: " + scores.get(scores.size() / 2));

    }
}
