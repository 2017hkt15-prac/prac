package com.a2017hkt15.sortaddrprac;

import java.util.ArrayList;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class PathInfo {
    private int[] pathRoute;
    private double pathLength;

    public PathInfo()
    {

    }

    public PathInfo(int[] pr,double pl)
    {
        pathRoute = pr;
        pathLength =pl;
    }


    public int[] getPathRoute() {
        return pathRoute;
    }

    public void setPathRoute(int[] pathRoute) {
        this.pathRoute = pathRoute;
    }

    public double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }
}
