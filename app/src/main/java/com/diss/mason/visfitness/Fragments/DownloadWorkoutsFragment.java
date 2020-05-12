package com.diss.mason.visfitness.Fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.diss.mason.visfitness.R;
import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.Adapters.viewWorkoutAdapter;
import com.diss.mason.visfitness.utils.DBHelper;
import com.diss.mason.visfitness.utils.DataHandlerInterface;
import com.diss.mason.visfitness.utils.DownloadRoutine;
import com.diss.mason.visfitness.utils.SeverRequests.RetrieveRoutine;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*
    This fragment allows users to download workouts
 */
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
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                if(et_code.getText().length()>0)
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
            Toast.makeText(getContext(), routinesModel.getRoutineName()+" has been saved!",
                    Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

}
