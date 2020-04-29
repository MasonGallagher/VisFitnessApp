package com.example.mason.visfitness.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;

import java.util.ArrayList;

public class GetSavedRoutines {

    public ArrayList<RoutinesModel> getSavedRoutines(Context context){
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
        DBHelper dbHelper = new DBHelper(context, "mydb", null, 8);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(MY_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                int new_routine_id = cursor.getInt(0);
                int exercise_id = cursor.getInt(1);
                String rout_name = cursor.getString(2);
                String ex_name = cursor.getString(3);
                int sets = cursor.getInt(4);
                int reps = cursor.getInt(5);
                if(routine_id!=new_routine_id){
                    if(routine_id!=-1)
                        routinesModelList.add(routinesModel);
                    routinesModel = new RoutinesModel();
                    exerciseModelList=new ArrayList<>();
                    routinesModel.setRoutineID(new_routine_id);
                    routinesModel.setRoutineName(rout_name);
                }
                exerciseModel.setExerciseID(exercise_id);
                exerciseModel.setExerciseName(ex_name);
                exerciseModel.setDefaultSets(sets);
                exerciseModel.setDefaultReps(reps);
                exerciseModelList.add(exerciseModel);
                routinesModel.setExercises(exerciseModelList);
                exerciseModel = new ExerciseModel();
                routine_id = new_routine_id;
            } while(cursor.moveToNext());
            routinesModelList.add(routinesModel);
        }
        cursor.close();
        return routinesModelList;
    }
}
