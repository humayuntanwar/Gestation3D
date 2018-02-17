package com.example.humayunt.templateui.MainPanel;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.humayunt.templateui.Adapter.ExerciseRetriever;
import com.example.humayunt.templateui.DataModel.ExerciseDataModel;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;

import java.io.IOException;
import java.util.ArrayList;

public class excercises extends AppCompatActivity {

    DatabaseHandler db;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercises);
        this.list = (RecyclerView) findViewById(R.id.Exercise);
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
                ArrayList<ExerciseDataModel> menuItems = new ArrayList();
                menuItems.clear();
                ArrayList<ExerciseDataModel> contacts = this.db.getExcercise();
                Log.e("Array", ">>" + menuItems.size());
                this.list.setAdapter(new ExerciseRetriever(this, contacts));
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (IOException e2) {
            throw new Error("Unable to create database");
        }
    }

}
