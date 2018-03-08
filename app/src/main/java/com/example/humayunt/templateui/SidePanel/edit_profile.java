package com.example.humayunt.templateui.SidePanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.humayunt.templateui.AutoCompletePlace.PlaceAutocompleteAdapter;
import com.example.humayunt.templateui.AutoCompletePlace.placedetails;
import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.DataModel.UserDetail;
import com.example.humayunt.templateui.R;
import com.example.humayunt.templateui.UserProfile;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.concurrent.Executor;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link edit_profile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link edit_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class edit_profile extends Fragment implements View.OnClickListener {


    private static final String TAG = "";
    // private OnFragmentInteractionListener mListener;
    private EditText EditInputName, EditInputEmail, EditInputNumber, EditInputClinic;
    private AutoCompleteTextView EditInputAddress;
    private Button saveProfile;
    private ProgressDialog progressdialog;
    private Double longitude,latitude;


    private String addres, buttonAddress;
    private Geocoder geocoder;
    private PlaceAutocompleteAdapter mPlaceAutocompleAdapter;
    private GoogleApiClient mGoogleApiClient;
    protected GeoDataClient mGeoDataClient;
    private com.example.humayunt.templateui.AutoCompletePlace.placedetails placedetails;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private FirebaseDatabase firebaseDatabase;
    private String UserId;
    private DatabaseReference databaseUserRef;
    private FirebaseAuth firebaseAuth;
    String NewUserId;
    String DocID, PatientID;
    public edit_profile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\

        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
         PatientID = getArguments().getString("passPatientEdit");
        DocID = getArguments().getString("passDocEdit");


        EditInputName = (EditText) v.findViewById(R.id.edit_input_name);
       // EditInputEmail = (EditText) v.findViewById(R.id.edit_email);
        EditInputClinic = (EditText) v.findViewById(R.id.edit_input_clinic);
        EditInputNumber = (EditText) v.findViewById(R.id.edit_input_number);
        EditInputAddress = (AutoCompleteTextView) v.findViewById(R.id.edit_address);
        saveProfile = (Button) v.findViewById(R.id.save_Profile);
        firebaseDatabase = FirebaseDatabase.getInstance();



//       firebaseauth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid().toString();
        try{
            if( DocID != null || DocID.equals(UserId)){
                  Log.i("doctor",DocID);
                NewUserId = DocID;

                databaseUserRef = firebaseDatabase.getReference("doctor");
            }
        }
        catch (NullPointerException e ){
            e.printStackTrace();

            try {
                if (PatientID != null || PatientID.equals(UserId))
                    NewUserId = PatientID;
                databaseUserRef = firebaseDatabase.getReference("users");
                EditInputClinic.setVisibility(View.INVISIBLE);
                EditInputNumber.setVisibility(View.INVISIBLE);
            }
            catch (NullPointerException f ){
                f.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Log.d(TAG,UserId );
       // Toast.makeText(getContext(), UserId, Toast.LENGTH_LONG).show();
        try{
        databaseUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
//                Toast.makeText(getActivity(), "Method ccalled..", Toast.LENGTH_LONG).show();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Log.i("user ids..",ds.getKey());
                    try {
                        if (ds.getKey().equals("111"+DocID)) {

                            Log.i("user data..", ds.getValue().toString());
                            Log.d(TAG, ds.toString());

                            DoctorDetail doctorDetail = ds.getValue(DoctorDetail.class);

                            //display all information
                            Toast.makeText(getActivity(), doctorDetail.getName().toString(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, "ShowData :email " + doctorDetail.getEmail());
                            Log.d(TAG, "ShowData :name " + doctorDetail.getName());
                           // EditInputEmail.setText(doctorDetail.getEmail().toString());
                            EditInputName.setText(doctorDetail.getName().toString());
                            EditInputClinic.setText(doctorDetail.getClinic().toString());
                            EditInputNumber.setText(String.valueOf(doctorDetail.getNumber()));
                            EditInputAddress.setText(doctorDetail.getAddress().toString());


                        }

                    }
                    catch (NullPointerException g){
                        g.printStackTrace();

                    }
                    try {


                        if(ds.getKey().equals(PatientID)) {
                            Log.i("user", ds.toString());
                            // Toast.makeText(this, User.getName().toString(), Toast.LENGTH_LONG).show();

                            UserDetail UserDetail = ds.getValue(UserDetail.class);
                            Toast.makeText(getActivity(), UserDetail.getName().toString(), Toast.LENGTH_LONG).show();
                            Log.i(TAG, "ShowData :email " + UserDetail.getEmail());
                            Log.i(TAG, "ShowData :name " + UserDetail.getName());
                            EditInputAddress.setText(UserDetail.getAddress().toString());
                            EditInputName.setText(UserDetail.getName().toString());
                        }

                    }
                    catch (NullPointerException h){
                        h.printStackTrace();
                    }


                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); }
        catch (NullPointerException e){
            e.printStackTrace();
        }

        saveProfile.setOnClickListener(this);
        return v;


    }


    @Override
    public void onClick(View v) {
        if (saveProfile.isPressed()) {
          updateProfile();
        }

    }


    private void updateProfile() {

        final String name = EditInputName.getText().toString().trim();
      //  final String email = EditInputEmail.getText().toString().trim();
       final String clinic = EditInputClinic.getText().toString().trim();
        final String number = EditInputNumber.getText().toString().trim();
        final String address = EditInputAddress.getText().toString().trim() ;
       // System.out.println(email + password);

        //mPlaceAutocompleAdapter = new PlaceAutocompleteAdapter(this,mGeoDataClient, LAT_LNG_BOUNDS, null);
        // signupInputAddress.setAdapter(mPlaceAutocompleAdapter);

      /*  if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(getContext(), "enter email ", Toast.LENGTH_SHORT).show();
            return;
        }*/
        if (TextUtils.isEmpty(name)) {
            //password is empty
            Toast.makeText(getContext(), "enter password ", Toast.LENGTH_SHORT).show();
            return;
        }
        databaseUserRef.child(NewUserId).child("name").setValue(name);
       // databaseUserRef.child(NewUserId).child("email").setValue(email);
        databaseUserRef.child(NewUserId).child("clinic").setValue(clinic);
        databaseUserRef.child(NewUserId).child("latitude").setValue(latitude);
        databaseUserRef.child(NewUserId).child("longitude").setValue(longitude);
        databaseUserRef.child(NewUserId).child("address").setValue(address);

        databaseUserRef.child(NewUserId).child("number").setValue(number)


                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                     //   myDialogRating.dismiss();
                        Toast.makeText(getActivity(), "Profile Edited!", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //address= addres;
        //if validation is ok
        //show progressdialog




    }
    private AdapterView.OnItemClickListener mAutocompleteListenerView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //  hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                Log.d(TAG, "onResult : Places query did not complete " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place  = places.get(0);
            try {
                placedetails = new placedetails();
                LatLng ll = place.getLatLng();
                latitude = ll.latitude;
                longitude = ll.longitude;

                placedetails.setLatlng(place.getLatLng());
                addres = place.getLatLng().toString();
                Log.d(TAG , "on RESUKLT : PLACES DETAIKLS>" + place.getId());
                Log.d(TAG , "on RESUKLT : PLACES DETAIKLS>" + place.getLatLng());
            }
            catch (NullPointerException e ){
                Log.e(TAG, "onResult : NullPointerException"+ e.getMessage() );
            }
            places.release();


        }
    };

}