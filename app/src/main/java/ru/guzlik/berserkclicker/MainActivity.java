package ru.guzlik.berserkclicker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {


    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final Integer NOTIFY_ID = 1;


    boolean isUp;

    public int time = 10;
    static public long count = 0;
    static public long countAngry = 0;
    static public long countSwordLength = 0;
    static public long countEvil = 0;
    static public long countArmor = 0;

    static public long price0 = 100;

    static public long price1 = 200;

    static public long price2 = 300;

    static public long price3 = 500;

    static public long plus_kill = 1;

    public CountDownTimer timer;

    ImageView improve, knight;
    RelativeLayout menuDown, menu;
    Button angry, swordLength, evil, armor;

    static public TextView text, textCountAngry, textCostAngry, textCostLength, textCountLength,
            textCostEvil,textCountEvil, textCountArmor, textCostArmor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        click();
        buttons();
        upgrade();
        Save.init(getApplicationContext());
        new Save().load();
        backgoundNotification();
    }



    /*
    TODO сделать анимацию нажатия кнопки
    TODO сделать механику найма героев (урон/золото в секунду/броня)
    */


    void click(){
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += plus_kill;
                text.setText(count + "");
                new Save().save();
            }
        });
    }


    void buttons(){

        knight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Boss.class);
                startActivity(intent);
            }
        });
    }

    void upgrade(){
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


    public void backgoundNotification(){
        timer = new CountDownTimer(time*1000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
            }

            @Override
            public void onFinish() {
            notification();
            createNotificationChannel();
            }
        }.start();
    }

      void  notification(){
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.berserk)
                .setContentTitle("ГРИФФИТ ПИДОРАС")
                .setContentText("ПИЗДУЙ УБЕЙ ГРИФФИТА,ЗАЕБАЛ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFY_ID, builder.build());

    }



    private void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel berserkClicker = new NotificationChannel(CHANNEL_ID,
                    "Clicker",
                    NotificationManager.IMPORTANCE_DEFAULT);
            berserkClicker.setLightColor(Color.GREEN);
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm.createNotificationChannel(berserkClicker);
        }
    }

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
}