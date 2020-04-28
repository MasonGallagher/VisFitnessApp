package com.example.mason.visfitness.utils;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.R;

import java.util.ArrayList;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<ExerciseRecyclerAdapter.ViewHolder>  {

    private ArrayList<ExerciseModel> exerciseModels;
    private Context context;

    public ExerciseRecyclerAdapter(Context context, ArrayList<ExerciseModel> exerciseModels) {
        this.context=context;
        this.exerciseModels = exerciseModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_exercise, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.exercise_name.setText(exerciseModels.get(position).getExerciseName());
        if(exerciseModels.get(position).getDefaultSets()==0) {
            holder.sets.setText("");
            holder.reps.setText("");
        }else {
            holder.sets.setText(String.valueOf(exerciseModels.get(position).getDefaultSets()));
            holder.reps.setText(String.valueOf(exerciseModels.get(position).getDefaultReps()));
        }
    }

    @Override
    public int getItemCount() {
        return exerciseModels.size();
    }


    public void removeItem(int position) {
        exerciseModels.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ExerciseModel item, int position) {
        exerciseModels.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<ExerciseModel> getData() {
        return exerciseModels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText exercise_name;
        EditText sets;
        EditText reps;
        public ViewHolder(View itemView) {
            super(itemView);
            exercise_name = itemView.findViewById(R.id.et_exercise_name);
            sets = itemView.findViewById(R.id.et_sets);
            reps = itemView.findViewById(R.id.et_reps);
        }
    }

}
