package com.example.humayunt.templateui.MainPanel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.DataModel.UserDetail;
import com.example.humayunt.templateui.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocateDoctorMap extends Fragment implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    Button ShowDoc;


    private FirebaseDatabase firebaseDatabase;
    private String UserId;
    private DatabaseReference databaseUserRef;
    private FirebaseAuth firebaseAuth;
    Marker marker;
    List<DoctorDetail> list;
    private SupportMapFragment fragment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.activity_locate_doctor_map,container,false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        databaseUserRef = firebaseDatabase.getInstance().getReference("doctor");
        databaseUserRef.push().setValue(marker);



        return view;

    }

    public LocateDoctorMap() {
    }

    @Override
    public void onStart() {
        super.onStart();
       // setContentView(R.layout.activity_locate_doctor_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

      /*  ShowDoc = (Button) getView().findViewById(R.id.locate_doctor);
        ShowDoc.setOnClickListener(this);*/




      //  firebaseAuth = FirebaseAuth.getInstance();
      //  FirebaseUser user = firebaseAuth.getCurrentUser();
       // UserId = user.getUid().toString();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMapLongClickListener(this);

        try{
            databaseUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    list = new ArrayList<DoctorDetail>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        DoctorDetail docDetail = ds.getValue(DoctorDetail.class);
                        LatLng newLocation = new LatLng(
                                docDetail.getLatitude(),
                              docDetail.getLongitude()
                        );
                       // Toast.makeText(getActivity(),docDetail.getLatitude().toString(),Toast.LENGTH_LONG).show();
                        mMap.addMarker(new MarkerOptions()
                                .title(docDetail.getName()+  "  Clinic: " + docDetail.getClinic())
                                .position(newLocation)
                                      //  .snippet(docDetail.getName())
                                );
                        // showing information about that place.


                    }
                   // mMap.animateCamera(CameraUpdateFactory.zoomBy(10));


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            }); }
        catch (Exception e ){
            Toast.makeText(getActivity(),e.toString(), Toast.LENGTH_LONG ).show();
            // Toast.makeText(getContext(), UserId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
