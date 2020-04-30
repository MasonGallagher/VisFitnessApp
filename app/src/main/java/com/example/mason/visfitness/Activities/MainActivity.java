package com.example.mason.visfitness.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mason.visfitness.R;
import com.example.mason.visfitness.Models.RoutinesModel;
import com.example.mason.visfitness.Fragments.CreateWorkoutFragment;
import com.example.mason.visfitness.Fragments.ViewWorkoutsFragment;
import com.example.mason.visfitness.utils.downloadWorkoutsFragment;

public class MainActivity extends AppCompatActivity{

    private Fragment viewWorkouts;
    private Fragment createWorkouts;
    private Fragment downloadWorkouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewWorkouts = new ViewWorkoutsFragment();
        setFragment(viewWorkouts);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch(item.getItemId()){
                    case R.id.action_view:
                        if (viewWorkouts==null) {
                            setFragment(new ViewWorkoutsFragment());
                        }else{
                            setFragment(viewWorkouts);
                        }
                        break;
                    case R.id.action_create:
                        if (createWorkouts==null) {
                            setFragment(new CreateWorkoutFragment());
                        }else{
                            setFragment(createWorkouts);
                        }
                        break;
                    case R.id.action_download:
                        if (downloadWorkouts==null) {
                            setFragment(new downloadWorkoutsFragment());
                        }else{
                            setFragment(downloadWorkouts);
                        }
                        break;
                }
                return false;
            }
        });

    }

    public void refreshViewFragment(RoutinesModel model){
        ((ViewWorkoutsFragment) viewWorkouts).routinesModelList.remove(model);
        ((ViewWorkoutsFragment) viewWorkouts).adapter.notifyDataSetChanged();
    }

    public void setFragment(Fragment fragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
