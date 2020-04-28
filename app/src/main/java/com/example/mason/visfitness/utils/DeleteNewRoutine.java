package com.example.mason.visfitness.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.RoutinesModel;

public class DeleteNewRoutine {

    public void deleteNewRoutine(Context context,RoutinesModel routinesModel) {
        context.getContentResolver().delete(MyProviderContract.ROUT_URI,
                MyProviderContract.ROUTINE_ID + "=" + routinesModel.getRoutineID(), null);
        context.getContentResolver().delete(MyProviderContract.WORK_URI,
                MyProviderContract.ROUTINE_ID + "=" + routinesModel.getRoutineID(), null);
        for (ExerciseModel exerciseModel : routinesModel.getExercises())
            context.getContentResolver().delete(MyProviderContract.EXER_URI,
                    MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
    }
}
