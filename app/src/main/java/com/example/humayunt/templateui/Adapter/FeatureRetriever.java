package com.example.humayunt.templateui.Adapter;

/**
 * Created by HumayunT on 2/19/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.humayunt.templateui.DataModel.featureDataModel;
;
import com.example.humayunt.templateui.R;
import com.example.humayunt.templateui.SingleItem.singleItemFeature;

import java.util.ArrayList;
public class FeatureRetriever extends RecyclerView.Adapter<FeatureRetriever.DataObjectHolder> implements OnClickListener{
    ArrayList<featureDataModel> Show;
    private Activity activity;
    private String cat_id;
    private DataObjectHolder holder;
    int i = 0;
    private LayoutInflater mLayoutInflater = null;
    private String name;
    private String str_flag = "";

    public static class DataObjectHolder extends ViewHolder {
        public ImageView img;
        public RelativeLayout layout;
        public TextView tvCity;
        public TextView tvName;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.layout = (RelativeLayout) itemView.findViewById(R.id.dmodel);
            this.tvName = (TextView) itemView.findViewById(R.id.tv1);
            //  this.tvCity = (TextView) itemView.findViewById(R.id.txt_city_griditemlayout);
            this.img = (ImageView) itemView.findViewById(R.id.im1);
        }
    }

    public void onClick(View v) {
    }

    public FeatureRetriever(Activity activity2, ArrayList<featureDataModel> contacts) {
        this.activity = activity2;
        this.Show = contacts;
        this.mLayoutInflater = (LayoutInflater) this.activity.getSystemService("layout_inflater");
    }

    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.holder = new DataObjectHolder(this.mLayoutInflater.inflate(R.layout.featurelist, null));
        return this.holder;
    }

    public void onBindViewHolder(DataObjectHolder holder, final int position) {
        holder.layout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),singleItemFeature.class);
                intent.putExtra("no", ( FeatureRetriever.this.Show.get(position)).getNo());
                v.getContext().startActivity(intent);
            }
        });
        holder.tvName.setText(" " + ( this.Show.get(position)).getName());
        // holder.tvCity.setText("Feature : " + ( this.Show.get(position)).getHowuse());
        //holder.img.setImageBitmap(( this.Show.get(position)).getImg());
    }

    public int getItemCount() {
        return this.Show.size();
    }
}


