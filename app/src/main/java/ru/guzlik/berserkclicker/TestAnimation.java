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
    int screenHeight, screenWidth, newHeigth, newWidth;
    int forestX = 0, backForestX = 0, grassX = 0, backX = 0;
    int heroX, heroY, heroFrame = 0;
    Bitmap forest, backForest, grass, back;
    Bitmap hero[] = new Bitmap[18];
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

        heroX = screenWidth / 2 - 200;
        heroY = screenHeight - 1200;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        backX -= 1;
        if (backX < -newWidth) {
            backX = 0;
        }
        canvas.drawBitmap(back, backX, 0, null);
        if (backX < screenWidth - newWidth){
            canvas.drawBitmap(back, backX+newWidth, 0, null);
        }

        backForestX -= 3;
        if (backForestX < -newWidth) {
            backForestX = 0;
        }
        canvas.drawBitmap(backForest, backForestX, 0, null);
        if (backForestX < screenWidth - newWidth){
            canvas.drawBitmap(backForest, backForestX+newWidth, 0, null);
        }

        forestX -= 7;
        if (forestX < -newWidth) {
            forestX = 0;
        }
        canvas.drawBitmap(forest, forestX, 0, null);
        if (forestX < screenWidth - newWidth){
            canvas.drawBitmap(forest, forestX+newWidth, 0, null);
        }

        grassX -= 13;
        if (grassX < -newWidth) {
            grassX = 0;
        }
        canvas.drawBitmap(grass, grassX, heroY-700, null);
        if (grassX < screenWidth - newWidth){
            canvas.drawBitmap(grass, grassX+newWidth, heroY-700, null);
        }

        heroFrame++;
        if (heroFrame == 18){
            heroFrame = 0;
        }
        canvas.drawBitmap(hero[heroFrame], heroX, heroY, null);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
}
