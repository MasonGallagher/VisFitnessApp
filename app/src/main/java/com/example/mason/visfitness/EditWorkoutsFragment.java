package com.example.mason.visfitness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mason.visfitness.utils.DeleteExercise;
import com.example.mason.visfitness.utils.EditNewRoutine;
import com.example.mason.visfitness.utils.ExerciseRecyclerAdapter;
import com.example.mason.visfitness.utils.SwipeToDeleteCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditWorkoutsFragment extends Fragment {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.exercise_recycler)
    RecyclerView exercise_recycler;

    private RoutinesModel routinesModel;
    private ExerciseRecyclerAdapter adapter;
    private ArrayList<ExerciseModel> deleted_exercises;

    public Fragment makeFragment(RoutinesModel routinesModel){
      this.routinesModel=routinesModel;
      return this;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);
        ButterKnife.bind(this, view);
        et_routine_name.setText(routinesModel.getRoutineName());
        exercise_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseRecyclerAdapter(getContext(), routinesModel.getExercises());
        exercise_recycler.setAdapter(adapter);
        deleted_exercises=new ArrayList<>();
        enableSwipeToDeleteAndUndo();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ExerciseModel> exerciseModelArrayList = new ArrayList<>();
                RoutinesModel newRoutineModel = new RoutinesModel();
                newRoutineModel.setRoutineID(routinesModel.getRoutineID());
                newRoutineModel.setRoutineName(et_routine_name.getText().toString());
                for(int i=0; i<exercise_recycler.getChildCount();i++){
                    View v = exercise_recycler.getChildAt(i);
                    EditText etName = v.findViewById(R.id.et_exercise_name);
                    EditText etSets = v.findViewById(R.id.et_sets);
                    EditText etReps = v.findViewById(R.id.et_reps);
                    ExerciseModel exerciseModel = new ExerciseModel();
                    exerciseModel.setExerciseID(routinesModel.getExercises().get(i).getExerciseID());
                    exerciseModel.setExerciseName(etName.getText().toString());
                    exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                    exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                    exerciseModelArrayList.add(exerciseModel);
                }
                newRoutineModel.setExercises(exerciseModelArrayList);
                for(ExerciseModel exerciseModel: deleted_exercises)
                    new DeleteExercise().deleteNewExercise(getContext(), exerciseModel);
                new EditNewRoutine().EditNewRoutine(getContext(),newRoutineModel);
                Toast.makeText(getContext(), routinesModel.getRoutineName()+
                        " has been successfully edited!", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                deleted_exercises.add(routinesModel.getExercises().get(viewHolder.getAdapterPosition()));
                routinesModel.getExercises().remove(routinesModel.getExercises().get(viewHolder.getAdapterPosition()));
                adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(exercise_recycler);
    }

}
