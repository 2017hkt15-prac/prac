package com.a2017hkt15.sortaddrprac;

import java.util.ArrayList;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class PathInfo {
    private ArrayList<Integer> pathRoute;
    private int pathLength;

    public PathInfo()
    {

    }

    public PathInfo(ArrayList<Integer> pr,int pl)
    {
        pathRoute = pr;
        pathLength =pl;
    }


    public ArrayList<Integer> getPathRoute() {
        return pathRoute;
    }

    public void setPathRoute(ArrayList<Integer> pathRoute) {
        this.pathRoute = pathRoute;
    }

    public int getPathLength() {
        return pathLength;
    }

    public void setPathLength(int pathLength) {
        this.pathLength = pathLength;
    }
}
