package com.example.humayunt.templateui.DataModel;

/**
 * Created by HumayunT on 2/26/2018.
 */

public class QuizDataModel {
    private int ID;
    private String QUESTION;
    private String OPTIONA;
    private String OPTIONB;
    private String OPTIONC;
    private String ANSWER;
    public QuizDataModel()
    {

    }

    public int getID()
    {
        return ID;
    }
    public String getQUESTION() {
        return QUESTION;
    }
    public String getOPTIONA() {
        return OPTIONA;
    }
    public String getOPTIONB() {
        return OPTIONB;
    }
    public String getOPTIONC() {
        return OPTIONC;
    }
    public String getANSWER() {
        return ANSWER;
    }
    public void setID(int id)
    {
        ID=id;
    }
    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }
    public void setOPTIONA(String OPTIONA) {
        this.OPTIONA = OPTIONA;
    }
    public void setOPTIONB(String OPTIONB) {
        this.OPTIONB = OPTIONB;
    }
    public void setOPTIONC(String OPTIONC) {
        this.OPTIONC = OPTIONC;
    }
    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }
}
