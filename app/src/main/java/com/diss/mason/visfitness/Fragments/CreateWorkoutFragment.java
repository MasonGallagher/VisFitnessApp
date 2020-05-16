package com.diss.mason.visfitness.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.diss.mason.visfitness.Models.ExerciseModel;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.Adapters.ExerciseRecyclerAdapter;
import com.diss.mason.visfitness.utils.DeleteCallback;
import com.diss.mason.visfitness.utils.SaveNewRoutine;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
    This fragment builds a new RoutinesModel and saves it to the devices database
 */

public class CreateWorkoutFragment extends CreateEditWorkoutsFragment {


    @BindView(R.id.button)
    LinearLayout button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    LinearLayout save;
    @BindView(R.id.exercise_recycler)
    RecyclerView exercise_recycler;

    private ExerciseRecyclerAdapter adapter;

    @Override
    public View onCreateView (LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_edit_workout, container, false);
        ButterKnife.bind(this,view);
        exerciseModelArrayList = new ArrayList<>();
        exerciseModelArrayList.add(new ExerciseModel());
        exercise_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseRecyclerAdapter(exerciseModelArrayList);
        exercise_recycler.setAdapter(adapter);
        enableSwipe(exercise_recycler);
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
                    if(etSets.getText().length()!=0) {
                        exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                        exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                    }
                }
                exerciseModelArrayList.add(new ExerciseModel());
                adapter.notifyDataSetChanged();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validation = validate_name(et_routine_name);
                exerciseModelArrayList = new ArrayList<>();
                RoutinesModel routinesModel = new RoutinesModel();
                if(validation) {
                    routinesModel.setRoutineName(et_routine_name.getText().toString());
                    for (int i = 0; i < exercise_recycler.getChildCount(); i++) {
                        View v = exercise_recycler.getChildAt(i);
                        EditText etName = v.findViewById(R.id.et_exercise_name);
                        EditText etSets = v.findViewById(R.id.et_sets);
                        EditText etReps = v.findViewById(R.id.et_reps);
                        validation = validate_fields(etName, etSets, etReps);
                        if (validation) {
                            ExerciseModel exerciseModel = new ExerciseModel();
                            exerciseModel.setExerciseName(etName.getText().toString());
                            exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                            exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                            exerciseModelArrayList.add(exerciseModel);
                        } else {
                            break;
                        }
                    }
                    routinesModel.setExercises(exerciseModelArrayList);
                }
                if(validation) {
                    new SaveNewRoutine().saveNewRoutine(getContext(), routinesModel);
                    Toast.makeText(getContext(), routinesModel.getRoutineName() +
                            getString(R.string.has_been_created), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void enableSwipe(RecyclerView exercise_recycler) {
        DeleteCallback swipeToDeleteCallback = new DeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(exercise_recycler);
    }

}
