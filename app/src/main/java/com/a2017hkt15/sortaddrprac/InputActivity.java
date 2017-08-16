package com.a2017hkt15.sortaddrprac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.skp.Tmap.TMapView;

/**
 * Created by gwmail on 2017-08-15.
 */

public class InputActivity extends AppCompatActivity {
    private MarkerActivity markerActivity;
    private PathActivity pathActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        LinearLayout layoutForMap = (LinearLayout)findViewById(R.id.layout_for_map);
        TMapView tmapview = new TMapView(this);

        tmapview.setSKPMapApiKey("d78cbfb1-f9ee-3742-af96-bf845debb9ab");
        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        tmapview.setTrafficInfo(true);
        layoutForMap.addView(tmapview);
        setContentView(layoutForMap);

        // 현재 위치 마커 아이콘 리소스 불러온 후 적용
        Context context = getApplicationContext();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.point);
        tmapview.setIcon(bitmap);

        // 마커, 경로 관련 클래스 (타 마커 아이콘도 임시로 현재 위치 아이콘 사용)
        markerActivity = new MarkerActivity(tmapview, bitmap);
        pathActivity = new PathActivity();
    }
}
