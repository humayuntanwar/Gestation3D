package com.example.humayunt.templateui;

import android.graphics.Bitmap;

public class TwodDataModel {

    private String _name;
    private String fact;
    private Bitmap img;
    private String information;
    private String month;

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getInformation() {
        return this.information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getFact() {
        return this.fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Bitmap getImg() {
        return this.img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public TwodDataModel() {
    }


}
