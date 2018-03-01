package com.example.humayunt.templateui.SidePanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.humayunt.templateui.DataModel.QuizDataModel;
import com.example.humayunt.templateui.DatabaseHandler;
import com.example.humayunt.templateui.R;

public class QuizActivity extends AppCompatActivity {
    List<QuizDataModel> quesList;
    int score=0;
    int qid=0;
    String q;
    QuizDataModel currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        try {
            db = new DatabaseHandler(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        quesList=db.getQuiz();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();
       // Toast.makeText(this, currentQ.getQUESTION(), Toast.LENGTH_LONG);
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score"+score);
                }
                if(qid<10){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity.this, Result.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        Log.d("hun",currentQ.getOPTIONA()+"jvh");
        rda.setText(currentQ.getOPTIONA());
        rdb.setText(currentQ.getOPTIONB());
        rdc.setText(currentQ.getOPTIONC());
        qid++;
    }
}
