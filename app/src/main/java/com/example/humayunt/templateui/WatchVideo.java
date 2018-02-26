package com.example.humayunt.templateui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class WatchVideo extends YouTubeBaseActivity{
    private Button play;
    private YouTubePlayerView youTubePlayer;
    YouTubePlayer.OnInitializedListener mOnListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        play = (Button) findViewById(R.id.Play);
        youTubePlayer = (YouTubePlayerView) findViewById(R.id.youtubeplayer);
        mOnListner = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("BEu-GPeGXrc");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayer.initialize(YoutubeConfig.getApiKey(),mOnListner);
            }
        });
    }
}
