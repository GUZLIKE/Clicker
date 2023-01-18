package ru.guzlik.berserkclicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.security.SecureRandom;
import java.util.Random;

public class Boss extends AppCompatActivity {

    public long damage = MainActivity.plus_kill;
    Random random = new SecureRandom();
    int time = random.nextInt(60);
    long clicks = 0;
    int health_boss = random.nextInt(50);
    ImageView boss, back;
    ConstraintLayout layoutFight;
    TextView health, fightTime, fightClick, damm;
    public static CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
        layoutFight = (ConstraintLayout) findViewById(R.id.menuBoss);
        boss = (ImageView) findViewById(R.id.boss);
        back = (ImageView) findViewById(R.id.back);
        health = (TextView) findViewById(R.id.health);
        fightTime = (TextView) findViewById(R.id.time);
        fightClick = (TextView) findViewById(R.id.clicks);
        damm = (TextView) findViewById(R.id.damm);
        boss.setEnabled(true);
        health.setText("ЗДОРОВЬЕ МОНСТРА: " + health_boss);
        fightClick.setText("КЛИКОВ: " + clicks);
        fight();
        backButton();
    }



    void backButton(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Boss.this,MainActivity.class);
                startActivity(intent);
                timer.cancel();
            }
        });
    }

    void fight(){
        timer = new CountDownTimer(time*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                fightTime.setText("ВРЕМЯ:" + (time+1));
            }
            @Override
            public void onFinish() {
                boss.setEnabled(false);
                if (health_boss > 0) {
                    Toast.makeText(getApplicationContext(), "Вы не успели, бонусы не начислены!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Ваши удары стали в 2x раза сильнее!", Toast.LENGTH_LONG).show();
                    MainActivity.plus_kill*=2;
                }
                Intent intent = new Intent(Boss.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

        layoutFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                health_boss -= damage;
                if (health_boss <= 0){
                    timer.cancel();
                    timer.onFinish();
                }
                //ПОТОМ ДОБАВИТЬ. ТОЛЬКО ДЛЯ ДЕМОНСТРАЦИИ УВЕЛИЧЕНИЯ УРОНА КАЖДОГО КЛИКА
//                else {
                    health.setText("ЗДОРОВЬЕ МОНСТРА: " + health_boss);
                    fightClick.setText("КЛИКОВ: " + clicks);
//                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Boss.this, MainActivity.class); startActivity(intent); timer.cancel();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
