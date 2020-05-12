package com.diss.mason.visfitness.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diss.mason.visfitness.Models.ExerciseModel;
import com.diss.mason.visfitness.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mason on 04/12/2019.
 */

/*
    This adapter that populates exercises within a workouts card
 */
public class WorkoutAdapterExerciseAdapter extends RecyclerView.Adapter<WorkoutAdapterExerciseAdapter.ViewHolder> {

        private ArrayList<ExerciseModel> exercises;
        private LayoutInflater mInflater;

        public WorkoutAdapterExerciseAdapter(Context context, ArrayList<ExerciseModel> exercises) {
            this.mInflater = LayoutInflater.from(context);
            this.exercises = exercises;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.layout_exercise, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ExerciseModel exercise = exercises.get(position);
            holder.exercise_name.setText(exercise.getExerciseName());
            holder.sets.setText(String.format("Sets: %d",exercise.getDefaultSets()));
            holder.reps.setText(String.format("Reps: %d",exercise.getDefaultReps()));
        }

        @Override
        public int getItemCount() {
            return exercises.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView exercise_name;
            TextView sets;
            TextView reps;
            ViewHolder(View itemView) {
                super(itemView);
                exercise_name = itemView.findViewById(R.id.exercise_name);
                sets = itemView.findViewById(R.id.sets);
                reps = itemView.findViewById(R.id.reps);
            }
        }
}
