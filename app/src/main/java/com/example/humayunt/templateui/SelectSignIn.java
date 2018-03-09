package com.example.humayunt.templateui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.humayunt.templateui.Signinup.SelectSignUp;
import com.example.humayunt.templateui.Signinup.SignUpDoctor;
import com.example.humayunt.templateui.Signinup.SigninActivity;
import com.example.humayunt.templateui.Signinup.SignupActivity;

public class SelectSignIn extends AppCompatActivity {
    Button SignInDoc,SignInPatient;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sign_in);
        SignInDoc = (Button)findViewById(R.id.sign_inDoctor);
        SignInPatient = (Button)findViewById(R.id.sign_inPatient);
        tv1 = (TextView) findViewById(R.id.goto_sign_up);
        SignInDoc.getBackground().setAlpha(160);
        SignInPatient.getBackground().setAlpha(160);
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
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSignIn.this, SelectSignUp.class));

            }
        });
    }
}
