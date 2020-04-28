package com.example.mason.visfitness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mason.visfitness.utils.ExerciseRecyclerAdapter;
import com.example.mason.visfitness.utils.SwipeToDeleteCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditWorkoutsFragment extends Fragment {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.et_routine_name)
    EditText et_routine_name;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.exercise_recycler)
    RecyclerView exercise_recycler;

    private RoutinesModel routinesModel;
    private ExerciseRecyclerAdapter adapter;

    public Fragment makeFragment(RoutinesModel routinesModel){
      this.routinesModel=routinesModel;
      return this;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_workout, container, false);
        ButterKnife.bind(this, view);
        et_routine_name.setText(routinesModel.getRoutineName());
        exercise_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ExerciseRecyclerAdapter(getContext(), routinesModel.getExercises());
        exercise_recycler.setAdapter(adapter);
        enableSwipeToDeleteAndUndo();
        return view;
    }

    private void enableSwipeToDeleteAndUndo() {
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
