package com.a2017hkt15.sortaddrprac;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPolyLine;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by YouMin on 2017-08-17.
 */

public class PathActivity {
    private TMapData tmapdata;
    private double[][] distanceArr;

    public PathActivity() {
        tmapdata = new TMapData();
        distanceArr = new double[10][10];
    }

    // 리스트에 있는 마커끼리의 거리를 계산해 거리 배열에 저장
    public void calcDistance(ArrayList<TMapMarkerItem> markerList) {
        for (int start = 0; start < markerList.size(); start++)
            for (int end = 0; end < markerList.size(); end++) {
                if (start == end)
                    distanceArr[start][end] = -1;
                else {
                    try {
                        distanceArr[start][end] = tmapdata.findPathData(markerList.get(start).getTMapPoint(), markerList.get(end).getTMapPoint()).getDistance();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    // 완료된 경로를 지도에 표시 (미구현)
    public void showPath() {

    }

    public double[][] getDistanceArr() {
        return distanceArr;
    }
}
