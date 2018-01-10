package com.example.humayunt.templateui;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by HumayunT on 11/22/2017.
 */

public class placedetails {
    private  String name;
    private  String address;
    private LatLng latlng;
    public  placedetails(){

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public placedetails(LatLng latlng) {
        this.latlng = latlng;
    }



}

