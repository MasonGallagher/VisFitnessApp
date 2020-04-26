package com.example.mason.visfitness.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.RoutinesModel;
import com.example.mason.visfitness.viewWorkoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class viewWorkoutsFragment extends Fragment {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_view_workouts, container, false);
        ButterKnife.bind(this,view);
        populateDatabase();
        return view;
    }

    private void populateDatabase(){

        ArrayList<RoutinesModel> routinesModelList = new ArrayList<>();
        ArrayList<ExerciseModel> exerciseModelList = new ArrayList<>();
        RoutinesModel routinesModel = new RoutinesModel();
        ExerciseModel exerciseModel = new ExerciseModel();
        int routine_id=-1;

        /* query the database for all routines and their associated workouts */
        final String MY_QUERY = "SELECT workouts.routineID, workouts.exerciseID, " +
                "routines.routineName, exercises.exerciseName, exercises.defaultSets," +
                " exercises.defaultReps FROM workouts INNER JOIN exercises ON " +
                "exercises.exerciseID = workouts.exerciseID  INNER JOIN routines ON " +
                "routines.routineID = workouts.routineID";
        dbHelper = new DBHelper(getContext(), "mydb", null, 8);
        db= dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(MY_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                int new_routine_id = cursor.getInt(0);
                int exercise_id = cursor.getInt(1);
                String rout_name = cursor.getString(2);
                String ex_name = cursor.getString(3);
                if(routine_id!=new_routine_id){
                    if(routine_id!=-1)
                        routinesModelList.add(routinesModel);
                    routinesModel = new RoutinesModel();
                    exerciseModelList=new ArrayList<>();
                    routinesModel.setRoutineID(routine_id);
                    routinesModel.setRoutineName(rout_name);
                }
                exerciseModel.setExerciseID(exercise_id);
                exerciseModel.setExerciseName(ex_name);
                exerciseModelList.add(exerciseModel);
                routinesModel.setExercises(exerciseModelList);
                exerciseModel = new ExerciseModel();
                routine_id = new_routine_id;
            } while(cursor.moveToNext());
            routinesModelList.add(routinesModel);
        }
        cursor.close();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewWorkoutAdapter adapter = new viewWorkoutAdapter(getContext(), routinesModelList);
        recyclerView.setAdapter(adapter);
    }




}
