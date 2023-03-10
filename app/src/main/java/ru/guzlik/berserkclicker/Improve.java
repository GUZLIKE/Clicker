package ru.guzlik.berserkclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Improve extends AppCompatActivity {

    Button angry;
    Button swordLength;
    Button evil;
    SharedPreferences preferences;
    ImageView back;
    static public TextView text;

    int price0 = 100;
    int price1 = 200;
    int price2 = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve);
        angry = (Button) findViewById(R.id.angry);
        swordLength = (Button) findViewById(R.id.swordLength);
        evil = (Button) findViewById(R.id.evil);
        text = (TextView) findViewById(R.id.count);
        back = (ImageView) findViewById(R.id.back);
        text.setText(MainActivity.count + "");
        angry.setText("ГНЕВ (+1 КЛИК) -" + price0);
        swordLength.setText("ДЛИНА МЕЧА (+3 КЛИКА) -" + price1);
        evil.setText("ПРЕДЧУВСТВИЕ ЗЛА (+10 КЛИКОВ) -" + price2);
        backButton();
        Angry();
        swordLength();
        Evil();
        Load();
    }


    void saveAngry(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("price0", price0);
        editor.commit();
    }


    void saveLength(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("price1", price1);
        editor.commit();
    }

    void saveSteal(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("price2", price2);
        editor.commit();
    }


    void Load(){
        preferences = getPreferences(MODE_PRIVATE);
        price0 = preferences.getInt("price0", price0);
        price1 = preferences.getInt("price1", price1);
        price2 = preferences.getInt("price2", price2);
        angry.setText("ГНЕВ (+1 КЛИК) -" + price0);
        swordLength.setText("ДЛИНА МЕЧА (+3 КЛИКА) -" + price1);
        evil.setText("ПРЕДЧУВСТВИЕ ЗЛА (+10 КЛИКОВ) -" + price2);
    }

    void backButton(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Improve.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    /*
    TODO В методах покупки улучшений, может произойти ситуация, когда число кликов будет
    TODO  отрицательным, нужно пофиксить
     */
    void Angry(){
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= price0){
                    MainActivity.plus_kill += 1;
                    MainActivity.count -= price0;
                    price0 += 5;
                    angry.setText("ГНЕВ (+1 КЛИК) -" + price0);
                    text.setText(MainActivity.count + "");
                    MainActivity.text.setText(MainActivity.count + "");
                }
                saveAngry();
            }
        });
    }


    void swordLength(){
        swordLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= price1){
                    MainActivity.plus_kill += 3;
                    MainActivity.count -= price1;
                    price1 += 20;
                    swordLength.setText("ДЛИНА МЕЧА (+3 КЛИКА) -" + price1);
                    MainActivity.text.setText(MainActivity.count + "");
                    text.setText(MainActivity.count + "");
                }
                saveLength();
            }
        });
    }

    void Evil(){
        evil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= price2){
                    MainActivity.plus_kill += 10;
                    MainActivity.count -= price2;
                    price2 += 50;
                    evil.setText("ПРЕДЧУВСТВИЕ ЗЛА (+10 КЛИКОВ) -" + price2);
                    MainActivity.text.setText(MainActivity.count + "");
                    text.setText(MainActivity.count + "");
                }
                saveSteal();
            }
        });
    }

}

