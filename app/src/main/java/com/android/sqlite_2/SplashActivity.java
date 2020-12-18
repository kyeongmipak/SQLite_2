package com.android.sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    ImageView image1;
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        image1 = findViewById(R.id.img);
        image2 = findViewById(R.id.img2);





//        Animation animation11 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate_alpha);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

//        animation11.setAnimationListener(new Animation.AnimationListener() {
//            Animation animation11 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate_alpha);
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//                trans(-200,1000,0,-800);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                image1.startAnimation(animation11);
//
//            }
//        });
//        image1.startAnimation(animation11);
        image2.startAnimation(animation2);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void trans(float formXDelta,float toXDelta,float fromYDelta, float toYDelta){
        TranslateAnimation translateAnimation = new TranslateAnimation(formXDelta,toXDelta,fromYDelta,toYDelta);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}