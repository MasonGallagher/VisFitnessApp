package com.diss.mason.visfitness.Activities;


import android.os.Bundle;
import android.view.MenuItem;

import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.Fragments.CreateWorkoutFragment;
import com.diss.mason.visfitness.Fragments.ViewWorkoutsFragment;
import com.diss.mason.visfitness.Fragments.DownloadWorkoutsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/*
    The Vis Fitness apps Main Activity responsible for all functionality
 */
public class MainActivity extends AppCompatActivity {

    private Fragment viewWorkouts;

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
                        setFragment(new CreateWorkoutFragment());
                        break;
                    case R.id.action_download:
                        setFragment(new DownloadWorkoutsFragment());
                        break;
                }
                return false;
            }
        });

    }

    // method for refreshing the workouts displayed upon a deletion
    public void refreshViewFragment(RoutinesModel model){
        ((ViewWorkoutsFragment) viewWorkouts).routinesModelList.remove(model);
        ((ViewWorkoutsFragment) viewWorkouts).adapter.notifyDataSetChanged();
    }

    //method for swapping out fragments in the FrameLayout
    public void setFragment(Fragment fragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
