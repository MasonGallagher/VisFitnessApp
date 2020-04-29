package com.example.mason.visfitness;

/**
 * Created by Mason on 06/12/2019.
 */

public class ExerciseModel {

    int exerciseID;
    String exerciseName;
    int defaultSets;
    int defaultReps;

    public ExerciseModel(){

    }

    public ExerciseModel(int exerciseID, String exerciseName, int defaultSets, int defaultReps){
        this.exerciseID = exerciseID;
        this.exerciseName = exerciseName;
        this.defaultSets = defaultSets;
        this.defaultReps = defaultReps;
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
