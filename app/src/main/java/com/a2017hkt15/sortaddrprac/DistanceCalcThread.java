package com.a2017hkt15.sortaddrprac;


import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by YouMin on 2017-08-19.
 */

public class DistanceCalcThread extends Thread {
    private TMapData tmapdata;

    private TMapPoint[][][] pathPoint;
    private double[][] distanceArr;
    private int pointSize;


    DistanceCalcThread(TMapData tmapdata) {
        super();
        this.tmapdata = tmapdata;
        this.distanceArr = new double[10][10];
        this.pathPoint = new TMapPoint[10][10][2];
    }
    public void run() {
        synchronized (this) {
            try {
                for (int start = 0; start < pointSize; start++) {
                    for (int end = start + 1; end < pointSize; end++) {
                        this.distanceArr[start][end] = tmapdata.findPathData(pathPoint[start][end][0], pathPoint[start][end][1]).getDistance();
                        this.distanceArr[end][start] = this.distanceArr[start][end];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (RuntimeException var14) {
                var14.printStackTrace();
                throw var14;
            } catch (Exception var15) {
                var15.printStackTrace();
            }
            notify();
        }
    }

    public void setPoint(TMapPoint[][][] pathPoint, int pointSize) {
        this.pathPoint = pathPoint;
        this.pointSize = pointSize;
    }

    public double[][] getDistanceArr() {
        return distanceArr;
    }
}