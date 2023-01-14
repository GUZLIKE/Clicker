package ru.guzlik.berserkclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
        kill = (ImageView) findViewById(R.id.kill);
        improve = (ImageView) findViewById(R.id.improve);
        text = (TextView) findViewById(R.id.count);
        Load();
        Kill();
        Improve();
    }

    void Kill(){
        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                Save();
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


    //Метод сохранения кол-ва кликов

    void Save(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", count);
        editor.commit();
    }

    //Метод загрузки кол-ва кликов
    void Load(){
        preferences = getPreferences(MODE_PRIVATE);
        count = preferences.getInt("clicks", 0);
        text.setText(count + "");
    }

}