package com.example.humayunt.templateui.MainPanel;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
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

public class LocateDoctorMap extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    Button ShowDoc;


    private FirebaseDatabase firebaseDatabase;
    private String UserId;
    private DatabaseReference databaseUserRef;
    private FirebaseAuth firebaseAuth;
    Marker marker;
    List<DoctorDetail> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_doctor_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ShowDoc = (Button) findViewById(R.id.locate_doctor);
        ShowDoc.setOnClickListener(this);
        firebaseDatabase = FirebaseDatabase.getInstance();




      //  firebaseAuth = FirebaseAuth.getInstance();
      //  FirebaseUser user = firebaseAuth.getCurrentUser();
       // UserId = user.getUid().toString();
        databaseUserRef = firebaseDatabase.getReference("doctor");
        databaseUserRef.push().setValue(marker);

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
                        Toast.makeText(getApplicationContext(),docDetail.getLatitude().toString(),Toast.LENGTH_LONG).show();
                        mMap.addMarker(new MarkerOptions()
                                .position(newLocation)
                                );
                    }
                   // mMap.animateCamera(CameraUpdateFactory.zoomBy(10));


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            }); }
        catch (Exception e ){
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG ).show();
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
