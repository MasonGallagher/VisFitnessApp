package com.example.mason.visfitness.utils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mason.visfitness.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class createWorkoutFragment extends Fragment{

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
}
