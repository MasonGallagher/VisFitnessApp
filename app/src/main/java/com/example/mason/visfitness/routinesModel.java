package com.example.mason.visfitness;

import java.util.ArrayList;

/**
 * Created by Mason on 06/12/2019.
 */

public class routinesModel {

    int routineID;
    String routineName;
    ArrayList<exerciseModel> exercises;

    public routinesModel(int routineID, String routineName,ArrayList<exerciseModel> exercises){
        this.routineID = routineID;
        this.routineName = routineName;
        this.exercises = exercises;
    }

    public routinesModel() {

    }

    public int getRoutineID() {
        return routineID;
    }

    public void setRoutineID(int routineID) {
        this.routineID = routineID;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public ArrayList<exerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<exerciseModel> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[routineName=" + routineName + "]";
    }
}
