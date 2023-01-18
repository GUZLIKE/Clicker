package ru.guzlik.berserkclicker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Save extends AppCompatActivity {
    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;

    public Save() {
    }

    public static void init(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    void save() {
//        editor.putInt("count", MainActivity.count);
//        editor.putInt("price0", MainActivity.price0);
//        editor.putInt("price1", MainActivity.price1);
//        editor.putInt("price2", MainActivity.price2);
//        editor.putInt("price3", MainActivity.price3);
//        editor.putInt("countAngry", MainActivity.countAngry);
//        editor.putInt("countSwordLength", MainActivity.countSwordLength);
//        editor.putInt("countEvil", MainActivity.countEvil);
//        editor.putInt("countArmor", MainActivity.countArmor);
//        editor.commit();
        editor.putLong("count", MainActivity.count);
        editor.putLong("price0", MainActivity.price0);
        editor.putLong("price1", MainActivity.price1);
        editor.putLong("price2", MainActivity.price2);
        editor.putLong("price3", MainActivity.price3);
        editor.putLong("countAngry", MainActivity.countAngry);
        editor.putLong("countSwordLength", MainActivity.countSwordLength);
        editor.putLong("countEvil", MainActivity.countEvil);
        editor.putLong("countEvil", MainActivity.countEvil);
        editor.putLong("plus_kill", MainActivity.plus_kill);
        editor.commit();
    }


    void load() {

        MainActivity.price0 = preferences.getLong("price0", MainActivity.price0);
        MainActivity.price1 = preferences.getLong("price1", MainActivity.price1);
        MainActivity.price2 = preferences.getLong("price2", MainActivity.price2);
        MainActivity.price3 = preferences.getLong("price3", MainActivity.price3);
        MainActivity.plus_kill = preferences.getLong("plus_kill", MainActivity.plus_kill);
        MainActivity.count = preferences.getLong("count", MainActivity.count);
        MainActivity.countAngry = preferences.getLong("countAngty", MainActivity.countAngry);
        MainActivity.countSwordLength = preferences.getLong("countSwordLength", MainActivity.countSwordLength);
        MainActivity.countEvil = preferences.getLong("countEvil", MainActivity.countEvil);
        MainActivity.countArmor = preferences.getLong("countArmor", MainActivity.countArmor);
        MainActivity.textCountAngry.setText("УРОВЕНЬ: " + MainActivity.countAngry);
        MainActivity.textCostAngry.setText("СТОИМОСТЬ: " + MainActivity.price0);
        MainActivity.text.setText(MainActivity.count + "");
        MainActivity.textCountLength.setText("УРОВЕНЬ: " + MainActivity.countSwordLength);
        MainActivity.textCostLength.setText("СТОИМОСТЬ: " + MainActivity.price1);
        MainActivity.textCountEvil.setText("УРОВЕНЬ :" + MainActivity.countEvil);
        MainActivity.textCostLength.setText("СТОИМОСТЬ: " + MainActivity.price2);
        MainActivity.textCountArmor.setText("УРОВЕНЬ: " + MainActivity.countArmor);
        MainActivity.textCostArmor.setText("СТОИМОСТЬ: " + MainActivity.price3);

    }

    void delete(){
        editor.clear().commit();
    }
}
