package ru.guzlik.berserkclicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Improve extends AppCompatActivity {

    Button angry;
    Button swordLength;
    Button steal;
    static public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        angry = (Button) findViewById(R.id.angry);
        swordLength = (Button) findViewById(R.id.swordLength);
        steal = (Button) findViewById(R.id.steal);
        text = (TextView) findViewById(R.id.count);
        text.setText(MainActivity.count + "");
        Angry();
        swordLength();
        Steal();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void Angry(){
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= 100){
                    MainActivity.plus_kill += 1;
                    MainActivity.count -= 100;
                    MainActivity.text.setText(MainActivity.count + "");
                    text.setText(MainActivity.count + "");
                }
            }
        });
    }


    void swordLength(){
        swordLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= 200){
                    MainActivity.plus_kill += 3;
                    MainActivity.count -= 200;
                    MainActivity.text.setText(MainActivity.count + "");
                    text.setText(MainActivity.count + "");
                }
            }
        });
    }

    void Steal(){
        steal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.count >= 300){
                    MainActivity.plus_kill += 10;
                    MainActivity.count -= 300;
                    MainActivity.text.setText(MainActivity.count + "");
                    text.setText(MainActivity.count + "");
                }
            }
        });
    }

}

