package com.example.humayunt.templateui.Signinup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.humayunt.templateui.HelpGuide.Contactus;
import com.example.humayunt.templateui.R;
import com.example.humayunt.templateui.HelpGuide.TermsCondition;
import com.example.humayunt.templateui.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by HumayunT on 10/19/2017.
 */

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnSignIn , button2;
    private EditText email;
    private  EditText password;
    private  TextView forget_pass,contact,terms,btnSignUp;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    private  String UserId;
    FirebaseUser user;
    private RadioButton docradio,patradio;
    private RadioGroup optionSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gestation 3D");
        setContentView(R.layout.signin);
        firebaseauth= FirebaseAuth.getInstance();



        if(firebaseauth.getCurrentUser()!= null){
            user = firebaseauth.getCurrentUser();
            UserId = user.getUid();
            Intent intent = new Intent(getBaseContext(), UserProfile.class);
            intent.putExtra("Patient", UserId.toString());
            startActivity(intent);
        }

        email = (EditText) findViewById(R.id.email);
       // docradio = (RadioButton)findViewById(R.id.doctorradio);
       // patradio = (RadioButton)findViewById(R.id.patientradio);
       // optionSelect = (RadioGroup)findViewById(R.id.selectsign);
        forget_pass = (TextView) findViewById(R.id.forget_pass);
        contact = (TextView) findViewById(R.id.contact);
        terms = (TextView) findViewById(R.id.TermsConditions);
        password = (EditText)findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.sign_in);
        btnSignUp = (TextView) findViewById(R.id.sign_up);
       // button2 = (Button) findViewById(R.id.button2);
        btnSignIn.getBackground().setAlpha(200);
//        btnSignUp.getBackground().setAlpha(200);

        progressdialog = new ProgressDialog(this);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        forget_pass.setOnClickListener(this);
        contact.setOnClickListener(this);
        terms.setOnClickListener(this);
//        button2.setOnClickListener(this);
        btnSignIn.getBackground().setAlpha(160);
      //  btnSignUp.getBackground().setAlpha(160);

    }
    private void userLogin(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

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

        progressdialog.setMessage("Signing you in...");
        progressdialog.show();
        firebaseauth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activity
                          //  String user = user.
                            user = firebaseauth.getCurrentUser();
                            UserId = user.getUid();
                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            intent.putExtra("Patient",UserId.toString() );
                            startActivity(intent);
                        }
                    }
                });
    }
    public void onClick(View view) {
        if (view == btnSignIn){
            /*optionSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    View radioButton = optionSelect.findViewById(checkedId);
                    int index = optionSelect.indexOfChild(radioButton);

                    // Add logic here

                    switch (index) {
                        case 0: // first button

                           Toast.makeText(getApplicationContext(), "Selected button number ", Toast.LENGTH_LONG).show();
                            break;
                        case 1: // secondbutton

                           Toast.makeText(getApplicationContext(), "Selected button number " , Toast.LENGTH_LONG).show();
                            userLogin();
                            break;
                    }
                }
            });*/
            userLogin();
          //

        }
        if(view == btnSignUp){
            finish();
            startActivity(new Intent( this, SelectSignUp.class));
        }
        if(view == forget_pass){
            finish();
            startActivity(new Intent( this, forget_Password.class));
        }
        /*if(view == button2){
            finish();
            startActivity(new Intent( this, UserProfile.class));
        }*/
        if(view == contact){

            startActivity(new Intent( this, Contactus.class));
        }
        if(view == terms){

            startActivity(new Intent( this, TermsCondition.class));
        }


    }
}