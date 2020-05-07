package com.example.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;

import java.util.ArrayList;

public class DownloadRoutine {

    public void downloadRoutine(Context context,RoutinesModel routinesModel) {
        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());
        newValues.put(MyProviderContract.ROUTINE_NAME, routinesModel.getRoutineName());
        context.getContentResolver().insert(MyProviderContract.ROUT_URI, newValues);
        ArrayList<ExerciseModel> exerciseModelList = routinesModel.getExercises();
        for (ExerciseModel exerciseModel : exerciseModelList) {
            newValues = new ContentValues();
            ContentValues workoutValues = new ContentValues();
            workoutValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
            workoutValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());

            newValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
            newValues.put(MyProviderContract.EXERCISE_NAME, exerciseModel.getExerciseName());
            newValues.put(MyProviderContract.DEFAULT_SETS, exerciseModel.getDefaultSets());
            newValues.put(MyProviderContract.DEFAULT_REPS, exerciseModel.getDefaultReps());
            context.getContentResolver().insert(MyProviderContract.EXER_URI, newValues);
            context.getContentResolver().insert(MyProviderContract.WORK_URI, workoutValues);

        }
    }
}
