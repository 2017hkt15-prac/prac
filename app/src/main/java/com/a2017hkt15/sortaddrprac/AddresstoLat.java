package com.a2017hkt15.sortaddrprac;


import android.util.Log;
import android.view.View;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;

public class AddresstoLat {
    TMapData tMapdata = new TMapData();
    double lat;
    double lon;
    public AddressInfo addressToLat(View v, String address_send) {

        AddressInfo addressInfo = new AddressInfo();

        tMapdata.findAllPOI(address_send, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
                String[] array;
                Log.i("size", String.valueOf(poiItem.size()));
                TMapPOIItem item2 = poiItem.get(poiItem.size() - 1);
                array = item2.getPOIPoint().toString().split(" ");
                Log.i("lat", array[1]);
                Log.i("lon", array[3]);
                lat = Double.parseDouble(array[1]);
                lon = Double.parseDouble(array[3]);
            }
        });
        addressInfo.setAddr(address_send);
        addressInfo.setLat(lat);
        addressInfo.setLon(lon);

        return addressInfo;
    }
}
