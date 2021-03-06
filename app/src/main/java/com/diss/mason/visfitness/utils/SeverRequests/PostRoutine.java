package com.diss.mason.visfitness.utils.SeverRequests;

import android.os.AsyncTask;

import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.utils.DataHandlerInterface;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Mason on 04/12/2019.
 */

/*
       A class to turn a RoutinesModel to JSON to be posted to the server
 */

public class PostRoutine extends AsyncTask<Void, ArrayList<RoutinesModel>, ArrayList<RoutinesModel>> {
    private final DataHandlerInterface listener;
    private final RoutinesModel routinesModel;

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
