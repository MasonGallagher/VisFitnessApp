package com.example.mason.visfitness.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mason.visfitness.ExerciseModel;
import com.example.mason.visfitness.R;
import com.example.mason.visfitness.RoutinesModel;
import com.example.mason.visfitness.viewWorkoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class downloadWorkoutsFragment extends Fragment implements DataHandlerInterface {

    @BindView(R.id.rcView)
    RecyclerView recyclerView;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.submit)
    Button submit;
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
        RetrieveRoutine routine = new RetrieveRoutine(this,et_code.getText().toString());
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
        String[] routine_id= {String.valueOf(routinesModel.getRoutineID())};
        Cursor cursor = db.rawQuery("select * from routines where routineID=?",
                routine_id);
        if(cursor.moveToFirst()) {
            do {
                System.out.println("found");
            } while(cursor.moveToNext());
        }else{
            System.out.println("not found");

            ContentValues newValues = new ContentValues();
            newValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());
            newValues.put(MyProviderContract.ROUTINE_NAME, routinesModel.getRoutineName());
            getActivity().getContentResolver().insert(MyProviderContract.ROUT_URI, newValues);
            ArrayList<ExerciseModel> exerciseModelList =routinesModel.getExercises();
            for(ExerciseModel exerciseModel : exerciseModelList) {
                newValues = new ContentValues();
                ContentValues workoutValues = new ContentValues();
                workoutValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
                workoutValues.put(MyProviderContract.ROUTINE_ID, routinesModel.getRoutineID());

                newValues.put(MyProviderContract.EXERCISE_ID, exerciseModel.getExerciseID());
                newValues.put(MyProviderContract.EXERCISE_NAME, exerciseModel.getExerciseName());
                newValues.put(MyProviderContract.DEFAULT_SETS, exerciseModel.getDefaultSets());
                newValues.put(MyProviderContract.DEFAULT_REPS, exerciseModel.getDefaultReps());
                getActivity().getContentResolver().insert(MyProviderContract.EXER_URI, newValues);
                getActivity().getContentResolver().insert(MyProviderContract.WORK_URI, workoutValues);
            }
        }

        cursor.close();
    }

}
