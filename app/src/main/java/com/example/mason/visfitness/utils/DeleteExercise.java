package com.example.mason.visfitness.utils;

import android.content.Context;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.RoutinesModel;

public class DeleteExercise {

    public void deleteNewExercise(Context context,ExerciseModel exerciseModel) {
        context.getContentResolver().delete(MyProviderContract.WORK_URI,
                MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
        context.getContentResolver().delete(MyProviderContract.EXER_URI,
                    MyProviderContract.EXERCISE_ID + "=" + exerciseModel.getExerciseID(), null);
    }
}
