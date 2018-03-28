package com.example.humayunt.templateui.SidePanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.humayunt.templateui.R;


public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
       // bar.setNumStars(5);
      //  bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.textResult);
        TextView t1=(TextView)findViewById(R.id.tvv4);
        ImageView ima = (ImageView) findViewById(R.id.resultImage);

        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("score");
        String scor = String.valueOf(score);
        t1.setText(scor.toString());
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0 : t.setText("You scored 0%, keep learning");
                    ima.setImageResource(R.drawable.unhappy);
                break;
            case 1 : t.setText("You scored 10%, keep learning");
                    ima.setImageResource(R.drawable.unhappy);
                break;
            case 2  : t.setText("You have 20%, study better");
                        ima.setImageResource(R.drawable.unhappy);
                break;
            case 3: t.setText("You have 30%, study better");
                ima.setImageResource(R.drawable.sad);
            break;
            case 4 : t.setText("You have 40%, keep learning");
                ima.setImageResource(R.drawable.confused);
                break;
            case  5: t.setText("You have 50%, keep learning");
                ima.setImageResource(R.drawable.smiling);
                break;
            case 6 : t.setText("You have 60%, good attempt");
                ima.setImageResource(R.drawable.smiling);
                break;
            case  7: t.setText("You have 70%, good attempt");
                ima.setImageResource(R.drawable.happy);
                break;
            case 8 :t.setText("You have 80% Hmmmm.. maybe you have been reading a lot ");
                ima.setImageResource(R.drawable.smart);
                break;
            case 9:t.setText("You have 90%  maybe you have been reading a lot ");
                ima.setImageResource(R.drawable.smart);
                break;
            case 10:t.setText(" Whao, you have 100%,");
                ima.setImageResource(R.drawable.nerd);
            break;
        }
    }
}
