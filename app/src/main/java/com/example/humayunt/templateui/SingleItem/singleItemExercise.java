package com.example.humayunt.templateui.SingleItem;

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

import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;

import java.io.IOException;
public class singleItemExercise extends AppCompatActivity {
    private SQLiteDatabase db;
    DatabaseHandler dh = new DatabaseHandler(this);
    public ImageView diagram;
    public TextView name, position,steps,repeat,precaution;
    boolean isImageFitToScreen;
    public TextView month;

    public singleItemExercise() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_exercise);
        this.db = this.dh.getReadableDatabase();
        this.month = (TextView) findViewById(R.id.textView);
        this.position = (TextView) findViewById(R.id.position);
        this.steps = (TextView) findViewById(R.id.steps);
        this.repeat = (TextView) findViewById(R.id.repeat);
        this.precaution = (TextView) findViewById(R.id.precaution);
        String id = getIntent().getExtras().getString("month");
        int mon = Integer.parseInt(id);
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        String selectQuery = "SELECT  * FROM exercise where month =" + mon;
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            this.month.setText( cursor.getString(1));
            this.position.setText(cursor.getString(2));
            this.steps.setText(cursor.getString(3));
            this.repeat.setText(cursor.getString(4));
            this.precaution.setText(cursor.getString(5));


        }
    }
}
