package com.vishnu.contact_form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    Animation top;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        androidx.core.splashscreen.SplashScreen splashScreen=androidx.core.splashscreen.SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
//        splashScreen.setKeepOnScreenCondition(() -> false );
//        startSomeNextActivity();
//        finish();
        setContentView(R.layout.activity_splash_screen);
        startanimation();
    }

    private void startanimation() {
        image=findViewById(R.id.imageView);
        top= AnimationUtils.loadAnimation(this,R.anim.topapproach);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        image.setAnimation(top);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}