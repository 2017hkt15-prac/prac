package com.a2017hkt15.sortaddrprac;

import java.util.ArrayList;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class CalcPath {
    int nodeNum;
    int min;
    ArrayList<Integer> minPath;
    ArrayList<ArrayList<Integer>> map;
    int start;
    int destination; // if -1 , it doesn't have specific desti.

    public CalcPath()
    {
        nodeNum = 0;
        start=0;
        destination = -1;
        min=987654321;
        minPath=new ArrayList<Integer>();
        map=new ArrayList<>();
    }

    public CalcPath(int start,int nodeNum,ArrayList<ArrayList<Integer>> map,int dest)
    {
        this();
        this.start=start;
        this.nodeNum= nodeNum;
        this.destination=dest;
        this.map=map;
    }

    public PathInfo pathCalc(int num, ArrayList<Integer> ver, int length)
    {
        PathInfo ret=new PathInfo();


        preCalcDfs(1,new ArrayList<Integer>(),0);

        ret.setPathLength(min);
        ret.setPathRoute(minPath);

        return ret;
    }

    public int preCalc(int num, ArrayList<Integer> ver, int length)
    {
        ArrayList<Integer> v=new ArrayList<>();
        int ret = length;

        for (int i = 0; i < nodeNum; i++)
        {
            v.add(1);
        }
        for (int i = 0; i < num; i++)
        {
            v.set(i,0);
        }


        for (int i = 0; i < nodeNum; i++)
        {
            if (v.get(i)==1)
            {
                int m = map.get(i).get(0);
                for (int j = 0; j < nodeNum; j++)
                {
                    if (map.get(i).get(j) < m)
                    {
                        m = map.get(i).get(j);
                    }
                }
                ret += m;
            }
        }

        return ret;
    }

    private int preCalcDfs(int num, ArrayList<Integer> ver, int length)
    {
        //도착지가 정해졌는지 않됬는지
        if (num == nodeNum)//일단 도착지 없음
        {
            if (min > length)
            {
                min = length;
                for (int i = 0; i < nodeNum; i++)
                {
                    minPath.set(i,ver.get(i));
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
                if (i == ver.get(j))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                ver.set(num,i);

                if (length < min)
                {
                    preCalcDfs(num+1, ver, length+map.get(ver.get(num-1)).get(i));
                }
            }
        }

        return min;
    }
}
