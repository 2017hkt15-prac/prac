package com.a2017hkt15.sortaddrprac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.skp.Tmap.TMapView;

/**
 * Created by gwmail on 2017-08-15.
 */

public class InputActivity extends AppCompatActivity {

    private ListView listview ;
    private ListViewAdapter adapter;

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
//        setContentView(layoutForMap);


        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.wayListView1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        //adapter.addItem("출발지", "", ContextCompat.getDrawable(getApplicationContext(), R.drawable.delete_640));
        adapter.addItem("출발지");

        addLine(); addLine(); addLine();

        ImageButton addimageButton = (ImageButton)findViewById(R.id.addimageButton);
        addimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLine();
            }
        });
    }

    private void addLine() {
        if(Variable.numberOfLine < 10) {
            adapter.addItem("목적지" + Variable.numberOfLine);
            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(getApplicationContext(),"최대 갯수에 도달했습니다.",Toast.LENGTH_LONG).show();
        }
    }
}
