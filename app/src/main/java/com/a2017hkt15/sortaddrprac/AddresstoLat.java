package com.a2017hkt15.sortaddrprac;


import android.util.Log;
import android.view.View;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPOIItem;

import java.util.ArrayList;

public class AddresstoLat {
    TMapData tMapdata = new TMapData();
    float lat;
    float lon;
    public AddressInfo addressToLat(View v, String address_send) {

        AddressInfo addressInfo = new AddressInfo();

        tMapdata.findAllPOI(address_send, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
                String[] array;
                Log.i("size", String.valueOf(poiItem.size()));
                TMapPOIItem item2 = poiItem.get(poiItem.size() - 1);
                array = item2.getPOIPoint().toString().split(" ");
                lat = Float.parseFloat(array[1]);
                lon =  Float.parseFloat(array[3]);
            }
        });
        addressInfo.setAddr(address_send);
        addressInfo.setLat(lat);
        addressInfo.setLon(lon);

        return addressInfo;
    }
}
