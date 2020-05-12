package com.example.mason.visfitness.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mason.visfitness.Activities.MainActivity;
import com.example.mason.visfitness.R;

/**
 * Created by Mason on 04/12/2019.
 */

public class SplashScreenActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Handler handler = new Handler();
        final Intent intent = new Intent(this, MainActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2500);

    }

}
