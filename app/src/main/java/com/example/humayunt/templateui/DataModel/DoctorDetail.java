package com.example.humayunt.templateui.DataModel;

/**
 * Created by HumayunT on 12/28/2017.
 */

public class DoctorDetail {
    private String name;
    private String email;
    private String password;
    private String address;
    private String clinic;
    private String UserId;
    private float rating;
    private int numberOfRating;





    private Double latitude,Longitude;
    private String number;
    public float getRating() {
        return rating;
    }

    public DoctorDetail(float rating, int numberOfRating) {
        this.rating = rating;
        this.numberOfRating = numberOfRating;
    }

    public int getNumberOfRating() {
        return numberOfRating;
    }

    public void setNumberOfRating(int numberOfRating) {
        this.numberOfRating = numberOfRating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public DoctorDetail() {
    }

    public DoctorDetail(String name, String email, String password, String address, String clinic, String userId, Double latitude, Double longitude, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.clinic = clinic;
        UserId = userId;
        this.latitude = latitude;
        Longitude = longitude;
        this.number = number;
    }

    public DoctorDetail(String name, String email, String password, String clinic, Double latitude, Double longitude, String number) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clinic = clinic;
        this.latitude = latitude;
        Longitude = longitude;
        this.number = number;
    }

    public DoctorDetail(String name, String email, String password, String clinicc, Double latitude, Double longitude, String number, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.clinic = clinicc;
        this.latitude = latitude;
        Longitude = longitude;
        this.number = number;
        this.address = address;
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

    public  Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public String getNumber() {
        return number;
    }
}
