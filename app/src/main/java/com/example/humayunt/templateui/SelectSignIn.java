package com.example.humayunt.templateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.humayunt.templateui.Signinup.SelectSignUp;
import com.example.humayunt.templateui.Signinup.SignUpDoctor;
import com.example.humayunt.templateui.Signinup.SigninActivity;
import com.example.humayunt.templateui.Signinup.SignupActivity;

public class SelectSignIn extends AppCompatActivity {
    Button SignInDoc,SignInPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sign_in);
        SignInDoc = (Button)findViewById(R.id.sign_inDoctor);
        SignInPatient = (Button)findViewById(R.id.sign_inPatient);
        SignInDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSignIn.this, DoctorSignIn.class));


            }
        });
        SignInPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SelectSignIn.this, SigninActivity.class));

            }
        });
    }
}
