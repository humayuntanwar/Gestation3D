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
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.HelpGuide.Contactus;
import com.example.humayunt.templateui.HelpGuide.TermsCondition;
import com.example.humayunt.templateui.Signinup.SelectSignUp;
import com.example.humayunt.templateui.Signinup.forget_Password;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorSignIn extends AppCompatActivity implements View.OnClickListener {
    private Button doc_btnSignIn ,  button2;
    private EditText doc_sign_email;
    private  EditText doc_signpassword;
    private TextView doc_forget_pass,doc_contact,doc_terms, doc_btnSignUp;
    private ProgressDialog doc_progressdialog;
    private FirebaseAuth doc_firebaseauth;
    private  String docUserId;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_in);
        doc_firebaseauth = FirebaseAuth.getInstance();

        //maslaaa
       if(doc_firebaseauth.getCurrentUser()!= null){
           user = doc_firebaseauth.getCurrentUser();
           docUserId = user.getUid();
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("Doctor", docUserId.toString());
            startActivity(intent);
        }
        doc_sign_email = (EditText) findViewById(R.id.doc_Singin_email);
        doc_forget_pass = (TextView) findViewById(R.id.doc_forget_pass);
        doc_contact = (TextView) findViewById(R.id.doc_contact);
        doc_terms = (TextView) findViewById(R.id.doc_TermsConditions);
        doc_signpassword = (EditText)findViewById(R.id.doc_Singin_password);
        doc_btnSignIn = (Button) findViewById(R.id.doc_sign_in);
        doc_btnSignUp = (TextView) findViewById(R.id.doc_sign_up);
        //button2 = (Button) findViewById(R.id.button2);
        doc_btnSignIn.getBackground().setAlpha(200);
//        doc_btnSignUp.getBackground().setAlpha(200);

        doc_progressdialog = new ProgressDialog(this);
        doc_btnSignIn.setOnClickListener(this);
        doc_btnSignUp.setOnClickListener(this);
        doc_forget_pass.setOnClickListener(this);
        doc_contact.setOnClickListener(this);
        doc_terms.setOnClickListener(this);
       // button2.setOnClickListener(this);
        doc_btnSignIn.getBackground().setAlpha(160);
       // doc_btnSignUp.getBackground().setAlpha(160);
    }

    private void DoctorLogin(){
        String Email = doc_sign_email.getText().toString().trim();
        String Password = doc_signpassword.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            //email is empty
            Toast.makeText(this, "enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            //password is empty
            Toast.makeText(this, "enter password ", Toast.LENGTH_SHORT).show();
            return;
        }

        doc_progressdialog.setMessage("Signing you in...");
        doc_progressdialog.show();
        doc_firebaseauth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        doc_progressdialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activity
                            //maslaaa
                            user = doc_firebaseauth.getCurrentUser();
                            docUserId = user.getUid();
                            Intent intentt = new Intent(getApplicationContext(), UserProfile.class);
                            intentt.putExtra("Doctor",docUserId.toString() );
                            startActivity(intentt);

                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if (v == doc_btnSignIn){
            DoctorLogin();

        }
        if(v == doc_btnSignUp){
            finish();
            startActivity(new Intent( this, SelectSignUp.class));
        }
        if(v == doc_forget_pass){
            finish();
            startActivity(new Intent( this, forget_Password.class));
        }
       /* if(v == button2){
            finish();
            startActivity(new Intent( this, UserProfile.class));
        }*/
        if(v == doc_contact){

            startActivity(new Intent( this, Contactus.class));
        }
        if(v== doc_terms){

            startActivity(new Intent( this, TermsCondition.class));
        }


    }
    }

