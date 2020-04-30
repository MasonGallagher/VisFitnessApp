package com.example.mason.visfitness.Models;

import java.util.ArrayList;

/**
 * Created by Mason on 06/12/2019.
 */

public class RoutinesModel {

    int routineID;
    String routineName;
    ArrayList<ExerciseModel> exercises;

    public RoutinesModel(int routineID, String routineName, ArrayList<ExerciseModel> exercises){
        this.routineID = routineID;
        this.routineName = routineName;
        this.exercises = exercises;
    }

    public RoutinesModel() {

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

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseModel> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[routineName=" + routineName +", ID="+routineID+ "]";
    }
}
