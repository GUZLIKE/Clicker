package ru.guzlik.berserkclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    static public int count = 0;
    int countAngry = 0;
    int countSwordLegth = 0;
    int countEvil = 0;
    int countArmor = 0;

    int price0 = 100;
    static public int plus_kill = 1;
    ImageView kill, improve, knight;
    RelativeLayout menuUp, menuDown;
    Button angry, swordLength, evil, armor;
    static SharedPreferences preferences;

    static public TextView text, textCountAngry, textCostAngry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuUp = (RelativeLayout) findViewById(R.id.mainMenuUp);
        menuDown = (RelativeLayout) findViewById(R.id.mainMenuDown);
        angry = (Button) findViewById(R.id.buttonAngry);
        swordLength = (Button) findViewById(R.id.buttonSwordLength);
        evil = (Button) findViewById(R.id.buttonEvil);
        armor = (Button) findViewById(R.id.buttonArmor);
        textCountAngry = (TextView) findViewById(R.id.textCountAngry);
        textCostAngry = (TextView) findViewById(R.id.textCostAngry);
        improve = (ImageView) findViewById(R.id.improve);
        knight = (ImageView) findViewById(R.id.knight);
        text = (TextView) findViewById(R.id.count);
        Load();
        Buttons();
    }

    /*
    TODO сделать прокрутку улучшений - COMPLETE
    TODO разделить экран на 2 части - COMPLETE
    TODO сделать кнопку назад - COMPLETE
    TODO сделать количество улучшений рядом с кнопками - COMPLETE
    TODO починить - сделать сохранение улучшений (цены увеличиваются, а предмета нет)
    TODO таймер не должен быть отрицательным - COMPLETE
    TODO хп монстра не должен быть отрицательным - COMPLETE
    TODO сделать анимацию нажатия кнопки
    TODO сделать таймер для усиления после убийства босс
    TODO сделать механику найма героев (урон/золото в секунду/броня)
    */

    void Buttons(){
        menuUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                Save();
            }
        });

        improve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Improve.class);
                startActivity(intent);
                Save();
            }
        });

        knight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Boss.class);
                startActivity(intent);
            }
        });

        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count >= price0) {
                    count -= price0;
                    price0 += 20;
                    countAngry++;
                    plus_kill += 1;
                    textCountAngry.setText("УРОВЕНЬ: " + countAngry);
                    textCostAngry.setText("СТОИМОСТЬ: " + price0);
                    text.setText(count + "");
                    Save();
                }
            }
        });
    }



    //Метод сохранения кол-ва кликов
    public void Save(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", count);
        editor.putInt("plus_kill", plus_kill);
        editor.commit();
    }

    //Метод загрузки кол-ва кликов
    public void Load(){
        preferences = getPreferences(MODE_PRIVATE);
        count = preferences.getInt("clicks", 0);
        plus_kill = preferences.getInt("plus_kill", 1);
        text.setText(count + "");
    }

}