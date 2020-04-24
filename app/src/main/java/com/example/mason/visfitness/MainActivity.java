package com.example.mason.visfitness;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mason.visfitness.utils.viewWorkoutsFragment;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment newFragment = new viewWorkoutsFragment();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
