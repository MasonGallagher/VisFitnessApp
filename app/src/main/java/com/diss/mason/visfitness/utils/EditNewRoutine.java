package com.diss.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;

import com.diss.mason.visfitness.Models.ExerciseModel;
import com.diss.mason.visfitness.Models.RoutinesModel;

/*
    A class to edit routines already saved in the devices database
 */

public class EditNewRoutine {

    public void EditNewRoutine(Context context,RoutinesModel routinesModel) {
        ContentValues newValues = new ContentValues();
        newValues.put(MyProviderContract.ROUTINE_NAME, routinesModel.getRoutineName());
        context.getContentResolver().update(MyProviderContract.ROUT_URI, newValues,
                MyProviderContract.ROUTINE_ID + "=" + routinesModel.getRoutineID(), null);
        for (ExerciseModel exerciseModel : routinesModel.getExercises()) {
            newValues = new ContentValues();
            newValues.put(MyProviderContract.EXERCISE_NAME, exerciseModel.getExerciseName());
            newValues.put(MyProviderContract.DEFAULT_SETS, exerciseModel.getDefaultSets());
            newValues.put(MyProviderContract.DEFAULT_REPS, exerciseModel.getDefaultReps());
            context.getContentResolver().update(MyProviderContract.EXER_URI, newValues,
                    MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
        }
    }
}
