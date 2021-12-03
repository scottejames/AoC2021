package com.scottejames.aoc2021;

import java.util.List;

public class DayTwo {
    public static void main(String[] args) {
        FileUtils fh = new FileUtils("2021/DayTwo.txt");

        List<String> data = fh.getFileAsList();
        int horiz = 0;
        int vert = 0;
        int aim = 0;
        for (String line : data) {
            String[] split = line.split(" ");
            String instruction = split[0];
            Integer size = Integer.parseInt(split[1]);
            switch (instruction) {
                case "forward":
                    horiz += size;
                    vert += size * aim;
                    break;
                case "down":
                  //  vert += size;
                    aim += size;
                    break;
                case "up":
                  //  vert -= size;
                    aim -= size;
                    break;

            }


        }
        System.out.println("Part One Horiz : " + horiz + " Vert : " + vert + " mult : " + vert * horiz);

    }
}
