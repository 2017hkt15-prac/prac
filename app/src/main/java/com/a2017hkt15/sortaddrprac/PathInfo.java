package com.a2017hkt15.sortaddrprac;

import java.util.ArrayList;

/**
 * Created by 함상혁입니다 on 2017-08-17.
 */

public class PathInfo {
    ArrayList<Integer> pathRoute;
    int pathLength;

    public PathInfo()
    {

    }

    public PathInfo(ArrayList<Integer> pr,int pl)
    {
        pathRoute = pr;
        pathLength =pl;
    }
}
