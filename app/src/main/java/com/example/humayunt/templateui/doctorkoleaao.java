package com.example.humayunt.templateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.humayunt.templateui.DataModel.DoctorDetail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctorkoleaao extends AppCompatActivity {
    private RecyclerView mBlog;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorkoleaao);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("doctor");
        mDatabase.keepSynced(true);
        mBlog = (RecyclerView)findViewById(R.id.recycledockeliey);
        mBlog.setHasFixedSize(true);
        mBlog.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<DoctorDetail,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DoctorDetail, BlogViewHolder>
                (DoctorDetail.class,R.layout.doctorcardview,BlogViewHolder.class,mDatabase) {

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, DoctorDetail model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setNumber(model.getNumber());

            }
        };
        mBlog.setAdapter(firebaseRecyclerAdapter);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
        View mview;
        public BlogViewHolder(View itemView){
            super(itemView);
            mview=itemView;

        }
        public void setName(String title){
            TextView ttx1 = (TextView)mview.findViewById(R.id.docname);
            ttx1.setText(title);


        }
        public void setNumber(int number){
            TextView ttx2 = (TextView)mview.findViewById(R.id.docnumber);
            ttx2.setText(number);


        }

    }
}
