package com.example.mason.visfitness.Models;

import java.util.ArrayList;

/**
 * Created by Mason on 06/12/2019.
 */

public class workoutsModel {

    ArrayList<RoutinesModel> workouts;

    public workoutsModel(ArrayList<RoutinesModel> workouts){
        this.workouts = workouts;
    }

    public workoutsModel() {

    }

    public ArrayList<RoutinesModel> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<RoutinesModel> workouts) {
        this.workouts = workouts;
    }
}
