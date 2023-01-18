package ru.guzlik.berserkclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    ////
    Button myButton;
    View myView;
    boolean isUp;
    ////
    static public int count = 0;
    static public int countAngry = 0;
    static public int countSwordLength = 0;
    static public int countEvil = 0;
    static public int countArmor = 0;

    static public int price0 = 100;

    static public int price1 = 200;

    static public int price2 = 300;

    static public int price3 = 500;

    static public int plus_kill = 1;
    ImageView improve, knight;
    RelativeLayout menuDown, menu;
    LinearLayout menuUp;
    Button angry, swordLength, evil, armor;
    static SharedPreferences preferences;

    static public TextView text, textCountAngry, textCostAngry, textCostLength, textCountLength,
            textCostEvil,textCountEvil, textCountArmor, textCostArmor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, RunningManAnimation.class);
        startActivity(intent);

        menu = (RelativeLayout) findViewById(R.id.mainMenu);
        menuDown = (RelativeLayout) findViewById(R.id.mainMenuDown);
        menuDown.setVisibility(View.GONE);
        isUp = false;

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
        Buttons();
        Upgrade();
        Save.init(getApplicationContext());
        new Save().load();



    }



    /*
    TODO сделать анимацию нажатия кнопки
    TODO сделать механику найма героев (урон/золото в секунду/броня)
    */


    void Click(){
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                new Save().save();
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
                    new Save().save();
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
                    new Save().save();
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
                    textCostEvil.setText("СТОИМОСТЬ:" + price2);
                    text.setText(count + "");
                    new Save().save();
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
                    new Save().save();
                }
            }
        });


    }


    ////////////////////////
    public void onSlideViewButtonClick(View view) throws InterruptedException {
        toggle(isUp);
        isUp = !isUp;
    }

    private void toggle(boolean show) throws InterruptedException {
        View menuDown = findViewById(R.id.mainMenuDown);
        ViewGroup parent = findViewById(R.id.mainMenu);

        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(500);
        transition.addTarget(R.id.mainMenuDown);

        TransitionManager.beginDelayedTransition(parent, transition);
        menuDown.setVisibility(show ? View.GONE : View.VISIBLE);

    }
    ////////////////////////

//    public void slideUp(View view){
//        view.setVisibility(View.VISIBLE);
//        TranslateAnimation animate = new TranslateAnimation(
//                0,                 // fromXDelta
//                0,                 // toXDelta
//                view.getHeight(),  // fromYDelta
//                0);                // toYDelta
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        view.startAnimation(animate);
//    }
//
//    // slide the view from its current position to below itself
//    public void slideDown(View view){
//        TranslateAnimation animate = new TranslateAnimation(
//                0,                 // fromXDelta
//                0,                 // toXDelta
//                0,                 // fromYDelta
//                view.getHeight()); // toYDelta
//        animate.setDuration(500);
//        animate.setFillAfter(true);
//        view.startAnimation(animate);
//    }
//
//    public void onSlideViewButtonClick(View view) {
//        if (isUp) {
//            slideDown(menuDown);
//        } else {
//            slideUp(menuDown);
//        }
//        isUp = !isUp;
//    }
}