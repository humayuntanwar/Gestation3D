package com.example.humayunt.templateui;

/**
 * Created by HumayunT on 2/28/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.humayunt.templateui.DataModel.DoctorDetail;

import java.util.List;
public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.MyHoder>{

    List<DoctorDetail> list;
    Context context;

    public DoctorListAdapter(List<DoctorDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doctorcardview,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        DoctorDetail mylist = list.get(position);
        holder.name.setText(mylist.getName());
        holder.email.setText(mylist.getEmail());

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

    class MyHoder extends RecyclerView.ViewHolder{
        TextView name,email;


        public MyHoder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.docname);
            email= (TextView) itemView.findViewById(R.id.docnumber);


        }
    }


}
