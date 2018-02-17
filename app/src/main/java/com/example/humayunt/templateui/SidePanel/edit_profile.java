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
    private EditText signupInputName, signupInputEmail, signupInputPassword;
    private AutoCompleteTextView signupInputAddress;
    private Button btnSignUp, btnSign;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    DatabaseReference databaseUser;
    // private String UserId;
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
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    List<android.location.Address> addresses;


    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -16), new LatLng(71, 136));


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

        signupInputName = (EditText) v.findViewById(R.id.signup_input_name);
        signupInputEmail = (EditText) v.findViewById(R.id.signup_email);
        signupInputPassword = (EditText) v.findViewById(R.id.signup_password);
        signupInputAddress = (AutoCompleteTextView) v.findViewById(R.id.signup_address);
        btnSignUp = (Button) v.findViewById(R.id.btn_signup);
        firebaseDatabase = FirebaseDatabase.getInstance();



       firebaseauth = FirebaseAuth.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid().toString();
        databaseUserRef = firebaseDatabase.getReference();

        Log.d(TAG,UserId );
       // Toast.makeText(getContext(), UserId, Toast.LENGTH_LONG).show();
        try{
        databaseUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)

            {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); }
        catch (Exception e ){
           Toast.makeText(getContext(),e.toString(), Toast.LENGTH_LONG ).show();
           // Toast.makeText(getContext(), UserId, Toast.LENGTH_SHORT).show();
        }

        btnSignUp.setOnClickListener(this);
        return v;


    }


    @Override
    public void onClick(View v) {
        if (btnSignUp.isPressed()) {
           registerUser();
        }

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Log.d(TAG, ds.toString());
            UserDetail UserDetail = new UserDetail();
         //  UserDetail.setEmail(ds.child(UserId).getValue(UserDetail.class).getEmail());
            UserDetail.setEmail(ds.child(UserId).getValue(UserDetail.class).getEmail());
           UserDetail.setName(ds.child(UserId).getValue(UserDetail.class).getName());
            UserDetail.setPassword(ds.child(UserId).getValue(UserDetail.class).getPassword());
            UserDetail.setDoctor(ds.child(UserId).getValue(UserDetail.class).getDoctor());
            UserDetail.setAddress(ds.child(UserId).getValue(UserDetail.class).getAddress());

            //display all information

          //  Log.d(TAG, "ShowData :name " + UserDetail.getName());
            signupInputEmail.setText(UserDetail.getEmail());
            signupInputName.setText(UserDetail.getName());
            signupInputPassword.setText((UserDetail.getPassword()));
            signupInputAddress.setText(UserDetail.getAddress());


        }
    }

    private void registerUser() {

        final String name = signupInputName.getText().toString().trim();
        final String email = signupInputEmail.getText().toString().trim();
        final String password = signupInputPassword.getText().toString().trim();
        final String address ;
        System.out.println(email + password);

        //mPlaceAutocompleAdapter = new PlaceAutocompleteAdapter(this,mGeoDataClient, LAT_LNG_BOUNDS, null);
        // signupInputAddress.setAdapter(mPlaceAutocompleAdapter);

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(getContext(), "enter email ", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "enter m",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(getContext(), "enter password ", Toast.LENGTH_SHORT).show();
            return;
        }

        //address= addres;
        //if validation is ok
        //show progressdialog
        progressdialog.setMessage("Registering user...");
        progressdialog.show();

        firebaseauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is registered successfully
                            //Profile activity here
                            firebaseauth.getCurrentUser();

                            //String tempEmail = email.replaceAll("\\.","*");
                            // String tempEmail =databaseUser.getKey();
                            FirebaseUser user = firebaseauth.getCurrentUser();
                            UserId = user.getUid();
                            databaseUser = FirebaseDatabase.getInstance().getReference("users");

                            UserDetail User = new UserDetail(name, email, password, addres);
                            databaseUser.child(UserId).setValue(User).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                                }
                            });
                            progressdialog.hide();
                            Toast.makeText(getContext(), "Changed successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(),UserProfile.class);
                            startActivity(intent);
                            // finish();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            progressdialog.hide();
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                            //Toast.makeText(SignupActivity.this, "could not register! try again!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
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