package com.a2017hkt15.sortaddrprac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        tmapview.setZoomLevel(8);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        tmapview.setTrafficInfo(true);
        layoutForMap.addView(tmapview);

        // 현재 위치 마커 아이콘 리소스 불러온 후 적용
        Context context = getApplicationContext();
        Bitmap startIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
        Bitmap passIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.pass);
        Bitmap endIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.end);


        // 마커, 경로 관련 클래스
        markerActivity = new MarkerActivity(tmapview, startIcon, passIcon, endIcon);
        // pathActivity = new PathActivity();

        markerActivity.setStartMarker(37.570841f, 126.985302f, "test");
        markerActivity.setStartMarker(37.470841f, 126.985302f, "test");
        markerActivity.addMarker(37.680841f, 126.985302f, "testedf");
        markerActivity.addMarker(37.370841f, 126.985302f, "testzxc");
        markerActivity.addMarker(37.870841f, 126.985302f, "testvbb");
        markerActivity.addMarker(37.170841f, 126.985302f, "testqwer");
    }
}
