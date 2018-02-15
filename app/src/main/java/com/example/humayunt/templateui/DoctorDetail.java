package com.example.humayunt.templateui;

/**
 * Created by HumayunT on 12/28/2017.
 */

public class DoctorDetail {
    private String name, email,password,address;
    private Double latitude,Longitude;
    private int number;

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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DoctorDetail(String name, String email, String password, Double latitude, Double longitude, int number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        Longitude = longitude;
        this.number = number;
    }

    public DoctorDetail() {
    }

    public  String getName() {
        return name;
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public int getNumber() {
        return number;
    }
}
