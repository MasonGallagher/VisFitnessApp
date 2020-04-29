package com.example.mason.visfitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mason.visfitness.utils.GetSavedRoutines;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class viewWorkoutsFragment extends Fragment {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;

    public viewWorkoutAdapter adapter;
    public ArrayList<RoutinesModel> routinesModelList;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_view_workouts, container, false);
        ButterKnife.bind(this,view);
        routinesModelList = new GetSavedRoutines().getSavedRoutines(getContext());
        if(!routinesModelList.isEmpty()) {
            empty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new viewWorkoutAdapter(getContext(), routinesModelList);
            recyclerView.setAdapter(adapter);
        }else{
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        return view;
    }






}
