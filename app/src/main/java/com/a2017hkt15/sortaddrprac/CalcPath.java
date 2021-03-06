package com.a2017hkt15.sortaddrprac;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class CalcPath {
    final int MAX_NODE=10;
    int nodeNum;
    double min;
    int[] minPath;
    double[][] map;
    int start;
    int destination; // if -1 , it doesn't have specific desti.

    public CalcPath()
    {
        nodeNum = 0;
        start=0;
        destination = -1;
        min=987654321;
        minPath=new int[MAX_NODE];
        map=new double[MAX_NODE][MAX_NODE];
    }

    public CalcPath(int start,int nodeNum,double[][] map,int dest)
    {
        this();
        this.start=start;
        this.nodeNum= nodeNum;
        this.destination=dest;
        this.map=map;
    }

    public PathInfo pathCalc()
    {
        PathInfo ret=new PathInfo();


        preCalcDfs(1,new int[10],0);

        ret.setPathLength(min);
        ret.setPathRoute(minPath);

        return ret;
    }

    public double preCalc(int num, int[] ver, double length)
    {
        int[] v=new int[MAX_NODE];
        double ret = length;

        for (int i = 0; i < nodeNum; i++)
        {
            v[i]=1;
        }
        for (int i = 0; i < num; i++)
        {
            v[i]=0;
        }


        for (int i = 0; i < nodeNum; i++)
        {
            if (v[i]==1)
            {
                double m = map[i][0];
                for (int j = 0; j < nodeNum; j++)
                {
                    if (map[i][j] < m)
                    {
                        m = map[i][j];
                    }
                }
                ret += m;
            }
        }

        return ret;
    }

    private double preCalcDfs(int num, int[] ver, double length)
    {
        //도착지가 정해졌는지 않됬는지
        if (num == nodeNum)//일단 도착지 없음
        {
            if (min > length)
            {
                min = length;
                for (int i = 0; i < nodeNum; i++)
                {
                    minPath[i]=ver[i];
                }
            }
            return 1;
        }

        if (preCalc(num,ver,length) >= min)
        {
            return -1;
        }


        for (int i = 0; i < nodeNum; i++)
        {
            boolean flag = true;
            for (int j = 0; j < num; j++)
            {
                if (i == ver[j])
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                ver[num]=i;

                if (length < min)
                {
                    preCalcDfs(num+1, ver, length+map[ver[num-1]][i]);
                }
            }
        }

        return min;
    }
}
