package com.example.mason.visfitness.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
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

public class downloadWorkoutsFragment extends Fragment implements DataHandlerInterface {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    ContentResolver cr;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Handler h = new Handler();

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        cr = getActivity().getContentResolver();
        RetrieveRoutine routine = new RetrieveRoutine(this,1);
        routine.execute();
        return view;
    }

    @Override
    public void DataHandlerInterface(ArrayList<RoutinesModel> routinesModel) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewWorkoutAdapter adapter = new viewWorkoutAdapter(getContext(), routinesModel);
        recyclerView.setAdapter(adapter);
        dbHelper = new DBHelper(getContext(), "mydb", null, 8);
        db= dbHelper.getWritableDatabase();
        //populateRoutine(routinesModel.get(0));


        final String MY_QUERY = "SELECT workouts.routineID, workouts.exerciseID, routines.routineName, exercises.exerciseName, exercises.defaultSets, exercises.defaultReps FROM workouts INNER JOIN exercises ON exercises.exerciseID = workouts.exerciseID  INNER JOIN routines ON routines.routineID = workouts.routineID";
        Cursor cursor = db.rawQuery(MY_QUERY, null);
        RoutinesModel routineModel = new RoutinesModel();
        ExerciseModel exerciseModel;
        ArrayList<RoutinesModel> routinesModelList = new ArrayList<>();
        ArrayList<ExerciseModel> exerciseModelList = new ArrayList<>();
        int routine_id=-1;
        if(cursor.moveToFirst()) {
            do {
                int new_routine_id = cursor.getInt(0);
                int exercise_id = cursor.getInt(1);
                String rout_name = cursor.getString(2);
                String ex_name = cursor.getString(3);
                if(routine_id!=new_routine_id){
                    if(routine_id!=-1)
                        routinesModelList.add(routineModel);
                    routineModel = new RoutinesModel();
                    exerciseModelList=new ArrayList<>();
                    routineModel.setRoutineID(routine_id);
                    routineModel.setRoutineName(rout_name);
                }
                exerciseModel = new ExerciseModel();
                exerciseModel.setExerciseID(exercise_id);
                exerciseModel.setExerciseName(ex_name);
                exerciseModelList.add(exerciseModel);
                routineModel.setExercises(exerciseModelList);
                routine_id = new_routine_id;
            } while(cursor.moveToNext());
            routinesModelList.add(routineModel);
        }
        cursor.close();
    }



    private void populateRoutine(RoutinesModel routinesModel){
        String[] routine_id= {String.valueOf(routinesModel.getRoutineID())};
        Cursor cursor = db.rawQuery("select * from routines where routineID=?",
                routine_id);
        if(cursor.moveToFirst()) {
            do {
                System.out.println("found");
            } while(cursor.moveToNext());
        }else{
            System.out.println("not found");

            ContentValues newValues = new ContentValues();
            newValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());
            newValues.put(MyProviderContract.ROUTINE_NAME, routinesModel.getRoutineName());
            getActivity().getContentResolver().insert(MyProviderContract.ROUT_URI, newValues);
            ArrayList<ExerciseModel> exerciseModelList =routinesModel.getExercises();
            for(ExerciseModel exerciseModel : exerciseModelList) {
                newValues = new ContentValues();
                ContentValues workoutValues = new ContentValues();
                workoutValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
                workoutValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());

                newValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
                newValues.put(MyProviderContract.EXERCISE_NAME, exerciseModel.getExerciseName());
                newValues.put(MyProviderContract.DEFAULT_SETS, exerciseModel.getDefaultSets());
                newValues.put(MyProviderContract.DEFAULT_REPS, exerciseModel.getDefaultReps());
                getActivity().getContentResolver().insert(MyProviderContract.EXER_URI, newValues);
                getActivity().getContentResolver().insert(MyProviderContract.WORK_URI, workoutValues);
            }
        }

        cursor.close();
    }

}
