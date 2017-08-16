package com.a2017hkt15.sortaddrprac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.skp.Tmap.TMapView;

public class MainActivity extends AppCompatActivity {
    private String ApiKey = "1580079a-acc2-307f-b1bb-979bf55fc810";
    private MarkerActivity markerActivity;
    private PathActivity pathActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 지도 데이터 호출 및 설정
        RelativeLayout relativeLayout = new RelativeLayout(this);
        TMapView tmapview = new TMapView(this);
        tmapview.setSKPMapApiKey("1580079a-acc2-307f-b1bb-979bf55fc810");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(10);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(true);
        tmapview.setTrackingMode(true);

        // 현재 위치 마커 아이콘 리소스 불러온 후 적용
        Context context = getApplicationContext();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.point);
        tmapview.setIcon(bitmap);

        // 마커, 경로 관련 클래스
        markerActivity = new MarkerActivity(tmapview, bitmap);
        pathActivity = new PathActivity();


        relativeLayout.addView(tmapview);
        setContentView(relativeLayout);
    }
}
