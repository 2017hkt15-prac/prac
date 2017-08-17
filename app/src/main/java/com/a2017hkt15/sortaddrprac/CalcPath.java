package com.a2017hkt15.sortaddrprac;

import java.util.ArrayList;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class CalcPath {
    int min;
    ArrayList<Integer> minPath;
    ArrayList<ArrayList<Integer>> map;

    public CalcPath()
    {
        min=987654321;
        minPath=new ArrayList<Integer>();
        map=new ArrayList<>();
    }

    public CalcPath(int min, ArrayList<ArrayList<Integer>> map)
    {
       this();
    }

    public PathInfo pathCalc()
    {
        PathInfo ret=new PathInfo();

        return ret;
    }

}
