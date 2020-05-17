package com.diss.mason.visfitness.utils;

import android.content.Context;

import com.diss.mason.visfitness.Models.ExerciseModel;
import com.diss.mason.visfitness.Models.RoutinesModel;

/*
    A class to icon_delete a routine from the devices database
 */
class DeleteNewRoutine {

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
