package com.example.humayunt.templateui;

import android.graphics.Bitmap;

/**
 * Created by HumayunT on 2/13/2018.
 */

public class DietsDataModel {
    private String foodtype;

    private Bitmap img;
    private String month;
    private String prohibitedfood;


    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public String getProhibitedfood() {
        return prohibitedfood;
    }

    public void setProhibitedfood(String prohibitedfood) {
        this.prohibitedfood = prohibitedfood;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }



    public DietsDataModel() {
    }
}
