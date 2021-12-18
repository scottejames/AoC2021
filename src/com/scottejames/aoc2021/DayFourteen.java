package com.scottejames.aoc2021;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DayFourteen {
    public static void main(String[] args) {


        FileUtils fh = new FileUtils("2021/DayFourteen.txt");

        Map<String, Character> rules = new HashMap<String, Character>();
        Map<String, Long> pairs = new HashMap<String, Long>();
        Map<String, Long> temppairs = new HashMap<String, Long>();
        Map<Character, Long> counts = new HashMap<Character, Long>();

        List<String> data = fh.getFileAsList();
        String code = "";

        for (String line : data) {
            if (line.isEmpty()) continue;
            if (line.contains("->")) {
                String[] split = line.split("->");
                rules.put(split[0].trim(), split[1].trim().charAt(0));
                pairs.put(split[0].trim(), 0L);
                temppairs.put(split[0].trim(), 0L);

            } else {
                code = line;
            }
        }

        for (int i = 0; i < code.length() - 1; i++) {
            String polymer = code.substring(i, i + 2);
            pairs.put(polymer, (pairs.get(polymer) + 1));
        }
        for (int i = 0; i < 40; i++) {

            for (String s : pairs.keySet()) {
                System.out.println("STring " + s);
                String lhs = "" + s.charAt(0) + rules.get(s);
                String rhs = "" + rules.get(s) + s.charAt(1);
                System.out.println(lhs + ", " + rhs);

                if (pairs.get(s) > 0) {
                    temppairs.put(lhs, (temppairs.get(lhs) + pairs.get(s)));
                    temppairs.put(rhs, (temppairs.get(rhs) + pairs.get(s)));
                }

            }
            for (String c : temppairs.keySet()) {
                pairs.put(c , temppairs.get(c));
                temppairs.put(c, 0L);
            }
        }

        counts.put(code.charAt(code.length()-1), 1L);

        for (String s : pairs.keySet()) {
            counts.putIfAbsent(s.charAt(0), 0L);
            counts.put(s.charAt(0), (counts.get(s.charAt(0)) + pairs.get(s)));
        }
        for (String s : pairs.keySet()) {
            counts.putIfAbsent(s.charAt(0), 0L);
            counts.put(s.charAt(0), (counts.get(s.charAt(0)) + pairs.get(s)));
        }
        Long min = Collections.min(counts.values());
        Long max = Collections.max(counts.values());
        Long result = (max - min -1)/2;
        System.out.println("Max - Min " + max + " - " + min + " = " + result);
    }
}

//        for (int iteration = 0; iteration < 40; iteration++) {
////            System.out.println("Itter " + iteration + " Seed : " + seed);
//            StringBuilder sb = new StringBuilder();
//            sb.append(seed.charAt(0));
//
//            for (int i = 0; i < seed.length() - 1; i++) {
//                String pair = seed.substring(i, i + 2);
//                String insert = mapping.get(pair);
////                System.out.println(pair + " matches " + insert);
//                if (!insert.isEmpty()) {
//                    sb.append( insert + pair.charAt(1));
//                } else {
//                    sb.append(pair.charAt(1));
//                }
//            }
//            seed = sb.toString();
////            System.out.println("Out : " + seed);
//         }
      //  var letters = seed.chars().boxed().collect(Collectors.groupingBy(s-> Character.toString(s), Collectors.counting()));
        //var c = letters.values().stream().sorted().toList();

//        System.out.println("Out : " + letters);
        //System.out.println("part1:" + (c.get(c.size()-1) -  c.get(0)));

//    }

    // NNCB
    // NCNBCHB
    // NBCCNBBBCBHCB

    /*
    Template:       NNCB
After step 1:       NCNBCHB
After step 2:       NBCCNBBBCBHCB
After step 3:       NBBBCNCCNBBNBNBBCHBHHBCHB
After step 4:       NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
     */
//}
