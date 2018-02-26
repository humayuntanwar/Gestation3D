package com.example.humayunt.templateui.SingleItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;
import com.example.humayunt.templateui.YoutubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.IOException;

/*
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifTextView;
*/

public class singleItemExercise extends YouTubeBaseActivity {
    private SQLiteDatabase db;
    DatabaseHandler dh = new DatabaseHandler(this);
    public ImageView diagram;
    public TextView name, position,steps,repeat,precaution;
    boolean isImageFitToScreen;
    public TextView month;
  // public GifImageView gif;
    private YouTubePlayerView youTubePlayer;
    YouTubePlayer.OnInitializedListener mOnListner;

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
        //gif = (GifImageView) findViewById(R.id.gif);
        youTubePlayer = (YouTubePlayerView) findViewById(R.id.youtubeplayer);

        String id = getIntent().getExtras().getString("month");
        int mon = Integer.parseInt(id);
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        String selectQuery = "SELECT  * FROM exercise where month =" + mon;
        Log.d("rawquery", "inputs" + selectQuery);
        final Cursor cursor = this.db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
           /* if(cursor.getBlob(6)!=null) {
                byte[] bytes = cursor.getBlob(6);
                try {

                    GifDrawable gifimage = new GifDrawable(bytes);
                    gif.setImageDrawable(gifimage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

            this.month.setText( cursor.getString(1));
            this.position.setText(cursor.getString(2));
            this.steps.setText(cursor.getString(3));
            this.repeat.setText(cursor.getString(4));
            this.precaution.setText(cursor.getString(5));


        }

        mOnListner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(cursor.getString(7));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayer.initialize(YoutubeConfig.getApiKey(),mOnListner);
    }
}
