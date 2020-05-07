package com.example.mason.visfitness.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mason.visfitness.Adapters.ExerciseRecyclerAdapter;
import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.Models.RoutinesModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.utils.SaveNewRoutine;
import com.example.mason.visfitness.utils.SwipeToDeleteCallback;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SupportFragment extends Fragment {

    @BindView(R.id.open_web)
    LinearLayout open_web;
    @BindView(R.id.open_pdf)
    LinearLayout open_pdf;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        ButterKnife.bind(this,view);
        open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.visfitness.org/wp-content/uploads/2020/05/AIrev.pdf"));
                startActivity(browserIntent);
            }
        });
        open_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.visfitness.org/user-guide/"));
                startActivity(browserIntent);
            }
        });
        return view;
    }

}
