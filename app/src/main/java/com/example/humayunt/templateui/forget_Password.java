package com.example.humayunt.templateui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_Password extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseauth;
    private EditText forget_email;
    private Button for_email , cancel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        firebaseauth = FirebaseAuth.getInstance();
        if(firebaseauth.getCurrentUser()!= null){
            startActivity(new Intent( getApplicationContext(),UserProfile.class));

        }
        forget_email = (EditText)findViewById(R.id.change_email);
        for_email = (Button) findViewById(R.id.forget_password);
        cancel = (Button) findViewById(R.id.cancel);
       //progressdialog = new ProgressDialog(this);
        for_email.setOnClickListener(this);
        cancel.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }


    @Override
    public void onClick(View v) {
        if(v == for_email){
            String email = forget_email.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);

            firebaseauth.sendPasswordResetEmail(email)

                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(forget_Password.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(forget_Password.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }

                            progressBar.setVisibility(View.GONE);
                        }
                    });
        }
        if( v == cancel){
            finish();
            startActivity(new Intent( getApplicationContext(),SigninActivity.class));

        }

    }
}

