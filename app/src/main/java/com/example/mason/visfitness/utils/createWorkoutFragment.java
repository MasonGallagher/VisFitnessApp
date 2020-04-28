package com.example.mason.visfitness.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.RoutinesModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class createWorkoutFragment extends Fragment{


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.exercises_layout)
    LinearLayout exercises_layout;



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);
        ButterKnife.bind(this,view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View newLayout = getLayoutInflater()
                        .inflate(R.layout.layout_add_exercise, exercises_layout, false);
                exercises_layout.addView(newLayout);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoutinesModel routinesModel = new RoutinesModel();
                ArrayList<ExerciseModel> exerciseModelArrayList = new ArrayList<>();
                routinesModel.setRoutineName(et_routine_name.getText().toString());
                for(int i=0; i<exercises_layout.getChildCount();i++){
                    View v = exercises_layout.getChildAt(i);
                    EditText etName = v.findViewById(R.id.et_exercise_name);
                    EditText etSets = v.findViewById(R.id.et_sets);
                    EditText etReps = v.findViewById(R.id.et_reps);
                    ExerciseModel exerciseModel = new ExerciseModel();
                    exerciseModel.setExerciseName(etName.getText().toString());
                    exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                    exerciseModel.setDefaultReps(Integer.valueOf(etSets.getText().toString()));
                    exerciseModelArrayList.add(exerciseModel);
                }
                routinesModel.setExercises(exerciseModelArrayList);
                System.out.println(routinesModel);
            }
        });
        return view;
    }
}
