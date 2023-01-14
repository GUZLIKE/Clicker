package ru.guzlik.berserkclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static public int count = 0;
    static public int plus_kill = 10;
    ImageView kill, improve;

    static public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kill = (ImageView) findViewById(R.id.kill);
        improve = (ImageView) findViewById(R.id.improve);
        text = (TextView) findViewById(R.id.count);
        Kill();
        Improve();
    }

    void Kill(){
        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
            }
        });
    }

    void Improve(){
        improve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Improve.class);
                startActivity(intent);
            }
        });
    }
}