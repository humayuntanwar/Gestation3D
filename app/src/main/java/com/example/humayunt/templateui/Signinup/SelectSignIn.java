package com.example.humayunt.templateui.Signinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.humayunt.templateui.HelpGuide.Contactus;
import com.example.humayunt.templateui.HelpGuide.TermsCondition;
import com.example.humayunt.templateui.R;

public class SelectSignIn extends AppCompatActivity {
    Button SignInDoc,SignInPatient;
    TextView tv1,select_contact,select_terms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sign_in);
        SignInDoc = (Button)findViewById(R.id.sign_inDoctor);
        SignInPatient = (Button)findViewById(R.id.sign_inPatient);
        tv1 = (TextView) findViewById(R.id.goto_sign_up);
        select_contact = (TextView)findViewById(R.id.sign_contact);
        select_terms = (TextView)findViewById(R.id.sign_TermsConditions);
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
        select_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSignIn.this, TermsCondition.class));

            }
        });
        select_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSignIn.this, Contactus.class));

            }
        });
    }
}
