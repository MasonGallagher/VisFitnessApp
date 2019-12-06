package com.example.mason.visfitness;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mason on 04/12/2019.
 */

public class viewWorkoutAdapter  extends RecyclerView.Adapter<viewWorkoutAdapter.ViewHolder> {

        private ArrayList<routinesModel> routineList;
        private LayoutInflater mInflater;

        public viewWorkoutAdapter(Context context,ArrayList<routinesModel> routines) {
            this.mInflater = LayoutInflater.from(context);
            this.routineList = routines;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.recyclerview_workout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            routinesModel routine = routineList.get(position);
            String text = String.format("routine: %s", routine.getRoutineName());
            for(int i=0; i < routine.getExercises().size();i++){
                exerciseModel exerciseModel = routine.getExercises().get(i);
                text=text+String.format("\n%s sets: %d reps: %d",exerciseModel.getExerciseName(),
                        exerciseModel.getDefaultSets(), exerciseModel.getDefaultReps());
            }
            holder.textView.setText(text);
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return routineList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }

        }


    }
