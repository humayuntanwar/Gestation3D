package com.example.humayunt.templateui.Signinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.humayunt.templateui.R;

public class SelectSignUp extends AppCompatActivity {
    Button SignUpDoc,SignUpPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sign_up);
        SignUpDoc = (Button)findViewById(R.id.sign_upDoctor);
        SignUpPatient = (Button)findViewById(R.id.sign_upPatient);
        SignUpDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectSignUp.this, SignUpDoctor.class));


            }
        });
        SignUpPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SelectSignUp.this, SignupActivity.class));

            }
        });
    }
}
