package com.example.humayunt.templateui.MainPanel;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.humayunt.templateui.CustomWindow;
import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.DataModel.UserDetail;
import com.example.humayunt.templateui.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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
    ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_locate_doctor_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        databaseUserRef = firebaseDatabase.getInstance().getReference("doctor");
        databaseUserRef.push().setValue(marker);
        pd = new ProgressDialog(getActivity());



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
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null)
        {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        try{
            pd.setMessage("Loading..");
            pd.show();
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
                                .title(docDetail.getName())
                                .position(newLocation)
                                      //  .snippet(docDetail.getName())
                                );
                        pd.dismiss();

                        // showing information about that place.


                    //    mMap.setInfoWindowAdapter(new CustomWindow(getActivity()));
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
