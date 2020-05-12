package com.example.mason.visfitness.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mason.visfitness.R;

import androidx.fragment.app.Fragment;
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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.visfitness.org/wp-content/uploads/2020/05/User-Guide-for-the-Vis-Fitness-App.pdf"));
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
