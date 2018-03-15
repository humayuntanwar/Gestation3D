package com.example.humayunt.templateui.Adapter;

/**
 * Created by HumayunT on 2/28/2018.
 */

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.MainPanel.DoctorList;
import com.example.humayunt.templateui.MainPanel.Doctorlisttry;
import com.example.humayunt.templateui.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class DoctorListAdapter extends  RecyclerView.Adapter<DoctorListAdapter.MyHoder>  implements View.OnClickListener {

    List<DoctorDetail> list;
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    Context context;
    private Activity activity;
    Dialog myDialogRating;
    RatingBar ratingBarSubmitt;
    DoctorDetail mylist;
    FirebaseDatabase database;
    DatabaseReference myRef ;


    float rating;
    int noofrating = 0;




    public DoctorListAdapter(List<DoctorDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doctorcardview, parent, false);
        MyHoder myHoder = new MyHoder(view);
        context = view.getContext();
        myDialogRating = new Dialog(context);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("doctor");
        return myHoder;
    }

    @Override
    public void onBindViewHolder(final MyHoder holder, int position) {
          mylist = list.get(position);

        holder.name.setText("Dr. " + mylist.getName());
        holder.email.setText("Email: " + mylist.getEmail());
        holder.clinic.setText(""+ mylist.getClinic());
        final float previousrating = mylist.getRating();
        final int previoustotal = mylist.getNumberOfRating();
        float totalrating = previousrating / previoustotal;
        holder.showrating.setRating(totalrating);
        holder.docrates.setText("("+ mylist.getNumberOfRating() +")");
        final String  userId = String.valueOf(mylist.getUserId());
        final String number = String.valueOf(mylist.getNumber());


        holder.calldoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CAll the Doctor
                Log.d("btn","inside");
              //  int number = (mylist.getNumber());
              //  String num = String.valueOf(number);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                else {

                    Log.d("btn","inside1");
                    Toast.makeText(context,number,Toast.LENGTH_LONG).show();
                    String dial = "tel:" +number;
                    Intent callintent = new Intent(Intent.ACTION_CALL);
                    callintent.setData(Uri.parse(dial));
                    context.startActivity(callintent);
                }
            }
        });

        holder.showratingpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ////    Toast.makeText(context,userId,Toast.LENGTH_LONG).show();
              //  Log.d("hello",holder.clinic.getText().toString());
                //Toast.makeText(context, userId, Toast.LENGTH_LONG).show();
                TextView txtclose ;
                Button submitRating;
                myDialogRating.setContentView(R.layout.ratedoctorlayout);

                ratingBarSubmitt = (RatingBar) myDialogRating.findViewById(R.id.ratingBarsubmit);

                txtclose =(TextView) myDialogRating.findViewById(R.id.txtclose);
                txtclose.setText("X");
                submitRating= (Button) myDialogRating.findViewById(R.id.submitRating);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialogRating.dismiss();
                    }
                });

                submitRating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //startActivity(new Intent(getActivity(),About.class));
                        rating = ratingBarSubmitt.getRating();
                        final float newRating = previousrating + rating;
                        final  int newNumberRating = previoustotal + 1 ;
                        myRef.child(userId).child("rating").setValue(newRating);
                        myRef.child(userId).child("numberOfRating").setValue(newNumberRating)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                myDialogRating.dismiss();
                                Toast.makeText(context, "rating Submitted!", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }
                });
                myDialogRating.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                myDialogRating.show();

            }
        });


    }
    public void ShowPopup() {


    }


    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    @Override
    public void onClick(View v) {

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,email,clinic, docrates;
        Button calldoctor, showratingpopup;
        RatingBar showrating;


        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.docname);
            docrates = (TextView)itemView.findViewById(R.id.docrates);
            email= (TextView) itemView.findViewById(R.id.docnumber);
            clinic = (TextView) itemView.findViewById(R.id.docclinic);
            calldoctor = (Button)itemView.findViewById(R.id.calldoctor);
            showratingpopup = (Button)itemView.findViewById(R.id.showratingpopup);
            showrating = (RatingBar)itemView.findViewById(R.id.ratingBar);


        }

    }


}
