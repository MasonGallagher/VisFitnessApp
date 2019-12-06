package com.example.mason.visfitness.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mason.visfitness.R;
import com.example.mason.visfitness.routinesModel;
import com.example.mason.visfitness.viewWorkoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class viewWorkoutsFragment extends Fragment implements DataHandlerInterface {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        RetrieveRoutine routine = new RetrieveRoutine(this,1);
        routine.execute();
        return view;
    }

    @Override
    public void DataHandlerInterface(ArrayList<routinesModel> routinesModel) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewWorkoutAdapter adapter = new viewWorkoutAdapter(getContext(), routinesModel);
        recyclerView.setAdapter(adapter);
    }


}
