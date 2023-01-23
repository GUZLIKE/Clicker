package ru.guzlik.berserkclicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Merc extends AppCompatActivity {

    Button kaska,griff,farn,isid;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        kaska = (Button) findViewById(R.id.kaska);
        griff = (Button) findViewById(R.id.griffith);
        farn = (Button) findViewById(R.id.farnese);
        isid = (Button) findViewById(R.id.isido);

    }

    void merc(){
        kaska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        griff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        farn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        isid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
