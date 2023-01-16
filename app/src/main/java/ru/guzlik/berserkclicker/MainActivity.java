package ru.guzlik.berserkclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ////
    Button myButton;
    View myView;
    boolean isUp;
    ////
    static public int count = 0;
    int countAngry = 0;
    int countSwordLength = 0;
    int countEvil = 0;
    int countArmor = 0;

    static int price0 = 100;

    int price1 = 200;

    int price2 = 300;

    int price3 = 500;

    static public int plus_kill = 1;
    ImageView kill, improve, knight;
    RelativeLayout menuUp, menuDown;
    Button angry, swordLength, evil, armor;
    static SharedPreferences preferences;

    static public TextView text, textCountAngry, textCostAngry, textCostLength, textCountLength,
            textCostEvil,textCountEvil, textCountArmor, textCostArmor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuUp = (RelativeLayout) findViewById(R.id.mainMenuUp);

        myView = findViewById(R.id.my_view);
        myView.setVisibility(View.INVISIBLE);
        isUp = false;

        menuDown = (RelativeLayout) findViewById(R.id.mainMenuDown);
        angry = (Button) findViewById(R.id.buttonAngry);
        swordLength = (Button) findViewById(R.id.buttonSwordLength);
        evil = (Button) findViewById(R.id.buttonEvil);
        armor = (Button) findViewById(R.id.buttonArmor);
        textCountAngry = (TextView) findViewById(R.id.textCountAngry);
        textCostAngry = (TextView) findViewById(R.id.textCostAngry);
        textCostLength = (TextView) findViewById(R.id.textCostLength);
        textCountLength = (TextView) findViewById(R.id.textCountLength);
        text = (TextView) findViewById(R.id.count);
        textCostEvil = (TextView) findViewById(R.id.textCostEvil);
        textCountEvil = (TextView) findViewById(R.id.textCountEvil);
        textCostArmor = (TextView) findViewById(R.id.textCostArmor);
        textCountArmor = (TextView) findViewById(R.id.textCountArmor);
        improve = (ImageView) findViewById(R.id.improve);
        knight = (ImageView) findViewById(R.id.knight);
        Click();
        Load();
        Buttons();
        Upgrade();

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


    void Click(){
        menuUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                clicksSave();
            }
        });
    }


    void Buttons(){

        knight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Boss.class);
                startActivity(intent);
            }
        });
    }

    void Upgrade(){
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
                    clicksSave();
                }
            }
        });

        swordLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= price1){
                    plus_kill += 3;
                    countSwordLength++;
                    count -= price1;
                    price1 += 20;
                    textCountLength.setText("УРОВЕНЬ:" + countSwordLength);
                    textCostLength.setText("СТОИМОСТЬ:" + price1);
                    text.setText(count + "");
                }
            }
        });

        evil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count >= price2){
                    plus_kill += 10;
                    countEvil++;
                    count -= price2;
                    price2 += 50;
                    textCountEvil.setText("УРОВЕНЬ:" + countEvil);
                    textCostLength.setText("СТОИМОСТЬ:" + price2);
                    text.setText(count + "");
                }
            }
        });

        armor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >= price3){
                    plus_kill += 30;
                    countArmor++;
                    count -= price3;
                    price3 += 500;
                    textCountArmor.setText("УРОВЕНЬ:" + countArmor);
                    textCostArmor.setText("СТОИМОСТЬ:" + price3);
                    text.setText(count + "");
                }
            }
        });


    }


    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    public void onSlideViewButtonClick(View view) {
        if (isUp) {
            slideDown(myView);
        } else {
            slideUp(myView);
        }
        isUp = !isUp;
    }


    void Saves(){
        //Метод сохранения кол-ва кликов
            preferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clicks", count);
            editor.commit();


    }



    //Метод сохранения кол-ва кликов
    public void clicksSave(){
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clicks", count);
        editor.commit();
    }

    //Метод загрузки кол-ва кликов
    public void Load(){
        preferences = getPreferences(MODE_PRIVATE);
        count = preferences.getInt("clicks", 0);
        text.setText(count + "");
    }

}