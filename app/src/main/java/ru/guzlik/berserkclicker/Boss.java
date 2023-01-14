package ru.guzlik.berserkclicker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;
import java.util.Random;

public class Boss extends AppCompatActivity {

    static public int damage = MainActivity.plus_kill;
    Random random = new SecureRandom();
    int time = random.nextInt(60);
    int health_boss = random.nextInt(300);
    ImageView boss;
    TextView health, fightTime;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        boss = (ImageView) findViewById(R.id.boss);
        health = (TextView) findViewById(R.id.health);
        fightTime = (TextView) findViewById(R.id.time);
        boss.setEnabled(true);
        Time();
        Attack();
//        timer = new CountDownTimer(30000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//            time--;
//            fightTime.setText("ВРЕМЯ:" + time);
//
//            }
//
//            @Override
//            public void onFinish() {
//                boss.setEnabled(false);
//            }
//        };
    }

    void Time(){
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                fightTime.setText("ВРЕМЯ:" + time);

            }

            @Override
            public void onFinish() {
                boss.setEnabled(true);
            }
        };

    }


    void Attack(){
        boss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                health.setText("ЗДОРОВЬЕ:" + health_boss);
                health_boss -= damage;
                timer.start();
            }
        });
    }

}
