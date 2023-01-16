package ru.guzlik.berserkclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Save extends AppCompatActivity {
    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;
    public  Save(){}

    public static void init(Context context){
        if (preferences == null){
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    void save(){
        editor.putInt("count", MainActivity.count);
        editor.putInt("price0", MainActivity.price0);
        editor.putInt("price1", MainActivity.price1);
        editor.putInt("price2", MainActivity.price2);
        editor.putInt("price3", MainActivity.price3);
        editor.putInt("countAngry", MainActivity.countAngry);
        editor.putInt("countSwordLength", MainActivity.countSwordLength);
        editor.putInt("countEvil", MainActivity.countEvil);
        editor.putInt("countArmor", MainActivity.countArmor);
        editor.commit();
    }


    void load(){
        MainActivity.price0 = preferences.getInt("price0", MainActivity.price0);
        MainActivity.price1 = preferences.getInt("price1", MainActivity.price1);
        MainActivity.price2 = preferences.getInt("price2", MainActivity.price2);
        MainActivity.price3 = preferences.getInt("price3", MainActivity.price3);
        MainActivity.count = preferences.getInt("count", MainActivity.count);
        MainActivity.countAngry = preferences.getInt("countAngry",MainActivity.countAngry );
        MainActivity.countSwordLength = preferences.getInt("countSwordLength", MainActivity.countSwordLength);
        MainActivity.countEvil = preferences.getInt("countEvil", MainActivity.countEvil);
        MainActivity.countArmor = preferences.getInt("countArmor", MainActivity.countArmor);
        MainActivity.textCountAngry.setText("УРОВЕНЬ: " + MainActivity.countAngry);
        MainActivity.textCostAngry.setText("СТОИМОСТЬ: " + MainActivity.price0);
        MainActivity.text.setText(MainActivity.count + "");
        MainActivity.textCountLength.setText("УРОВЕНЬ:" + MainActivity.countSwordLength);
        MainActivity.textCostLength.setText("СТОИМОСТЬ:" + MainActivity.price1);
        MainActivity.textCountEvil.setText("УРОВЕНЬ:" + MainActivity.countEvil);
        MainActivity.textCostLength.setText("СТОИМОСТЬ:" + MainActivity.price2);
        MainActivity.textCountArmor.setText("УРОВЕНЬ:" + MainActivity.countArmor);
        MainActivity.textCostArmor.setText("СТОИМОСТЬ:" + MainActivity.price3);

    }

}
