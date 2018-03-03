package com.example.humayunt.templateui.MainPanel;

/**
 * Created by HumayunT on 2/28/2018.
 */
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.humayunt.templateui.Adapter.DoctorListAdapter;
import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Doctorlisttry extends Fragment  implements View.OnClickListener{

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<DoctorDetail> list;
    RecyclerView recycle;
    private RatingBar ratingBar;

    Button b1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listdoctors, container, false);

        String arr[]={Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(getActivity(),arr,111);

        recycle = (RecyclerView)rootView.findViewById(R.id.recycle);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("doctor");
        Log.d("pakistan","pakistan");
       // b1 = (Button) rootView.findViewById(R.id.calldoctor);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<DoctorDetail>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    DoctorDetail value = dataSnapshot1.getValue(DoctorDetail.class);

                    list.add(value);
                  //  Toast.makeText(getActivity(),list.size()+", "+value.getEmail(),Toast.LENGTH_LONG).show();


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
        DoctorDetail doc = new DoctorDetail();
        final String number = String.valueOf(doc.getNumber());
        Toast.makeText(getActivity(),number,Toast.LENGTH_LONG).show();



       /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btn","inside");
                Toast.makeText(getActivity(),number,Toast.LENGTH_LONG).show();
                //  int number = (mylist.getNumber());
                //  String num = String.valueOf(number);

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                else {
                    String dial = "tel:" +number;
                    Intent callintent = new Intent(Intent.ACTION_CALL);
                    callintent.setData(Uri.parse(dial));
                    startActivity(callintent);
                    //context.startActivity(callintent);
                    //context.getApplicationContext().startActivity(callintent);
                }
            }
        });*/

        return rootView;


    }

    @Override
    public void onClick(View v) {



    }
}
