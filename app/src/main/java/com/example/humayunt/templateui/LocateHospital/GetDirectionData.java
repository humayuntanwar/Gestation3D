package com.example.humayunt.templateui.LocateHospital;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.humayunt.templateui.LocateHospital.dataParser;
import com.example.humayunt.templateui.LocateHospital.downloadURL;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

/**
 * Created by HumayunT on 11/20/2017.
 */

public class GetDirectionData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance;
    LatLng latLng;
    PolylineOptions options = new PolylineOptions();
    Polyline pl= null;


    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];
        downloadURL downloadUrl = new downloadURL();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {

        String[] directionsList;
        dataParser parser = new dataParser();
        directionsList = parser.parseDirections(s);
        displayDirection(directionsList);

    }

    public void displayDirection(String[] directionsList)
    {
        try {
            if (pl != null) {
                pl.remove();
                pl = null;
                Log.i("gym", "displayDirection:");

            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
            Log.i("gym", "displ");
            int count = directionsList.length;
            for (int i = 0; i < count; i++) {
                options.color(Color.RED);
                options.width(10);
                options.addAll(PolyUtil.decode(directionsList[i]));
                pl = mMap.addPolyline(options);
            }
        }

    }



/*

 <application >
    <activity android:name=".UnityPlayerActivity"/>
  </application>
  -ignorewarnings
   Intent intent = getIntent();
        mUnityPlayer = new UnityPlayer(this);
        mUnityPlayer.UnitySendMessage("Main Camera", "LoadScene",intent.getStringExtra("message"));
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();

*/


}

