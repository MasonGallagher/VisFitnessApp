package com.example.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;

public class SaveNewRoutine {

    public void saveNewRoutine(Context context,RoutinesModel routinesModel){
        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.ROUTINE_NAME, routinesModel.getRoutineName());
        Uri result = context.getContentResolver().insert(MyProviderContract.ROUT_URI, newValues);
        Integer routineID = Integer.valueOf(result.getLastPathSegment());
        for(ExerciseModel exerciseModel : routinesModel.getExercises()){
            newValues = new ContentValues();
            newValues.put(MyProviderContract.EXERCISE_NAME, exerciseModel.getExerciseName());
            newValues.put(MyProviderContract.DEFAULT_SETS, exerciseModel.getDefaultSets());
            newValues.put(MyProviderContract.DEFAULT_REPS, exerciseModel.getDefaultReps());
            result = context.getContentResolver().insert(MyProviderContract.EXER_URI, newValues);
            Integer exerciseID = Integer.valueOf(result.getLastPathSegment());
            newValues = new ContentValues();
            newValues.put(MyProviderContract.ROUTINE_ID, routineID);
            newValues.put(MyProviderContract.EXERCISE_ID, exerciseID);
            context.getContentResolver().insert(MyProviderContract.WORK_URI, newValues);
        }

    }
}
