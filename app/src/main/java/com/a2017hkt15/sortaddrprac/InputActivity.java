package com.a2017hkt15.sortaddrprac;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gwmail on 2017-08-15.
 */

public class InputActivity extends AppCompatActivity {
    private MarkerController markerController;
    private PathBasic pathBasic;
    private int button_pos;
    private ListView listview;
    private ListViewAdapter adapter;
    private AddressInfo addressInfo = new AddressInfo(); //AddressInfo class
    private ArrayList<AddressInfo> AddressInfo_array = new ArrayList<>();
    String address_lat_lon;
    float lat;
    float lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
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

        // 마커, 경로 관련 클래스
        markerController = new MarkerController(tmapview, startIcon, passIcon, endIcon);
        pathBasic = new PathBasic(tmapview, markerController);
        Button findButton = (Button) findViewById(R.id.button_find);
        //검색 버튼 클릭
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // 테스트용 좌표
      /*  markerController.setStartMarker(37.566474f, 126.985022f, "start");
        markerController.addMarker(37.566474f, 126.685022f, "test1");
        markerController.addMarker(37.566474f, 126.755022f, "test2");
        markerController.addMarker(37.136474f, 126.985022f, "test3");
        markerController.addMarker(37.536474f, 126.855022f, "test4");*/
        pathBasic.calcDistancePath(markerController.getMarkerList());


    }

    private void addLine() {
        if (Variable.numberOfLine < 10) {
            adapter.addItem("목적지");
        } else {
            Toast.makeText(getApplicationContext(), "최대 갯수에 도달했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    //AutoComplete으로부터 데이터를 받음
    //position, address_name
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
       final int position = intent.getIntExtra("position", 0);
        final String address_name = intent.getStringExtra("address_name");
        // String address_lat_lon;
        addressInfo.setAddr(address_name);
        adapter.getItem(position).setAddrStr(address_name);
        //edittext에 setText
        adapter.notifyDataSetChanged();
        //변경완료
        runOnUiThread(new Runnable() {
            public void run() {
                TMapData tdata = new TMapData();
                tdata.findAllPOI(address_name, new TMapData.FindAllPOIListenerCallback() {
                    @Override
                    public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
                        String[] array;
                        TMapPOIItem item2 = poiItem.get(poiItem.size() - 1);
                        array = item2.getPOIPoint().toString().split(" ");
                        lat = Float.parseFloat(array[1]);
                        addressInfo.setLat(Float.parseFloat(array[1]));
                        lon = Float.parseFloat(array[3]);
                        addressInfo.setLon(lon);
                    }
                });
            }
        });
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                address_lat_lon = address_name + "," + String.valueOf(addressInfo.getLat()) + "," + String.valueOf(addressInfo.getLon());
                AddressInfo_array.add(position,addressInfo);
                if(position==0){
                    markerController.setStartMarker(AddressInfo_array.get(position).getLat(),AddressInfo_array.get(position).getLon(),AddressInfo_array.get(0).getAddr());
                }
                else
                    markerController.addMarker(AddressInfo_array.get(position).getLat(),AddressInfo_array.get(position).getLon(),AddressInfo_array.get(position).getAddr());
            }
        }, 1000);
    }

    public int getButton_pos() {
        return button_pos;
    }

    public void setButton_pos(int button_pos) {
        this.button_pos = button_pos;
    }

    public ArrayList<AddressInfo> getAddressInfo_array() {
        return AddressInfo_array;
    }

    public void setAddressInfo_array(ArrayList<AddressInfo> addressInfo_array) {
        AddressInfo_array = addressInfo_array;
    }

    public MarkerController getMarkerController() {
        return markerController;
    }

    public void setMarkerController(MarkerController markerController) {
        this.markerController = markerController;
    }
}
