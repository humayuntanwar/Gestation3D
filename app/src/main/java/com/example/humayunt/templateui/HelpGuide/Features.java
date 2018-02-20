package com.example.humayunt.templateui.HelpGuide;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.Adapter.DietsRetriever;
import com.example.humayunt.templateui.Adapter.FeatureRetriever;
import com.example.humayunt.templateui.DataModel.DietsDataModel;
import com.example.humayunt.templateui.DataModel.featureDataModel;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;

import java.io.IOException;
import java.util.ArrayList;

public class Features extends AppCompatActivity {
    DatabaseHandler db;
    RecyclerView list;

    public Features() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

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
                ArrayList<featureDataModel> menuItems = new ArrayList();
                menuItems.clear();
                ArrayList<featureDataModel> contacts = this.db.getFeature();
                Log.e("Array", ">>" + menuItems.size());
                this.list.setAdapter(new FeatureRetriever(this, contacts));
            } catch (SQLException sqle) {
                throw sqle;
            }
        } catch (IOException e2) {
            throw new Error("Unable to create database");
        }
    }


}


