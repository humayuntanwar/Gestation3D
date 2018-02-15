package com.example.humayunt.templateui;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class diets extends AppCompatActivity {
    DatabaseHandler db;
    RecyclerView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diets);


        this.list = (RecyclerView) findViewById(R.id.recyclerView);
        this.list.setHasFixedSize(true);
        this.list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RetriveDataFromDB();
        //initializing the productlist

    }
    protected void RetriveDataFromDB() {
        try {
            this.db = new DatabaseHandler(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.db.createdatabase();
            try {
                this.db.opendatabase();
                ArrayList<DietsDataModel> menuItems = new ArrayList();
                menuItems.clear();
                ArrayList<DietsDataModel> contacts = this.db.getDiets();
                Log.e("Array", ">>" + menuItems.size());
                this.list.setAdapter(new DietsRetriever(this, contacts));
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (IOException e2) {
            throw new Error("Unable to create database");
        }
    }


}
