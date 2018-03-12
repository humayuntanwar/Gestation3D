package com.example.humayunt.templateui.Signinup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.humayunt.templateui.AutoCompletePlace.PlaceAutocompleteAdapter;
import com.example.humayunt.templateui.AutoCompletePlace.placedetails;
import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;


import com.google.android.gms.location.places.Place;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import static com.example.humayunt.templateui.R.id.yes_radio;
import static com.example.humayunt.templateui.LocateHospital.hospital_MapsActivity.REQUEST_LOCATION_CODE;



public class SignUpDoctor extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "RegisterActivity";
    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputNumber,clinic;
    private AutoCompleteTextView signupInputAddress;
    private Button btnSignUp, btnSign;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    DatabaseReference databaseUser, databaseDoctor;
    private String UserId;
    private String addres, buttonAddress;
    private Geocoder geocoder;
    private PlaceAutocompleteAdapter mPlaceAutocompleAdapter;
    private GoogleApiClient mGoogleApiClient;
    protected GeoDataClient mGeoDataClient;
    private com.example.humayunt.templateui.AutoCompletePlace.placedetails placedetails;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    List<android.location.Address> addresses;
    private Double longitude,latitude;
    private RadioGroup radioGroup;
    private RadioButton yesDoc, noDoc;
    private  View number;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -16), new LatLng(71, 136));


    PlaceAutocompleteFragment autocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);
        mGeoDataClient = Places.getGeoDataClient(this, null);
        geocoder = new Geocoder(this, Locale.getDefault());

        Toast.makeText(this,"dfbdf",Toast.LENGTH_LONG).show();

        setTitle("Gestation 3D");
        firebaseauth = FirebaseAuth.getInstance();
        // databaseUser = FirebaseDatabase.getInstance().getReference("users");
       /* if (firebaseauth.getCurrentUser() != null) {
            Intent intent = new Intent(this.getApplicationContext(), UserProfile.class);
            startActivity(intent);
            finish();
        }*/
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        progressdialog = new ProgressDialog(this);

        signupInputName = (EditText) findViewById(R.id.signupdoc_input_name);
        clinic = (EditText) findViewById(R.id.signupdoc_hospital);
        signupInputEmail = (EditText) findViewById(R.id.signupdoc_email);
        signupInputPassword = (EditText) findViewById(R.id.signupdoc_password);
        signupInputAddress = (AutoCompleteTextView) findViewById(R.id.signupdoc_address);
        signupInputNumber = (EditText) findViewById(R.id.signupdoc_number);
        btnSignUp = (Button) findViewById(R.id.btndoc_signup);
        btnSign = (Button) findViewById(R.id.btndoc_signin);
        //number =  findViewById(R.id.number);


       btnSign.getBackground().setAlpha(160);
        btnSignUp.getBackground().setAlpha(160);

        btnSignUp.setOnClickListener(this);
        btnSign.setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();


        signupInputAddress.setOnItemClickListener(mAutocompleteListenerView);
        mPlaceAutocompleAdapter = new PlaceAutocompleteAdapter(this, mGeoDataClient, LAT_LNG_BOUNDS, null);
        // mGeoDataClient.getPlaceById(mPlaceAutocompleAdapter);
        signupInputAddress.setAdapter(mPlaceAutocompleAdapter);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(signupInputName
                .getWindowToken(), 0);
        signupInputAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (signupInputAddress.getRight() - signupInputAddress.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here

                        getDeviceLocation();


                        return true;
                    }
                }
                return false;
            }
        });
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
            }else {
                Toast.makeText(this, "Please Connect to Internet ", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_LOCATION_CODE);
            }
        } else {
            //No internet
            Toast.makeText(this, "Please Connect to Internet ", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_LOCATION_CODE);

        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;

        } else
            return false;
    }

    private void getDeviceLocation() {





        try{
            if(checkLocationPermission()==true){
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "Found Location");
                            Location currentLocation = (Location) task.getResult();
                            try {
                                addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                                String city = addresses.get(0).getLocality();
                                signupInputAddress.setText(city);

                                String state = addresses.get(0).getAdminArea();
                                Toast.makeText(SignUpDoctor.this, city, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            buttonAddress = currentLocation.getProvider();

                            // String butAdd = currentLocation
                            // buttonAddress = new LatLng(currentLocation.getLatitude(),
                            // currentLocation.getLongitude()).toString();


                        }
                        else {
                            Log.d(TAG, "Found not  Location");
                        }
                    }
                });
            }


        }catch (SecurityException e ){
            Log.e(TAG,"Security exception"+ e.getMessage());
        }
    }

    private void registerDoctor() {

        final String name = signupInputName.getText().toString().trim();

        final String clinicc = clinic.getText().toString().trim();
        final String email = signupInputEmail.getText().toString().trim();
        final String password = signupInputPassword.getText().toString().trim();
        final String number = signupInputNumber.getText().toString() ;
        final String address = signupInputAddress.getText().toString().trim();

        System.out.println(email + password);

        //mPlaceAutocompleAdapter = new PlaceAutocompleteAdapter(this,mGeoDataClient, LAT_LNG_BOUNDS, null);
        // signupInputAddress.setAdapter(mPlaceAutocompleAdapter);

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "enter password ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() <8){
            Toast.makeText(this, "password should be 8 characters ", Toast.LENGTH_SHORT).show();
            return;

        } if (TextUtils.isEmpty(signupInputNumber.getText().toString())) {
            //email is empty
            Toast.makeText(this, "enter number  ", Toast.LENGTH_SHORT).show();
            return;
        }

        //address= addres;
        //if validation is ok
        //show progressdialog
        progressdialog.setMessage("Registering doctor...");
        progressdialog.show();



        firebaseauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is registered successfully
                            //Profile activity here
                            firebaseauth.getCurrentUser();

                            //String tempEmail = email.replaceAll("\\.","*");
                            // String tempEmail =databaseUser.getKey();
                            FirebaseUser user = firebaseauth.getCurrentUser();
                            UserId = "111" + user.getUid();
                             final String uid = UserId.toString();

                            databaseDoctor = FirebaseDatabase.getInstance().getReference("doctor");
                            DoctorDetail doctor = new DoctorDetail(name, email, password,address, clinicc,UserId, latitude,longitude , number);
                            databaseDoctor.child(UserId).setValue(doctor).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    progressdialog.hide();
                                    Toast.makeText(SignUpDoctor.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), DoctorSignIn.class);
                                    intent.putExtra("UserID", uid);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                                }
                            });


                            // finish();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            progressdialog.hide();
                            Toast.makeText(SignUpDoctor.this,task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                            //Toast.makeText(SignupActivity.this, "could not register! try again!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (btnSignUp.isPressed()) {
            registerDoctor();
         //   Toast.makeText(this,signupInputPassword.getText(),Toast.LENGTH_LONG).show();
        }
        if (btnSign.isPressed()) {
           // Toast.makeText(SignUpDoctor.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
            //getDeviceLocation();
            Intent intent = new Intent(this.getApplicationContext(), SigninActivity.class);
            startActivity(intent);
            finish();


        }


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /*++++++++++++++++++++GOOGLE PLACE AUTOCOMPLETE SUGGESTION++++++++*/
    private AdapterView.OnItemClickListener mAutocompleteListenerView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // hideSoftKeyboard();

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
