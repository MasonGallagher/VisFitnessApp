package com.example.mason.visfitness.utils;

import android.os.AsyncTask;

import com.example.mason.visfitness.RoutinesModel;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Mason on 04/12/2019.
 */

public class RetrieveRoutine extends AsyncTask<Void, ArrayList<RoutinesModel>, ArrayList<RoutinesModel>> {
    private DataHandlerInterface listener;
    private int variableID;

    public RetrieveRoutine(DataHandlerInterface listener, int variableID){
        this.listener=listener;
        this.variableID=variableID;
    }
    //In background make the sever request and convert the JSON to a list of Java objects
    protected ArrayList<RoutinesModel> doInBackground(Void... urls) {
        ArrayList<RoutinesModel> routinesModel = new ArrayList<>();
        try{
            String suffix = String.format("get_specific_routine.php?rID=%d",variableID);
            Response response = new MakeRequest().performRequest(suffix);
            JSONArray jsonArray = new JSONArray(response.body().string());
            routinesModel.add(new Gson().fromJson(jsonArray.get(0).toString(), RoutinesModel.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return routinesModel;
    }

    protected void onPostExecute(ArrayList<RoutinesModel> routinesModel) {
        //use interface to return the response
        listener.DataHandlerInterface(routinesModel);
    }
}
