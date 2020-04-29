package com.example.mason.visfitness.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.utils.ExerciseRecyclerAdapter;
import com.example.mason.visfitness.utils.SaveNewRoutine;
import com.example.mason.visfitness.utils.SwipeToDeleteCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateWorkoutFragment extends Fragment{


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.exercise_recycler)
    RecyclerView exercise_recycler;

    private ArrayList<ExerciseModel> exerciseModelArrayList;
    private  ExerciseRecyclerAdapter adapter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);
        ButterKnife.bind(this,view);
        exerciseModelArrayList = new ArrayList<>();
        exerciseModelArrayList.add(new ExerciseModel());
        exercise_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseRecyclerAdapter(getContext(), exerciseModelArrayList);
        exercise_recycler.setAdapter(adapter);
        enableSwipeToDeleteAndUndo();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<exercise_recycler.getChildCount();i++) {
                    View v = exercise_recycler.getChildAt(i);
                    EditText etName = v.findViewById(R.id.et_exercise_name);
                    EditText etSets = v.findViewById(R.id.et_sets);
                    EditText etReps = v.findViewById(R.id.et_reps);
                    ExerciseModel exerciseModel = exerciseModelArrayList.get(i);
                    exerciseModel.setExerciseName(etName.getText().toString());
                    exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                    exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                }
                exerciseModelArrayList.add(new ExerciseModel());
                adapter.notifyDataSetChanged();


            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseModelArrayList = new ArrayList<>();
                RoutinesModel routinesModel = new RoutinesModel();
                routinesModel.setRoutineName(et_routine_name.getText().toString());
                for(int i=0; i<exercise_recycler.getChildCount();i++){
                    View v = exercise_recycler.getChildAt(i);
                    EditText etName = v.findViewById(R.id.et_exercise_name);
                    EditText etSets = v.findViewById(R.id.et_sets);
                    EditText etReps = v.findViewById(R.id.et_reps);
                    ExerciseModel exerciseModel = new ExerciseModel();
                    exerciseModel.setExerciseName(etName.getText().toString());
                    exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                    exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                    exerciseModelArrayList.add(exerciseModel);
                }
                routinesModel.setExercises(exerciseModelArrayList);
                new SaveNewRoutine().saveNewRoutine(getContext(),routinesModel);
                Toast.makeText(getContext(), routinesModel.getRoutineName()+
                        " has been successfully deleted!", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(exercise_recycler);
    }
}
