package com.example.mason.visfitness.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.utils.MyPopupClass;

import java.util.ArrayList;

/**
 * Created by Mason on 04/12/2019.
 */

public class viewWorkoutAdapter  extends RecyclerView.Adapter<viewWorkoutAdapter.ViewHolder> {

        private ArrayList<RoutinesModel> routineList;
        private LayoutInflater mInflater;
        private Context context;

        public viewWorkoutAdapter(Context context,ArrayList<RoutinesModel> routines) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
            this.routineList = routines;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.recyclerview_workout_item, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RoutinesModel routine = routineList.get(position);
            String text = String.format("routine: %s", routine.getRoutineName());
            for(int i=0; i < routine.getExercises().size();i++){
                ExerciseModel exerciseModel = routine.getExercises().get(i);
                text=text+String.format("\n%s sets: %d reps: %d",exerciseModel.getExerciseName(),
                        exerciseModel.getDefaultSets(), exerciseModel.getDefaultReps());
            }
            holder.textView.setText(text);
        }

        @Override
        public int getItemCount() {
            return routineList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView editBtn;
            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                editBtn = itemView.findViewById(R.id.edit_btn);
                ViewHolder adapter = this;
                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MyPopupClass popUpClass = new MyPopupClass();
                        popUpClass.showPopupWindow(view,routineList.get(getAdapterPosition()),
                                context);
                    }
                });
            }
        }
}
