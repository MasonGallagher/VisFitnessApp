package com.diss.mason.visfitness.utils;

import android.content.Context;

import com.diss.mason.visfitness.Models.ExerciseModel;

/*
    A class to delete an exercise from the devices database
 */
public class DeleteExercise {

    public void deleteNewExercise(Context context,ExerciseModel exerciseModel) {
        context.getContentResolver().delete(MyProviderContract.WORK_URI,
                MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
        context.getContentResolver().delete(MyProviderContract.EXER_URI,
                    MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
    }
}
