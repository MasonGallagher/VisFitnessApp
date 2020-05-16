package com.diss.mason.visfitness.Models;

/**
 * Created by Mason on 06/12/2019.
 */

public class ExerciseModel {

    private int exerciseID;
    private String exerciseName;
    private int defaultSets;
    private int defaultReps;

    public ExerciseModel(){

    }

    public int getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getDefaultSets() {
        return defaultSets;
    }

    public void setDefaultSets(int defaultSets) {
        this.defaultSets = defaultSets;
    }

    public int getDefaultReps() {
        return defaultReps;
    }

    public void setDefaultReps(int defaultReps) {
        this.defaultReps = defaultReps;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[exerciseName=" + exerciseName + ", ID=" + exerciseID + "]";
    }
}
