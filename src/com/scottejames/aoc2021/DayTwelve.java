package com.scottejames.aoc2021;

import java.util.*;

public class DayTwelve {
    private static final String START = "start";
    private static final String END = "end";

    public static void main(String[] args){
        CollectionOfCaves caveCollection = new CollectionOfCaves();

        FileUtils fh = new FileUtils("2021/DayTwelveTest.txt");
        var data = fh.getFileAsList();
        for (String line: data){
            String[] caves = line.split("-");
            caveCollection.addCave(caves[0],caves[1]);
        }
        long pathCount = countPathsAgain(caveCollection.caves.get(START),new HashSet<>());
        System.out.println("Part One:" + pathCount);
    }

    private static long countPaths(Cave cave,HashSet<String> paths){
        if (cave.getName().equals(END))  {
            for(String c: paths){
                System.out.print(c + "->");
            }
            System.out.println();
            return 1;
        }
        HashSet<String> path = (HashSet<String>) paths.clone();
        path.add(cave.getName());
        int count = 0;
        for (Cave forward: cave.connections){
            if ((forward.large == true) || (!paths.contains(forward.getName())))
                count += countPaths(forward,path);
        }
        return count;
    }
    static long countPathsAgain(Cave cave, HashSet<String> paths)
    {

        if (cave.getName().equals(END))  {
            for(String c: paths){
                System.out.print(c + "->");
            }
            System.out.println();
            return 1;
        }
        HashSet<String> path = (HashSet<String>) paths.clone();


        path.add(
                (path.contains(cave.getName())
                        && cave.large == false) ? "CLOSED-FOR-REPEATS" :  cave.getName()
        );

        long count = 0;
        for (Cave forward : cave.connections)
        {
            if ( !forward.getName().equals("start") &&
                    (forward.large == true ||
                            !path.contains("CLOSED-FOR-REPEATS") ||
                            !paths.contains(forward.getName())) )
            {
                count += countPathsAgain(forward, path);
            }
        }
        return count;
    }


    }

class CollectionOfCaves {
    public final Map<String, Cave> caves = new HashMap<>();
    public CollectionOfCaves(){

    }
    public void addCave(String from, String to){
        Cave fromCave = new Cave(from);
        Cave toCave = new Cave(to);
        if (!caves.containsKey(from)){
            caves.put(from,fromCave);
        }
        if (!caves.containsKey(to)){
            caves.put(to,toCave);
        }
        Cave.connect(caves.get(from),caves.get(to));


    }

}
class Cave{
    public  final List<Cave> connections = new ArrayList<>();
    private final String name;
    public boolean large = false;
    public String getName() { return name;}

    public Cave(String name){
        this.name = name;
        large = Character.isUpperCase(name.charAt(0));
    }

    public static void connect(Cave a, Cave b){
        if (!a.connections.contains(b)){
            a.connections.add(b);
            b.connections.add(a);
        }
    }
}