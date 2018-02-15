package com.example.humayunt.templateui;

import android.app.Activity;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static String DB_NAME = "gestationtry.db";
    public static final String TAG = "DatabaseHandler";
    private String DB_PATH = "/data/data/com.example.humayunt.templateui/databases/";
    private Activity activity;
    private SQLiteDatabase db;
    String mydate;

    public DatabaseHandler(Activity activity) throws IOException {
        super(activity, DB_NAME, null, 1);
        this.activity = activity;
        if (checkdatabase()) {
            Log.d("Trong", "Database exists");
            opendatabase();
            return;
        }
        System.out.println("Database doesn't exist");
        createdatabase();
    }

    public void createdatabase() throws IOException {
        if (!checkdatabase()) {
            getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkdatabase() {
        boolean checkdb = false;
        try {
            String myPath = this.DB_PATH + DB_NAME;
            Log.d("Trong", "DB_PATH + DB_NAME " + this.DB_PATH + DB_NAME);
            checkdb = new File(myPath).exists();
        } catch (SQLiteException e) {
            Log.d("Trong", "Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        AssetManager am = this.activity.getAssets();
        OutputStream os = new FileOutputStream(this.DB_PATH + DB_NAME);
        byte[] b = new byte[1024];
        Arrays.sort(am.list(""));
        InputStream is = am.open("gestationtry.db");
        while (true) {
            int r = is.read(b);
            if (r != -1) {
                os.write(b, 0, r);
            } else {
                Log.i("BABY_DATABASE_HELPER", "Copying the database (part  of 9)");
                is.close();
                os.close();
                return;
            }
        }
    }

    public void opendatabase() throws SQLException {
        this.db = SQLiteDatabase.openDatabase(this.DB_PATH + DB_NAME, null, 0);
    }

    public synchronized void close() {
        if (this.db != null) {
            this.db.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            copydatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TwodDataModel> getInputMonths() {
        String fact = "None";
        ArrayList<TwodDataModel> ittoentity = new ArrayList();
        String selectQuery = "SELECT  * FROM twodguide";
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TwodDataModel itto = new TwodDataModel();
                byte[] byteArray = cursor.getBlob(2);
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                itto.setMonth(cursor.getString(0));

                itto.setFact(cursor.getString(4) != null ? cursor.getString(4) : "No Fact Available ");
                itto.setImg(bm);
                ittoentity.add(itto);
            } while (cursor.moveToNext());
        }
        return ittoentity;
    }
    public ArrayList<DietsDataModel> getDiets() {
        String fact = "None";
        ArrayList<DietsDataModel> ittoentity = new ArrayList();
        String selectQuery = "SELECT  * FROM twodguide";
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DietsDataModel itto = new DietsDataModel();
                byte[] byteArray = cursor.getBlob(2);
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                itto.setMonth(cursor.getString(0));

              //  itto.setFact(cursor.getString(4) != null ? cursor.getString(4) : "No Fact Available ");
                itto.setImg(bm);
                ittoentity.add(itto);
            } while (cursor.moveToNext());
        }
        return ittoentity;
    }
    public ArrayList<ExerciseDataModel> getExcercise() {
        String fact = "None";
        ArrayList<ExerciseDataModel> ittoentity = new ArrayList();
        String selectQuery = "SELECT  * FROM twodguide";
        Log.d("rawquery", "inputs" + selectQuery);
        Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ExerciseDataModel itto = new ExerciseDataModel();
                byte[] byteArray = cursor.getBlob(2);
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                itto.setMonth(cursor.getString(0));

                //  itto.setFact(cursor.getString(4) != null ? cursor.getString(4) : "No Fact Available ");
                itto.setImg(bm);
                ittoentity.add(itto);
            } while (cursor.moveToNext());
        }
        return ittoentity;
    }
}
