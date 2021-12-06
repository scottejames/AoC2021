package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Grid<T> {
    private HashMap<Point, T> _data = new HashMap<>();
    private int width = 0;
    private int height = 0;

    public void Grid() {
    }

    public void add(Point p, T value) {
        if (p.x > width) width = p.x;
        if (p.y > height) height = p.y;
        _data.put(p, value);
    }
    public T get(Point p) {
        if (p.x > width) width = p.x;
        if (p.y > height) height = p.y;
        return _data.get(p);
    }

    public List<Point> getRowPoints(int row) {
        List<Point> result = new ArrayList<>();

        for (int i = 0; i <= width; i++) {
            result.add(new Point(i, row));
        }
        return result;
    }

    public List<Point> getColumnPoints(int column) {
        List<Point> result = new ArrayList<>();

        for (int i = 0; i <= height; i++) {
            result.add(new Point(column, i));
        }
        return result;
    }

    public List<T> getRow(int r){
        List<T> result = new ArrayList<>();
        List<Point> rowPoints = getRowPoints(r);
        for (Point p : rowPoints){
            result.add(_data.get(p));
        }
        return result;
    }

    public List<T> getColumn(int c){
        List<T> result = new ArrayList<>();
        List<Point> colPoints = getColumnPoints(c);
        for (Point p : colPoints) {
            result.add(_data.get(p));
        }
        return result;
    }

    public List<Point> exists (T e){
        List<Point> result = new ArrayList<>();
        for (Point p: _data.keySet()){
            if (_data.get(p) == e){
                result.add(p);
            }
        }
        return result;
    }

    public void setListOfPoints(List<Point> points, T e){
        for (Point p : points) {
            _data.put(p,e);
        }
    }

    public int getWidth() {
        return width;
    }



    public int getHeight() {
        return height;
    }



    public void showGrid() {

        for (int y = 0; y <= height; y++) {
            for (int x = 0; x <= width; x++) {
                T value = _data.get(new Point(x, y));
                String show;
                if (value == null) show = ".";
                else show = value.toString();
                System.out.print(show + " ");
            }
            System.out.println("");
        }
    }

    public List<T> getAllData(){
        var results = new ArrayList<T>();
        for (Point key: _data.keySet()){
            results.add(_data.get(key));
        }
        return results;
    }


}
