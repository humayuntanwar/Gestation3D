package com.example.humayunt.templateui.MainPanel;

import android.database.SQLException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.humayunt.templateui.DataModel.TwodDataModel;

import java.io.IOException;
import java.util.ArrayList;
import com.example.humayunt.templateui.Adapter.*;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;



public class twodguideview extends Fragment implements View.OnClickListener{
    DatabaseHandler db;
    RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.activity_twodguideview, container, false);
            this.list = (RecyclerView) rootView.findViewById(R.id.list_dblayout);
            this.list.setHasFixedSize(true);
            this.list.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

            RetriveDataFromDB();
            return rootView;
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();


        //getting the recyclerview from xml



    }
    protected void RetriveDataFromDB() {
        try {
            this.db = new DatabaseHandler(getActivity());
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


                this.list.setAdapter(new DbRetrieveAdapter(getActivity(), contacts));
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



