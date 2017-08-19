package com.a2017hkt15.sortaddrprac;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;

public class AutoCompleteActivity extends AppCompatActivity {
    TMapData tMapdata = new TMapData();
    String address_send;
    double lat;
    double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        //툴바 세팅
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_auto_complete);
        setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.app_name);
        String subtitle = "목적지 입력: 검색 후 입력 클릭";
        toolbar.setSubtitle(subtitle);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(ContextCompat.getColor(AutoCompleteActivity.this, R.color.colorSubtitle));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button button = (Button) findViewById(R.id.button);

        TMapView tmapview = new TMapView(this);
        Log.i("check", "시작11");
        tmapview.setSKPMapApiKey("d78cbfb1-f9ee-3742-af96-bf845debb9ab");
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {

        final ArrayList<String> addressList = new ArrayList<>();
        String address;
        final EditText editText = (EditText) findViewById(R.id.edit);
        address = editText.getText().toString();

        Log.i("check123", address);          //장소 입력
        tMapdata.autoComplete(address, new TMapData.AutoCompleteListenerCallback() {
            @Override
            public void onAutoComplete(ArrayList<String> poiltem) {
                for (int i = 0; i < poiltem.size(); i++) {
                    Log.i("check", poiltem.get(i));
                    addressList.add(poiltem.get(i));
                    Log.i("size 확인", String.valueOf(addressList.size()));
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        ArrayAdapter<String> Adapter;
                        Adapter = new ArrayAdapter<String>(AutoCompleteActivity.this, android.R.layout.simple_list_item_1, addressList);
                        ListView list = (ListView) findViewById(R.id.list);
                        Adapter.notifyDataSetChanged();
                        list.setAdapter(Adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(AutoCompleteActivity.this, addressList.get(position), Toast.LENGTH_SHORT).show();
                                editText.setText(addressList.get(position));
                                address_send = editText.getText().toString();
                            }
                        });
                    }
                });
            }
        });
    }

    public void onPass(View v) {
        //address_send 검색해서 받아온 주소이름
        Log.i("onPass", address_send);
        Intent intent = new Intent(AutoCompleteActivity.this,InputActivity.class);
        //address_send 주소이름을 inputActivity로 보냄
        intent.putExtra("address_name",address_send);
        setResult(RESULT_OK,intent);
        finish();

       // intent.putExtra(get_intent.getExtras("position"));
       /* Intent intent = new Intent(AutoComplete.this, InputActivity.class);
        intent.putExtra("address_name", address_send);
        setResult(RESULT_OK, intent);
        finish();*/
      /*  tMapdata.findAllPOI(address_send, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
                Log.i("클릭","클릭");
                String[] array;
                Log.i("size",String.valueOf(poiItem.size()));
                TMapPOIItem item2 = poiItem.get(poiItem.size()-1);
                array = item2.getPOIPoint().toString().split(" ");
                Log.i("lat",array[1]);
                Log.i("lon",array[3]);
                lat = Double.parseDouble(array[1]);
                lon = Double.parseDouble(array[3]);
            }
        });*/
    }
}
