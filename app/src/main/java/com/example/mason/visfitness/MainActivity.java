package com.example.mason.visfitness;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mason.visfitness.utils.createWorkoutFragment;
import com.example.mason.visfitness.utils.downloadWorkoutsFragment;
import com.example.mason.visfitness.utils.viewWorkoutsFragment;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new viewWorkoutsFragment();
        setFragment(fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_view:
                        setFragment(new viewWorkoutsFragment());
                        break;
                    case R.id.action_create:
                        setFragment(new createWorkoutFragment());
                        break;
                    case R.id.action_download:
                        setFragment(new downloadWorkoutsFragment());
                        break;
                }
                return false;
            }
        });

    }

    private void setFragment(Fragment fragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
