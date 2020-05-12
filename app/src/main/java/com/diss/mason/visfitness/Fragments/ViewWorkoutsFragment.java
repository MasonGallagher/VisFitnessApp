package com.diss.mason.visfitness.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diss.mason.visfitness.Activities.MainActivity;
import com.diss.mason.visfitness.Adapters.viewWorkoutAdapter;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.utils.GetSavedRoutines;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewWorkoutsFragment extends Fragment {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.help)
    ImageView help;

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
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).setFragment(new SupportFragment());
                }
            });
        }
        return view;
    }






}
