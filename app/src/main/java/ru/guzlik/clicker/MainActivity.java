package ru.guzlik.clicker;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int clicks = 0;
    Button button, button2;
    TextView txt;
    ImageView image;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.restart);
        txt = (TextView) findViewById(R.id.txt);
        Click();
        Delete();
        clickImage();
    }

    void Click(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clicks++;
               txt.setText(clicks + "");
            }
        });
    }

    void Delete(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks *= 0;
                txt.setText(clicks + "");
            }
        });
    }

    void clickImage(){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                txt.setText(clicks + "");
            }
        });
    }
}