package com.example.mason.visfitness.Fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mason.visfitness.Models.ExerciseModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.Models.RoutinesModel;
import com.example.mason.visfitness.Adapters.viewWorkoutAdapter;
import com.example.mason.visfitness.utils.DBHelper;
import com.example.mason.visfitness.utils.DataHandlerInterface;
import com.example.mason.visfitness.utils.DownloadRoutine;
import com.example.mason.visfitness.utils.RetrieveRoutine;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadWorkoutsFragment extends Fragment implements DataHandlerInterface {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.submit)
    LinearLayout submit;
    ContentResolver cr;
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_download_workouts, container, false);
        ButterKnife.bind(this,view);
        cr = getActivity().getContentResolver();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getRoutine();
            }
        });

        return view;
    }

    private void getRoutine(){
        RetrieveRoutine routine = new RetrieveRoutine(this,et_code.getText().toString().trim());
        routine.execute();
    }

    @Override
    public void DataHandlerInterface(ArrayList<RoutinesModel> routinesModel) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewWorkoutAdapter adapter = new viewWorkoutAdapter(getContext(), routinesModel);
        recyclerView.setAdapter(adapter);
        dbHelper = new DBHelper(getContext(), "mydb", null, 8);
        db= dbHelper.getWritableDatabase();
        if(!routinesModel.isEmpty())
            populateRoutine(routinesModel.get(0));
    }



    private void populateRoutine(RoutinesModel routinesModel){
        String[] share_code= {et_code.getText().toString()};
        Cursor cursor = db.rawQuery("select * from shared where shareCode=?",
                share_code);
        if(cursor.moveToFirst()) {
            do {
                Toast.makeText(getContext(), "Routine already saved!", Toast.LENGTH_SHORT).show();
            } while(cursor.moveToNext());
        }else{
            new DownloadRoutine().downloadRoutine(getContext(), routinesModel);
        }
        cursor.close();
    }

}
