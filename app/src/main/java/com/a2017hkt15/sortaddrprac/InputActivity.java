package com.a2017hkt15.sortaddrprac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skp.Tmap.TMapView;

import java.util.ArrayList;

/**
 * Created by gwmail on 2017-08-15.
 */

public class InputActivity extends AppCompatActivity {
    private MarkerController markerController;
    private PathBasic pathBasic;

    private ListView listview;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //툴바 세팅
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_input);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
        String subtitle = "목적지 입력: 10개까지 입력 가능";
        toolbar.setSubtitle(subtitle);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(ContextCompat.getColor(InputActivity.this, R.color.colorSubtitle));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        LinearLayout layoutForMap = (LinearLayout) findViewById(R.id.layout_for_map);
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
//      setContentView(layoutForMap);

        // 지도를 큰 화면으로 보기 위한 레이아웃
        /*
        RelativeLayout relativeLayout = new RelativeLayout(this);
        TMapView tmapview = new TMapView(this);
        tmapview.setSKPMapApiKey("d78cbfb1-f9ee-3742-af96-bf845debb9ab");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(10);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(true);
        tmapview.setTrackingMode(true);
        relativeLayout.addView(tmapview);
        setContentView(relativeLayout);
        */

        // Adapter 생성
        adapter = new ListViewAdapter(this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.wayListView1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        // 첫 번째 아이템 추가.
        adapter.addItem("출발지");

        addLine();
        addLine();
        addLine();

        ImageButton addimageButton = (ImageButton) findViewById(R.id.addimageButton);
        addimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLine();
            }
        });


        // 현재 위치 마커 아이콘 리소스 불러온 후 적용
        Context context = getApplicationContext();
        Bitmap startIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.start);
        Bitmap passIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.pass);
        Bitmap endIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.end);

        Button findButton = (Button) findViewById(R.id.button_find);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 마커, 경로 관련 클래스
        markerController = new MarkerController(tmapview, startIcon, passIcon, endIcon);
        pathBasic = new PathBasic(tmapview, markerController);


        // 테스트용 좌표
        markerController.setStartMarker(37.566474f, 126.985022f, "start");
        markerController.addMarker(37.566474f, 126.685022f, "test1");
        markerController.addMarker(37.566474f, 126.755022f, "test2");
        markerController.addMarker(37.136474f, 126.985022f, "test3");
        markerController.addMarker(37.536474f, 126.855022f, "test4");

        pathBasic.calcDistancePath(markerController.getMarkerList());


    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_bt1:
                //TODO::최종 목적지 설정
                break;
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addLine() {
        if (Variable.numberOfLine < 10) {
            adapter.addItem("목적지");
        } else {
            Toast.makeText(getApplicationContext(), "최대 갯수에 도달했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == RESULT_OK) {
            int position;
            Log.i("position2", "position");

            adapter.getItem(intent.getIntExtra("position", 0)).setAddrStr(intent.getStringExtra("address_name"));
            position = intent.getIntExtra("position", 0);
            Log.i("position", String.valueOf(position));
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == RESULT_CANCELED) {

        }
    }
}
