package com.example.humayunt.templateui.MainPanel;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.humayunt.templateui.DataModel.TwodDataModel;

import java.io.IOException;
import java.util.ArrayList;
import com.example.humayunt.templateui.Adapter.*;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;


public class twodguideview extends AppCompatActivity  implements View.OnClickListener{
    DatabaseHandler db;
    RecyclerView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twodguideview);
        //getting the recyclerview from xml

        this.list = (RecyclerView) findViewById(R.id.list_dblayout);
        this.list.setHasFixedSize(true);
        this.list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RetriveDataFromDB();

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
                ArrayList<TwodDataModel> menuItems = new ArrayList();
                menuItems.clear();
                ArrayList<TwodDataModel> contacts = this.db.getInputMonths();
                Log.e("Array", ">>" + menuItems.size());


                this.list.setAdapter(new DbRetrieveAdapter(this, contacts));
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (IOException e2) {
            throw new Error("Unable to create database");
        }
    }

    @Override
    public void onClick(View v) {

    }
}



