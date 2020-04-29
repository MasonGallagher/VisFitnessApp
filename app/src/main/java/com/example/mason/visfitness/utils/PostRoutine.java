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

public class PostRoutine extends AsyncTask<Void, ArrayList<RoutinesModel>, ArrayList<RoutinesModel>> {
    private DataHandlerInterface listener;
    private RoutinesModel routinesModel;

    public PostRoutine(DataHandlerInterface listener, RoutinesModel routinesModel){
        this.listener=listener;
        this.routinesModel=routinesModel;
    }


    //In background make the sever request and convert the JSON to a list of Java objects
    protected ArrayList<RoutinesModel> doInBackground(Void... urls) {
        ArrayList<RoutinesModel> routinesModelArrayList = new ArrayList<>();
        try{
            String gson = new Gson().toJson(routinesModel);
            Response response = new MakeRequest().postRequest(gson);
            routinesModelArrayList.add(new RoutinesModel(3,response.body().string(),
                    null));
        }catch (Exception e){
            e.printStackTrace();
        }
        return routinesModelArrayList;
    }

    protected void onPostExecute(ArrayList<RoutinesModel> routinesModel) {
        //use interface to return the response
        listener.DataHandlerInterface(routinesModel);
    }
}