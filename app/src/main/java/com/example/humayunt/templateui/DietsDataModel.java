package com.example.humayunt.templateui;

import android.graphics.Bitmap;

/**
 * Created by HumayunT on 2/13/2018.
 */

public class DietsDataModel {
    private String dietName;
    private String Time;
    private Bitmap img;
    private String month;
    private String Fact;

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getFact() {
        return Fact;
    }

    public void setFact(String fact) {
        Fact = fact;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
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
