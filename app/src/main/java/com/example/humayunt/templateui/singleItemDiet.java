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

public class singleItemDiet extends AppCompatActivity {
    private SQLiteDatabase db;
    DatabaseHandler dh = new DatabaseHandler(this);
    public TextView avoidfood;
    public ImageView img;
    public TextView foodtype;
    boolean isImageFitToScreen;
    public TextView month;

    public singleItemDiet() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_diet);
        this.db = this.dh.getReadableDatabase();
        this.month = (TextView) findViewById(R.id.month);
        this.foodtype = (TextView) findViewById(R.id.foodtype);
        this.avoidfood = (TextView) findViewById(R.id.avoidfood);
        this.img = (ImageView) findViewById(R.id.dietimage);
        String id = getIntent().getExtras().getString("month");
        int mon = Integer.parseInt(id);
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        String selectQuery = "SELECT  * FROM pregnancydiets where month =" + mon;
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            DietsDataModel Dietsdata = new DietsDataModel();
            byte[] byteArray = cursor.getBlob(3);
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            this.month.setText("Month : " + cursor.getString(0));
            this.foodtype.setText(cursor.getString(1));
            this.img.setImageBitmap(bm);
            this.avoidfood.setText(cursor.getString(2));
        }
        this.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (singleItemDiet.this.isImageFitToScreen) {
                    singleItemDiet.this.isImageFitToScreen = false;
                    singleItemDiet.this.img.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    singleItemDiet.this.img.setAdjustViewBounds(true);
                    return;
                }
                singleItemDiet.this.isImageFitToScreen = true;
                singleItemDiet.this.img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                singleItemDiet.this.img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
    }
}
