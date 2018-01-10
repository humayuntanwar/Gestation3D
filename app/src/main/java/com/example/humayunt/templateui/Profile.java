package com.example.humayunt.templateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView useremail;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Gestation 3D");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
       if(firebaseAuth.getCurrentUser()==null) {
           finish();
           startActivity(new Intent(this, SigninActivity.class));

       }
        useremail = (TextView) findViewById(R.id.user_email);
        useremail.setText("Welcome" + user.getEmail());
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == logout){
            firebaseAuth.signOut();
            startActivity(new Intent(Profile.this, SigninActivity.class));
            finish();
            //finish();
            //startActivity(new Intent(this, SigninActivity.class));

        }
    }
}
