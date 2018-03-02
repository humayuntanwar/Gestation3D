package com.example.humayunt.templateui.Adapter;

/**
 * Created by HumayunT on 2/28/2018.
 */

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.example.humayunt.templateui.MainPanel.DoctorList;
import com.example.humayunt.templateui.MainPanel.Doctorlisttry;
import com.example.humayunt.templateui.R;

import java.util.List;

import static android.app.PendingIntent.getActivity;


public class DoctorListAdapter extends  RecyclerView.Adapter<DoctorListAdapter.MyHoder>  implements View.OnClickListener {

    List<DoctorDetail> list;
    Context context;
    private Activity activity;


    public DoctorListAdapter(List<DoctorDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doctorcardview, parent, false);
        MyHoder myHoder = new MyHoder(view);
        context = view.getContext();


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        final DoctorDetail mylist = list.get(position);
        holder.name.setText("Dr. " + mylist.getName());
        holder.email.setText("Email: " + mylist.getEmail());
        holder.clinic.setText("Hospital: " + mylist.getClinic());
         final String number = String.valueOf(mylist.getNumber());
        holder.calldoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CAll the Doctor
                Log.d("btn","inside");
              //  int number = (mylist.getNumber());
              //  String num = String.valueOf(number);

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                else {

                    Log.d("btn","inside1");
                    Toast.makeText(context,number,Toast.LENGTH_LONG).show();
                    String dial = "tel:" +number;
                    Intent callintent = new Intent(Intent.ACTION_CALL);
                    callintent.setData(Uri.parse(dial));
                    activity.startActivity(callintent);
                    //context.startActivity(callintent);
                    //context.getApplicationContext().startActivity(callintent);
                }
            }
        });

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
        TextView name,email,clinic;
        Button calldoctor;


        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.docname);
            email= (TextView) itemView.findViewById(R.id.docnumber);
            clinic = (TextView) itemView.findViewById(R.id.docclinic);
            calldoctor = (Button)itemView.findViewById(R.id.calldoctor);


        }

    }


}
