package ru.guzlik.berserkclicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

public class RunningManAnimation extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    class GameView extends SurfaceView implements Runnable {

        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private volatile boolean playing;
        private Canvas canvas;
        private Bitmap bitmapRunningMan, bitmapStandingMan;
        private boolean isMoving = true;
        private float runSpeedPerSecond = 200;
        private float manXPos = 10, manYPos = 800;
        private int frameWidth = 460, frameHeight = 548;
        private int frameCount = 18;
        private int currentFrame = 0;
        private long fps;
        private long timeThisFrame;
        private long lastFrameChangeTime = 0;
        private int frameLengthInMillisecond = 80;

        private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);

        private RectF whereToDraw = new RectF(manXPos, manYPos, manXPos + frameWidth, frameHeight);

        public GameView(Context context) {
            super(context);
            ourHolder = getHolder();

//            bitmapStandingMan = BitmapFactory.decodeResource(getResources(), R.drawable.standing_man);
//            bitmapStandingMan = Bitmap.createScaledBitmap(bitmapStandingMan, frameWidth * frameCount, frameHeight, false);

            bitmapRunningMan = BitmapFactory.decodeResource(getResources(), R.drawable.guts_flex);
            bitmapRunningMan = Bitmap.createScaledBitmap(bitmapRunningMan, frameWidth * frameCount, frameHeight, false);
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();
                update();
                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;

                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        public void update() {
            if (isMoving) {

//                if (manXPos >= 400) {
//                    manXPos = 400;
//                } else {
                    manXPos = manXPos + runSpeedPerSecond / fps;

                    if (manXPos > getWidth() - 300) {
//                    manYPos += (int) frameHeight;
                        manXPos = 10;
                    }

//                    if (manYPos + frameHeight > getHeight()) {
//                        manYPos = 400;
//                    }
//                }
            }
        }

        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (isMoving) {
                if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                    lastFrameChangeTime = time;
                    currentFrame++;

                    if (currentFrame >= frameCount) {
                        currentFrame = 0;
                    }
                }
            }

            frameToDraw.left = currentFrame * frameWidth;
            frameToDraw.right = frameToDraw.left + frameWidth;
        }

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                whereToDraw.set((int) manXPos, (int) manYPos, (int) manXPos + frameWidth, (int) manYPos + frameHeight);
                manageCurrentFrame();
//                if (manXPos >= 400){
//                    canvas.drawBitmap(bitmapStandingMan, frameToDraw, whereToDraw, null);
//                } else {
                canvas.drawBitmap(bitmapRunningMan, frameToDraw, whereToDraw, null);
//                }
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch(InterruptedException e) {
                Log.e("ERR", "Joining Thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN :
                    manXPos = 10;
                    break;
            }

            return true;
        }
    }
}