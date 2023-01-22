package ru.guzlik.berserkclicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.View;

public class TestAnimation extends View {
    boolean fight = false;
    int screenHeight, screenWidth, newHeigth, newWidth;
    int forestX = 0, backForestX = 0, grassX = 0, backX = 0;
    public static int heroX;
    public static int heroY;
    public static int heroFrame = 0;
    public int monsterFrame;
    public static int monsterX;
    public int monsterY;
    Bitmap forest, backForest, grass, back;
    static Bitmap[] hero = new Bitmap[18];
    Bitmap monster[] = new Bitmap[37];
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS=30;

    public TestAnimation(Context context) {
        super(context);

        forest = BitmapFactory.decodeResource(getResources(), R.drawable.new_forest);
        backForest = BitmapFactory.decodeResource(getResources(), R.drawable.new_back_forest);
        grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.backgound_game);

        hero[0] = BitmapFactory.decodeResource(getResources(), R.drawable.guts0);
        hero[1] = BitmapFactory.decodeResource(getResources(), R.drawable.guts1);
        hero[2] = BitmapFactory.decodeResource(getResources(), R.drawable.guts2);
        hero[3] = BitmapFactory.decodeResource(getResources(), R.drawable.guts3);
        hero[4] = BitmapFactory.decodeResource(getResources(), R.drawable.guts4);
        hero[5] = BitmapFactory.decodeResource(getResources(), R.drawable.guts5);
        hero[6] = BitmapFactory.decodeResource(getResources(), R.drawable.guts6);
        hero[7] = BitmapFactory.decodeResource(getResources(), R.drawable.guts7);
        hero[8] = BitmapFactory.decodeResource(getResources(), R.drawable.guts8);
        hero[9] = BitmapFactory.decodeResource(getResources(), R.drawable.guts9);
        hero[10] = BitmapFactory.decodeResource(getResources(), R.drawable.guts10);
        hero[11] = BitmapFactory.decodeResource(getResources(), R.drawable.guts11);
        hero[12] = BitmapFactory.decodeResource(getResources(), R.drawable.guts12);
        hero[13] = BitmapFactory.decodeResource(getResources(), R.drawable.guts13);
        hero[14] = BitmapFactory.decodeResource(getResources(), R.drawable.guts14);
        hero[15] = BitmapFactory.decodeResource(getResources(), R.drawable.guts15);
        hero[16] = BitmapFactory.decodeResource(getResources(), R.drawable.guts16);
        hero[17] = BitmapFactory.decodeResource(getResources(), R.drawable.guts17);

        monster[0] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit1);
        monster[1] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit2);
        monster[2] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit3);
        monster[3] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit4);
        monster[4] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit5);
        monster[5] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit6);
        monster[6] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit7);
        monster[7] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit8);
        monster[8] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit9);
        monster[9] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit10);
        monster[10] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit11);
        monster[11] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit12);
        monster[12] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit13);
        monster[13] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit14);
        monster[14] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit15);
        monster[15] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit16);
        monster[16] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit17);
        monster[17] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit18);
        monster[18] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit19);
        monster[19] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit20);
        monster[20] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit21);
        monster[21] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit22);
        monster[22] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit23);
        monster[23] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit24);
        monster[24] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit25);
        monster[25] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit26);
        monster[26] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit27);
        monster[27] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit28);
        monster[28] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit29);
        monster[29] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit30);
        monster[30] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit31);
        monster[31] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit32);
        monster[32] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit33);
        monster[33] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit34);
        monster[34] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit35);
        monster[35] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit36);
        monster[36] = BitmapFactory.decodeResource(getResources(), R.drawable.behelit37);


        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        float height = 1000;
        float width = 1000;
        float ratio = width / height;
        newHeigth = screenHeight;
        newWidth = (int) (ratio * screenHeight);

        backForest = Bitmap.createScaledBitmap(backForest, newWidth, newHeigth-500, false);
        forest = Bitmap.createScaledBitmap(forest, newWidth, newHeigth-500, false);
        grass = Bitmap.createScaledBitmap(grass, newWidth, newHeigth, false);
        back = Bitmap.createScaledBitmap(back, newWidth, newHeigth, false);

        heroX = 30;
        heroY = screenHeight - 1200;
        monsterX = screenWidth + 100;
        monsterY = screenHeight - 1200;

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void drawBackground(Canvas canvas){

        if (!fight){
            backX -= 1;
            backForestX -= 3;
            forestX -= 7;
            grassX -= 13;
        }

        if (backX < -newWidth) {
            backX = 0;
        }
        canvas.drawBitmap(back, backX, 0, null);
        if (backX < screenWidth - newWidth) {
            canvas.drawBitmap(back, backX + newWidth, 0, null);
        }

        if (backForestX < -newWidth) {
            backForestX = 0;
        }
        canvas.drawBitmap(backForest, backForestX, 0, null);
        if (backForestX < screenWidth - newWidth) {
            canvas.drawBitmap(backForest, backForestX + newWidth, 0, null);
        }

        if (forestX < -newWidth) {
            forestX = 0;
        }
        canvas.drawBitmap(forest, forestX, 0, null);
        if (forestX < screenWidth - newWidth) {
            canvas.drawBitmap(forest, forestX + newWidth, 0, null);
        }

        if (grassX < -newWidth) {
            grassX = 0;
        }
        canvas.drawBitmap(grass, grassX, heroY - 700, null);
        if (grassX < screenWidth - newWidth) {
            canvas.drawBitmap(grass, grassX + newWidth, heroY - 700, null);
        }
    }

    public void drawHero(Canvas canvas){

        if (heroX > 30){
            heroX -= 50;
        }

        heroFrame++;
        if (heroFrame == 18) {
            heroFrame = 0;
        }
        canvas.drawBitmap(hero[heroFrame], heroX, heroY, null);
    }

    public void drawMonster(Canvas canvas){
        if (MainActivity.monsterHealthLong > 0) {
            monsterX -= 20;
            if (monsterX < 700) {
                fight = true;
                monsterX = 700;
            }
            monsterFrame++;
            if (monsterFrame == 37) {
                monsterFrame = 0;
            }
        } else {
            fight = false;
            MainActivity.monsterHealth.setText("ЗДОРОВЬЕ МОНСТРА: 0");
//                MainActivity.monsterHealth.setVisibility(View.GONE);
            monsterX = screenWidth + 100;
            monsterY = screenHeight - 1200;

            MainActivity.monsterHealthLong = 300 + (30 * MainActivity.plus_kill);
            MainActivity.monsterHealth.setText("ЗДОРОВЬЕ МОНСТРА: " + MainActivity.monsterHealthLong);
        }
        canvas.drawBitmap(monster[monsterFrame], monsterX, monsterY, null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawBackground(canvas);
        drawHero(canvas);
        drawMonster(canvas);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
}
