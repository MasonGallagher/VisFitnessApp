package com.example.mason.visfitness;

import java.util.ArrayList;

/**
 * Created by Mason on 06/12/2019.
 */

public class workoutsModel {

    ArrayList<routinesModel> workouts;

    public workoutsModel(ArrayList<routinesModel> workouts){
        this.workouts = workouts;
    }

    public workoutsModel() {

    }

    public ArrayList<routinesModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<routinesModel> workouts) {
        this.workouts = workouts;
    }
}
