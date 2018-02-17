package com.example.humayunt.templateui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humayunt.templateui.R;
import com.example.humayunt.templateui.SingleItem.singleItemTwoDGuide;

/**
 * Created by HumayunT on 2/17/2018.
 */

public class summary extends Fragment {
    TextView first,second,third;
    public ImageView diagram;
    public ImageView img;
    boolean isImageFitToScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.summary, container, false);
        first = (TextView) rootView.findViewById(R.id.first);
        second = (TextView)rootView.findViewById(R.id.second);
        third = (TextView)rootView.findViewById(R.id.third);
        this.img = (ImageView) rootView.findViewById(R.id.img);
        this.diagram = (ImageView) rootView.findViewById(R.id.diagram);
        first.setText("The first trimester is the most crucial to your baby's development. During this period, your baby's body structure and organ systems develop. Most miscarriages and birth defects occur during this period.  Your body also undergoes major changes during the first trimester. These changes often cause a variety of symptoms, including nausea, fatigue, breast tenderness and frequent urination. Although these are common pregnancy symptoms, every woman has a different experience. For example, while some may experience an increased energy level during this period, others may feel very tired and emotional.");
        second.setText("The second trimester of pregnancy is often called the &quot;golden period&quot; because many of the unpleasant effects of early pregnancy disappear. During the second trimester, you're likely to experience decreased nausea, better sleep patterns and an increased energy level. However, you may experience a whole new set of symptoms, such as back pain, abdominal pain, leg cramps, constipation and heartburn.  Somewhere between 16 weeks and 20 weeks, you may feel your baby's first fluttering movements.");
        third.setText("You have now reached your final stretch of pregnancy and are probably very excited and anxious for the birth of your baby. Some of the physical symptoms you may experience during this period include shortness of breath, hemorrhoids, urinary incontinence, varicose veins and sleeping problems. Many of these symptoms arise from the increase in the size of your uterus, which expands from approximately 2 ounces before pregnancy to 2.5 pounds at the time of birth.");


        this.img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (summary.this.isImageFitToScreen) {
                    summary.this.isImageFitToScreen = false;
                    summary.this.img.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    summary.this.img.setAdjustViewBounds(true);
                    return;
                }
                summary.this.isImageFitToScreen = true;
                summary.this.img.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                summary.this.img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
        this.diagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (summary.this.isImageFitToScreen) {
                    summary.this.isImageFitToScreen = false;
                    summary.this.diagram.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    summary.this.diagram.setAdjustViewBounds(true);
                    return;
                }
                summary.this.isImageFitToScreen = true;
                summary.this.diagram.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                summary.this.diagram.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
        return rootView;
    }
}
