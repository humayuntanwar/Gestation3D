package com.example.humayunt.templateui;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.DataModel.UserDetail;
import com.example.humayunt.templateui.HelpGuide.About;
import com.example.humayunt.templateui.HelpGuide.Faq;
import com.example.humayunt.templateui.HelpGuide.Features;
import com.example.humayunt.templateui.HelpGuide.WatchVideo;
import com.example.humayunt.templateui.LocateHospital.hospital_MapsActivity;
import com.example.humayunt.templateui.MainPanel.DoctorList;
import com.example.humayunt.templateui.MainPanel.LocateDoctorMap;
import com.example.humayunt.templateui.MainPanel.diets;
import com.example.humayunt.templateui.MainPanel.excercises;
import com.example.humayunt.templateui.MainPanel.guidetry;
import com.example.humayunt.templateui.SidePanel.QuizActivity;
import com.example.humayunt.templateui.SidePanel.change_password;
import com.example.humayunt.templateui.SidePanel.edit_profile;
import com.example.humayunt.templateui.Signinup.SelectSignIn;
import com.example.humayunt.templateui.Signinup.SigninActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;import android.app.Dialog;


public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    public TextView Useremail, Username;

    private FirebaseDatabase firebaseDatabase;
     private String UserId;
    private DatabaseReference databaseRef;
    private String TAG;
    private CardView guidecard, modelcard,vrviewcard,arguidecard,dietcard,excercisecard,locatehospitalcard,doctorcard;
    Dialog myDialog;
    String Doctor, Patient, passDocEdit, passPatientEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         Doctor = getIntent().getStringExtra("Doctor");
        Patient = getIntent().getStringExtra("Patient");
//     Log.i("patient",Patient);
        //maslaaa
        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();
      //  Log.i("doccc" , UserId);
//        Log.i("Pat", Patient);
        Log.i("Patt" , UserId);
        doctorcard= (CardView) findViewById(R.id.ViewDoctors);
        locatehospitalcard = (CardView) findViewById(R.id.locatehospital);


        //maslaaa
    try{
        if( Doctor != null || Doctor.equals(UserId)){
        //  Log.i("doctor",Doctor);
            passDocEdit = Doctor;
            doctorcard.setVisibility(View.INVISIBLE);
            locatehospitalcard.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        databaseRef = firebaseDatabase.getReference("doctor");
        }
    }
        catch (NullPointerException e ){
            e.printStackTrace();

            try {
                if (Patient != null || Patient.equals(UserId))
                    passPatientEdit = Patient;
                    databaseRef = firebaseDatabase.getReference("users");
            }
            catch (NullPointerException f ){
                f.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        myDialog = new Dialog(this);


        //Log.d(TAG,UserId );

        if(firebaseAuth.getCurrentUser()==null) {
            finish();
            startActivity(new Intent(this, SelectSignIn.class));

        }
       //  databaseUserRef = firebaseDatabase.getReference("user");
        //mNavigationView=(NavigationView)findViewById(R.id.nav_view);
        guidecard = (CardView) findViewById(R.id.dguide);
        modelcard = (CardView) findViewById(R.id.dmodels);
     //   vrviewcard = (CardView) findViewById(R.id.vrview);
        arguidecard =(CardView) findViewById(R.id.arguide);
        dietcard = (CardView) findViewById(R.id.diets);
        excercisecard = (CardView) findViewById(R.id.excercise);


        guidecard.setOnClickListener(this);
        modelcard.setOnClickListener(this);
        //  vrviewcard.setOnClickListener(this);
         arguidecard.setOnClickListener(this);
         dietcard.setOnClickListener(this);
         excercisecard.setOnClickListener(this);
         locatehospitalcard.setOnClickListener(this);
        doctorcard.setOnClickListener(this);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        //mNavigationView.setNavigationItemSelectedListener(this);
        //View myview=mNavigationView.getHeaderView(0);
         Useremail = ((TextView) header.findViewById(R.id.textView_email));
         Username = ((TextView) header.findViewById(R.id.User_name));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //changed to add drawer from set drawer
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

              //  Toast.makeText(getApplicationContext(), "called...",Toast.LENGTH_LONG).show();

                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void ShowPopup() {
        TextView txtclose ;
        Button about,faq,features,watchvideo;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        faq = (Button)myDialog.findViewById(R.id.faq);
        features = (Button)myDialog.findViewById(R.id.features);
        watchvideo = (Button)myDialog.findViewById(R.id.watchVideo);
        txtclose.setText("X");

        about = (Button) myDialog.findViewById(R.id.About);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,About.class));


            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,Faq.class));


            }
        });
        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Features.class));


            }
        });
        watchvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,WatchVideo.class));


            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    private void showData(DataSnapshot dataSnapshot) {

        Toast.makeText(getApplicationContext(), "Method ccalled..", Toast.LENGTH_LONG).show();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Log.d("user ids..",ds.getKey());
            try {
                if (ds.getKey().equals("111"+Doctor)) {

                    Log.d("user data..", ds.getValue().toString());
                    Log.d(TAG, ds.toString());

                    DoctorDetail doctorDetail = ds.getValue(DoctorDetail.class);

                    //display all information
                    Toast.makeText(this, doctorDetail.getName().toString(), Toast.LENGTH_LONG).show();
                    Log.d(TAG, "ShowData :email " + doctorDetail.getEmail());
                    Log.d(TAG, "ShowData :name " + doctorDetail.getName());
                    Useremail.setText(doctorDetail.getEmail().toString());
                    Username.setText(doctorDetail.getName().toString());


                }

            }
            catch (NullPointerException g){
                g.printStackTrace();

            }
            try {


                if(ds.getKey().equals(Patient)) {
                    Log.i("user", ds.toString());
                    // Toast.makeText(this, User.getName().toString(), Toast.LENGTH_LONG).show();

                    UserDetail UserDetail = ds.getValue(UserDetail.class);
                    Toast.makeText(this, UserDetail.getName().toString(), Toast.LENGTH_LONG).show();
                    Log.i(TAG, "ShowData :email " + UserDetail.getEmail());
                    Log.i(TAG, "ShowData :name " + UserDetail.getName());
                    Useremail.setText(UserDetail.getEmail());
                    Username.setText(UserDetail.getName());
                }

            }
            catch (NullPointerException h){
                h.printStackTrace();
            }


        }

    }


    public void onClick(View view) {

            //finish();
            //startActivity(new Intent(this, SigninActivity.class));
        if(view == guidecard){
           // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, guidetry.class));
        }
        if(view == modelcard){
            // firebaseAuth.signOut();
          //startActivity(new Intent(UserProfile.this, UnityPlayerActivity.class));
            startActivity(new Intent(UserProfile.this,LocateDoctorMap.class));


        }
        if(view == arguidecard){
            // firebaseAuth.signOut();
           // startActivity(new Intent(UserProfile.this, Features.class));
            startActivity(new Intent(UserProfile.this, DoctorList.class));




        }
        if(view == dietcard){
            // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, diets.class));


        }
        if(view == excercisecard){
            // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, excercises.class));


        }
        if(view == locatehospitalcard){
            // firebaseAuth.signOut();

            startActivity(new Intent(UserProfile.this, hospital_MapsActivity.class));


        }
        if(view == doctorcard){
            startActivity(new Intent(UserProfile.this, DoctorList.class));




        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, SelectSignIn.class));
            finish();

           // return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();

       /* if (id == R.id.nav_camera) {


            Toast.makeText(getApplicationContext(),"inside",Toast.LENGTH_LONG).show();

            // Handle the camera action


            manager.beginTransaction().add(R.id.main_replace,new edit_profile()).addToBackStack("frag").commit();
          //  startActivity(new Intent(UserProfile.this, SigninActivity.class));
        }*/
        if (id == R.id.nav_changePassword) {
           // FragmentManager manager = getSupportFragmentManager();
            Log.i("tag2","inside");
            manager.beginTransaction().add(R.id.main_replace,new change_password()).addToBackStack("frag").commit();

        }
        else if (id == R.id.nav_edit_proflie) {
            try {
                if (passPatientEdit != null);
                Bundle bundle = new Bundle();
                bundle.putString("passPatientEdit",passPatientEdit);
                edit_profile fragInfo = new edit_profile();
                fragInfo.setArguments(bundle);
                manager.beginTransaction().replace(R.id.main_replace,fragInfo).addToBackStack("frag").commit();

            }
                catch (NullPointerException l) {
             l.printStackTrace();
            }
            try {
                if(passDocEdit != null){
                    Bundle bundle = new Bundle();
                    bundle.putString("passDocEdit",passDocEdit);
                    edit_profile fragInfo = new edit_profile();
                    fragInfo.setArguments(bundle);
                    manager.beginTransaction().replace(R.id.main_replace,fragInfo).addToBackStack("frag").commit();


                }

            }
            catch (NullPointerException m){
                m.printStackTrace();
            }

        }
        else if (id == R.id.TakeQuiz) {
           manager.beginTransaction().replace(R.id.main_replace,new QuizActivity()).addToBackStack("frag").commit();


        } else if (id == R.id.setting) {
            ShowPopup();

        }

        else if (id == R.id.nav_signout){
            firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, SigninActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
