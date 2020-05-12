package com.example.mason.visfitness.Fragments;

import android.widget.Adapter;
import android.widget.EditText;

import com.example.mason.visfitness.Adapters.ExerciseRecyclerAdapter;
import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.utils.SwipeToDeleteCallback;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class CreateEditWorkoutsFragment extends Fragment {

    ArrayList<ExerciseModel> exerciseModelArrayList;


    private boolean set_error(EditText editText){
        editText.setError("Invalid entry");
        return false;
    }
    boolean validate_name(EditText et_routine_name){
        if(et_routine_name.getText().length()<2 || et_routine_name.getText().length()>35){
            return set_error(et_routine_name);
        }else if(et_routine_name.getText().toString().matches("[0-9]+")){
            return set_error(et_routine_name);
        }
        return true;
    }

    boolean validate_fields(EditText name, EditText sets, EditText reps){
        String name_txt = name.getText().toString();
        String sets_txt = sets.getText().toString();
        String reps_txt = reps.getText().toString();
        if(name_txt.length()<2 || name_txt.length()>35){
            return set_error(name);
        }else if(name_txt.matches("[0-9]+")){
            return set_error(name);
        }
        if (!sets_txt.matches("[0-9]+") || sets_txt.length() < 1 || sets_txt.length() > 3) {
            return set_error(sets);
        }
        if (!reps_txt.matches("[0-9]+") || reps_txt.length() < 1 || reps_txt.length() > 3) {
            return set_error(reps);
        }
        return true;
    }

    void enableSwipeToDelete(RecyclerView exercise_recycler, final ExerciseRecyclerAdapter adapter) {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(exercise_recycler);
    }
}
