package com.a2017hkt15.sortaddrprac;

import android.graphics.Bitmap;
import android.util.Log;

import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

/**
 * Created by YouMin on 2017-08-16.
 */

public class MarkerActivity {
    // 현재 체크된 마커 리스트들
    private ArrayList<TMapMarkerItem> markerList;

    // 마커 중심점 위치 (하단, 중앙)
    private float markerCenterDx = 0.5f;
    private float markerCenterDy = 1.0f;

    // 지도 포인터 및 마커 아이콘 설정
    private TMapView tmapView;
    private Bitmap markerIcon;

    public MarkerActivity (TMapView tmapView, Bitmap MarkerIcon) {
        this.tmapView = tmapView;
        this.markerIcon = MarkerIcon;
        this.markerList = new ArrayList<TMapMarkerItem>();
    }

    // 위도, 경도, 마커(장소) 이름
    public void addMarker(float latitude, float longitude, String placeName) {
        // 포인트 지점 인스턴스 생성 후 마커 인스턴스 생성
        TMapPoint placePoint = new TMapPoint(latitude, longitude);
        TMapMarkerItem placeMarker = new TMapMarkerItem();

        // 마커 속성 설정
        placeMarker.setTMapPoint(placePoint);
        placeMarker.setID(placeName);
        placeMarker.setName(placeName);
        placeMarker.setVisible(TMapMarkerItem.VISIBLE);
        placeMarker.setIcon(markerIcon);
        placeMarker.setPosition(markerCenterDx, markerCenterDy);

        // 배열 리스트에 마커 추가 및 지도에 마커 추가
        markerList.add(placeMarker);
        tmapView.addMarkerItem(placeMarker.getID(), placeMarker);
    }

    // Parameter : 지울 마커의 인덱스 번호
    public void removeMarker(int markerIndex) {
        // 해당 번호의 마커를 맵과 배열 리스트에서 삭제.
        tmapView.removeMarkerItem(markerList.get(markerIndex).getID());
        markerList.remove(markerIndex);
    }

    public void removeAllMarker() {
        markerList.removeAll(markerList);
        tmapView.removeAllMarkerItem();
    }

    public ArrayList<TMapMarkerItem> getMarkerList() {
        return markerList;
    }
}
