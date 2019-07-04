package com.demo.data.karyawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar(). hide();

        //set timer
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
