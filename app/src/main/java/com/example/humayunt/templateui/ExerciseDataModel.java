package com.example.humayunt.templateui;

import android.graphics.Bitmap;

/**
 * Created by HumayunT on 2/13/2018.
 */

public class ExerciseDataModel {
    private String name;
    private String startingpos;
    private Bitmap img;
    private String month;
    private String steps,repition,precaution;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartingpos() {
        return startingpos;
    }

    public void setStartingpos(String startingpos) {
        this.startingpos = startingpos;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getRepition() {
        return repition;
    }

    public void setRepition(String repition) {
        this.repition = repition;
    }

    public String getPrecaution() {
        return precaution;
    }

    public void setPrecaution(String precaution) {
        this.precaution = precaution;
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

}
