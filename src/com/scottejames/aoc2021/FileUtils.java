package com.scottejames.aoc2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private List<String> fileData = new ArrayList<>();

    public FileUtils(String fileName) {
        InputStream resourceAsStream = FileUtils.class.getResourceAsStream("/" + fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String input;
            while ((input = br.readLine()) != null) {
                fileData.add(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public List<String> getFileAsList() {
        return fileData;
    }

    public List<Integer> getFileAsIntegerList() {
        List<Integer> result = new ArrayList<Integer>();
        for (String data : fileData){
            result.add(Integer.parseInt(data));
        }
        return result;
    }
}
