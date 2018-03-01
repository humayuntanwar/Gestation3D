package com.example.humayunt.templateui.SidePanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.humayunt.templateui.R;


public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0 | 1: t.setText("You scored 0%, keep learning");
                break;
            case 2 | 3: t.setText("You have 20%, study better");
                break;
            case 4 | 5: t.setText("You have 40%, keep learning");
                break;
            case 6 | 7: t.setText("You have 60%, good attempt");
                break;
            case 8 | 9:t.setText("You have 80% Hmmmm.. maybe you have been reading a lot ");
                break;
            case 10:t.setText(" Whao, you have 100%,");
            break;
        }
    }
}
