package com.example.humayunt.templateui.SingleItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.DataModel.featureDataModel;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;

import java.io.IOException;

public class singleItemFeature extends AppCompatActivity {
    private SQLiteDatabase db;
    DatabaseHandler dh = new DatabaseHandler(this);
    public TextView avoidfood;
    public ImageView img;
    public TextView foodtype;
    boolean isImageFitToScreen;
    public TextView month;

    public singleItemFeature() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_feature);
        this.db = this.dh.getReadableDatabase();
        this.month = (TextView) findViewById(R.id.month);

        String id = getIntent().getExtras().getString("no");
        int mon = Integer.parseInt(id);
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        String selectQuery = "SELECT  * FROM features where no =" + mon;
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
          //  featureDataModel Dietsdata = new featureDataModel();
           this.month.setText("Feature Details : " + cursor.getString(2));

        }
    }
}
