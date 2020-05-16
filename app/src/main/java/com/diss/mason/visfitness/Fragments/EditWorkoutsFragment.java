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
import com.diss.mason.visfitness.utils.DeleteCallback;
import com.diss.mason.visfitness.utils.DeleteExercise;
import com.diss.mason.visfitness.utils.EditNewRoutine;
import com.diss.mason.visfitness.Adapters.ExerciseRecyclerAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
    This fragment uses an old RoutinesModel to build a new RoutinesModel
    then updates it's values in the devices local database
 */
public class EditWorkoutsFragment extends CreateEditWorkoutsFragment {

    @BindView(R.id.button)
    LinearLayout button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    LinearLayout save;
    @BindView(R.id.exercise_recycler)
    RecyclerView exercise_recycler;

    private ExerciseRecyclerAdapter adapter;

    public Fragment makeFragment(RoutinesModel routinesModel){
      this.routinesModel=routinesModel;
      return this;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_edit_workout, container, false);
        ButterKnife.bind(this, view);
        exerciseModelArrayList = routinesModel.getExercises();
        et_routine_name.setText(routinesModel.getRoutineName());
        exercise_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseRecyclerAdapter(routinesModel.getExercises());
        exercise_recycler.setAdapter(adapter);
        deleted_exercises=new ArrayList<>();
        enableSwipe(exercise_recycler,adapter);
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
                ArrayList<ExerciseModel> exerciseModelArrayList = new ArrayList<>();
                RoutinesModel newRoutineModel = new RoutinesModel();
                if(validation) {
                    newRoutineModel.setRoutineID(routinesModel.getRoutineID());
                    newRoutineModel.setRoutineName(et_routine_name.getText().toString());
                    for (int i = 0; i < exercise_recycler.getChildCount(); i++) {
                        View v = exercise_recycler.getChildAt(i);
                        EditText etName = v.findViewById(R.id.et_exercise_name);
                        EditText etSets = v.findViewById(R.id.et_sets);
                        EditText etReps = v.findViewById(R.id.et_reps);
                        validation = validate_fields(etName,etSets,etReps);
                        if(validation) {
                            ExerciseModel exerciseModel = new ExerciseModel();
                            exerciseModel.setExerciseID(routinesModel.getExercises().get(i).getExerciseID());
                            exerciseModel.setExerciseName(etName.getText().toString());
                            exerciseModel.setDefaultSets(Integer.valueOf(etSets.getText().toString()));
                            exerciseModel.setDefaultReps(Integer.valueOf(etReps.getText().toString()));
                            exerciseModelArrayList.add(exerciseModel);
                        }
                    }
                    newRoutineModel.setExercises(exerciseModelArrayList);
                }
                if(validation) {
                    System.out.println(deleted_exercises.size());
                    for (ExerciseModel exerciseModel : deleted_exercises)
                        new DeleteExercise().deleteNewExercise(getContext(), exerciseModel);
                    new EditNewRoutine().EditNewRoutine(getContext(), newRoutineModel);
                    Toast.makeText(getContext(), routinesModel.getRoutineName() +
                            " has been successfully edited!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


}
