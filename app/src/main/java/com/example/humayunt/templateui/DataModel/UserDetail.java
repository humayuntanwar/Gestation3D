package com.example.humayunt.templateui.DataModel;

/**
 * Created by HumayunT on 10/25/2017.
 */

public class UserDetail {
    private String name;
    private  String email;
    private  String password;
    private String address;
    private String doctor;
    private  Double latitude,logitude;

    public UserDetail(String name, String email, String password, Double latitude, Double logitude) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.logitude = logitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }

    public UserDetail(){

    }

    public UserDetail(String name, String email, String password, String address, String doctor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.doctor = doctor;
    }

    public UserDetail(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public UserDetail(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public UserDetail(String name, String email) {
        this.name = name;

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getDoctor() {
        return doctor;
    }


}