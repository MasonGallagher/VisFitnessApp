package com.diss.mason.visfitness.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.diss.mason.visfitness.Models.ExerciseModel;
import com.diss.mason.visfitness.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/*
    Adapter responsible for populating a recycler view with exercises when creating a routine
 */
public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<ExerciseRecyclerAdapter.ViewHolder>  {

    private final ArrayList<ExerciseModel> exerciseModels;

    public ExerciseRecyclerAdapter(ArrayList<ExerciseModel> exerciseModels) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        final EditText exercise_name;
        final EditText sets;
        final EditText reps;
        ViewHolder(View itemView) {
            super(itemView);
            exercise_name = itemView.findViewById(R.id.et_exercise_name);
            sets = itemView.findViewById(R.id.et_sets);
            reps = itemView.findViewById(R.id.et_reps);
        }
    }

}
