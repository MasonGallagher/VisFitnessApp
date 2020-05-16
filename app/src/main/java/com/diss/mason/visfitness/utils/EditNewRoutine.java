package com.diss.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

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
            if(exerciseModel.getExerciseID()==0){
                Uri result =context.getContentResolver().insert(MyProviderContract.EXER_URI, newValues);
                Integer exerciseID = Integer.valueOf(result.getLastPathSegment());
                newValues = new ContentValues();
                newValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());
                newValues.put(MyProviderContract.EXERCISE_ID, exerciseID);
                context.getContentResolver().insert(MyProviderContract.WORK_URI, newValues);
            }else {
                context.getContentResolver().update(MyProviderContract.EXER_URI, newValues,
                        MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
            }
        }
    }
}
