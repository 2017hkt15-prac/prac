package com.a2017hkt15.sortaddrprac;

import android.graphics.Color;
import android.util.Log;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by YouMin on 2017-08-17.
 */

public class PathBasic {
    private TMapView tmapView;
    private TMapData tmapdata;
    private MarkerController markerController;

    private double[][] distanceArr;
    private int[] pathRoute;

    private int start, end;
    private int pathID;

    public PathBasic(TMapView tmapView, MarkerController markerController) {
        pathID = 0;
        this.tmapView = tmapView;
        tmapdata = new TMapData();
        distanceArr = new double[10][10];
        this.markerController = markerController;
    }

    // 리스트에 있는 마커끼리의 거리를 계산해 거리 배열에 저장
    public void calcDistancePath(ArrayList<TMapMarkerItem> markerList) {
        for (start = 0; start < markerList.size(); start++)
            for (end = 0; end < markerList.size(); end++) {
                if (start == end)
                    distanceArr[start][end] = -1.0;
                else {
                    Log.v("sds",tmapdata.toString());
                    tmapdata.findPathData(markerList.get(start).getTMapPoint(), markerList.get(end).getTMapPoint(), new TMapData.FindPathDataListenerCallback() {
                        @Override
                        public void onFindPathData(TMapPolyLine polyLine) {
                            distanceArr[start][end] = polyLine.getDistance();
                        }
                    });
                }
            }
        CalcPath calcPath = new CalcPath(0, markerController.getMarkerList().size(), distanceArr, markerController.getEndIndex());
        this.showPath(calcPath.pathCalc());
    }

    // 완료된 경로를 지도에 표시
    public void showPath(PathInfo pathInfo) {
        this.setPathRoute(pathInfo.getPathRoute());

        Log.d("xxx", pathInfo.getPathLength() + "");
        for (int xx : pathInfo.getPathRoute())
            Log.d("xxx", Integer.toString(xx));

        ArrayList<TMapMarkerItem> markerList = markerController.getMarkerList();
        // markerController.getPathMarkerList().add(markerList.get(pathRoute[0]));
        for(int cur = 1; cur < markerList.size(); cur++) {
            // markerController.getPathMarkerList().add(markerList.get(pathRoute[cur]));
            tmapdata.findPathData(markerList.get(pathRoute[cur-1]).getTMapPoint(), markerList.get(pathRoute[cur]).getTMapPoint(), new TMapData.FindPathDataListenerCallback() {
                @Override
                public void onFindPathData(TMapPolyLine polyLine) {
                    polyLine.setLineColor(Color.BLUE);
                    tmapView.addTMapPolyLine(pathID + "Route", polyLine);
                    pathID++;
                }
            });
        }
    }

    public int[] getPathRoute() {
        return pathRoute;
    }

    public void setPathRoute(int[] pathRoute) {
        this.pathRoute = pathRoute;
    }

    public void removePath() {
        tmapView.removeTMapPath();
    }

    public double[][] getDistanceArr() {
        return distanceArr;
    }
}
