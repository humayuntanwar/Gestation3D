package com.example.humayunt.templateui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class singleItemTwoDGuide extends AppCompatActivity {
    private SQLiteDatabase db;
    DatabaseHandler dh = new DatabaseHandler(this);
    public ImageView diagram;
    public TextView fact;
    public ImageView img;
    public TextView info;
    boolean isImageFitToScreen;
    public TextView month;

    public singleItemTwoDGuide() throws IOException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_two_dguide);
        this.db = this.dh.getReadableDatabase();
        this.month = (TextView) findViewById(R.id.textView);
        this.info = (TextView) findViewById(R.id.textView2);
        this.fact = (TextView) findViewById(R.id.textView3);
        this.img = (ImageView) findViewById(R.id.imageView2);
        this.diagram = (ImageView) findViewById(R.id.imageView3);
        String id = getIntent().getExtras().getString("month");
        int mon = Integer.parseInt(id);
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        String selectQuery = "SELECT  * FROM twodguide where month =" + mon;
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            TwodDataModel itto = new TwodDataModel();
            byte[] byteArray = cursor.getBlob(2);
            byte[] byteArray2 = cursor.getBlob(3);
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Bitmap bm2 = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
            this.month.setText("Month : " + cursor.getString(0));
            this.info.setText(cursor.getString(1));
            this.img.setImageBitmap(bm);
            this.diagram.setImageBitmap(bm2);
            this.fact.setText(cursor.getString(4));
        }
        this.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (singleItemTwoDGuide.this.isImageFitToScreen) {
                    singleItemTwoDGuide.this.isImageFitToScreen = false;
                    singleItemTwoDGuide.this.img.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    singleItemTwoDGuide.this.img.setAdjustViewBounds(true);
                    return;
                }
                singleItemTwoDGuide.this.isImageFitToScreen = true;
                singleItemTwoDGuide.this.img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                singleItemTwoDGuide.this.img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
        this.diagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (singleItemTwoDGuide.this.isImageFitToScreen) {
                    singleItemTwoDGuide.this.isImageFitToScreen = false;
                    singleItemTwoDGuide.this.diagram.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    singleItemTwoDGuide.this.diagram.setAdjustViewBounds(true);
                    return;
                }
                singleItemTwoDGuide.this.isImageFitToScreen = true;
                singleItemTwoDGuide.this.diagram.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                singleItemTwoDGuide.this.diagram.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
    }
}
