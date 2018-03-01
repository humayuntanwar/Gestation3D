package com.example.humayunt.templateui;

/**
 * Created by HumayunT on 2/28/2018.
 */
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Doctorlisttry extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<DoctorDetail> list;
    RecyclerView recycle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listdoctors, container, false);
        recycle = (RecyclerView)rootView.findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("doctor");
        Log.d("pakistan","pakistan");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<DoctorDetail>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    DoctorDetail value = dataSnapshot1.getValue(DoctorDetail.class);

                    list.add(value);
                    Toast.makeText(getActivity(),list.size()+", "+value.getEmail(),Toast.LENGTH_LONG).show();


                }
                //Log.d("kk",list.get(0))
                DoctorListAdapter recyclerAdapter = new DoctorListAdapter(list,getActivity().getApplicationContext());
                // this.recycle.setLinearLayoutManager(getActivity().getApplicationContext());
                recycle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                // recycle.setLayoutManager(recyce);
                // recycle.setItemAnimator( new DefaultItemAnimator());
                // recycle.setHasFixedSize(true);
                recycle.setAdapter(recyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


        return rootView;


    }
}
