package ru.guzlik.berserkclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences preferences;

    static public TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences;
        kill = (ImageView) findViewById(R.id.kill);
        improve = (ImageView) findViewById(R.id.improve);
        text = (TextView) findViewById(R.id.count);
        load();
        Kill();
        Improve();
    }

    void Kill(){
        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                save();
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


    void save(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", count);
        editor.commit();
    }

    void load(){
        preferences = getPreferences(MODE_PRIVATE);
        count = preferences.getInt("clicks", 0);
        text.setText(count + "");
    }

}