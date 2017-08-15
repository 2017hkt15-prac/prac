package com.a2017hkt15.sortaddrprac;

import android.content.Intent;
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

    }
}
