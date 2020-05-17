package com.diss.mason.visfitness.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.diss.mason.visfitness.R;

/**
 * Created by Mason on 04/12/2019.
 */

/*
    Activity responsible for the splash screen
 */

public class SplashScreenActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Handler handler = new Handler();
        final Intent intent = new Intent(this, MainActivity.class);
        //delay the opening of the next activity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2500);

    }

}
