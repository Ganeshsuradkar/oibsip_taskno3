package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageView im,b,b2,flag,refdup,dupflag;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer cm;
    ListView listView;
    long elapsedMillis =0;
    int c=0;

    ArrayList<String>arr=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        im=findViewById(R.id.refresh);
        flag=findViewById(R.id.flag);
        dupflag=findViewById(R.id.dupflag);
        refdup=findViewById(R.id.refduplicate);

        listView=findViewById(R.id.list);



        icanchor=findViewById(R.id.icanchor);
        cm=findViewById(R.id.timehere);

        //load amination
        roundingalone= AnimationUtils.loadAnimation(this,R.anim.roundingalone);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                im.setVisibility(View.GONE);
                refdup.setVisibility(View.VISIBLE);
                flag.setVisibility(View.VISIBLE);
                dupflag.setVisibility(View.GONE);
                b2.setVisibility(View.VISIBLE);
                b.setVisibility(View.GONE);

                if(c>0){
                    cm.setBase(SystemClock.elapsedRealtime());
                    cm.start();

                    c=0;

                }
                else {
                    icanchor.startAnimation(roundingalone);
                    //start time
                    cm.setBase(SystemClock.elapsedRealtime() - elapsedMillis);
                    cm.start();
                }




            }


        });


        //STOP ANIMATION BUTTON
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.stop();
                b.setVisibility(View.VISIBLE);
                b2.setVisibility(View.GONE);
                icanchor.clearAnimation();
                im.setVisibility(View.VISIBLE);
                refdup.setVisibility(View.GONE);
                flag.setVisibility(View.GONE);
                dupflag.setVisibility(View.VISIBLE);



                elapsedMillis = SystemClock.elapsedRealtime() - cm.getBase();




            }
        });

    }


    public void refresh(View view) {

        c++;
        cm.stop();
        cm.setBase(SystemClock.elapsedRealtime());

        b.setVisibility(View.VISIBLE);
        b2.setVisibility(View.GONE);
        im.setVisibility(View.GONE);
        refdup.setVisibility(View.VISIBLE);
        flag.setVisibility(View.GONE);
        dupflag.setVisibility(View.VISIBLE);

        arr.clear();
        listView.setAdapter(null);
        icanchor.clearAnimation();

    }

    public void flag(View view) {
        String s=cm.getText().toString();
        arr.add(s);
        arrayAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,arr);
        listView.setAdapter(arrayAdapter);

    }
}