package com.diss.mason.visfitness.utils.SeverRequests;

import android.os.AsyncTask;

import com.diss.mason.visfitness.Models.RoutinesModel;
import com.diss.mason.visfitness.utils.DataHandlerInterface;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Mason on 04/12/2019.
 */

public class RetrieveRoutine extends AsyncTask<Void, ArrayList<RoutinesModel>, ArrayList<RoutinesModel>> {
    private final DataHandlerInterface listener;
    private final String variableID;

    public RetrieveRoutine(DataHandlerInterface listener, String variableID){
        this.listener=listener;
        this.variableID=variableID;
    }
    //In background make the sever request and convert the JSON to a list of Java objects
    protected ArrayList<RoutinesModel> doInBackground(Void... urls) {
        ArrayList<RoutinesModel> routinesModelArrayList = new ArrayList<>();
        try{
            String suffix = String.format("get_specific_routine.php?rID=%s",variableID);
            Response response = new MakeRequest().getRequest(suffix);
            JSONArray jsonArray = new JSONArray(response.body().string());
            routinesModelArrayList.add(new Gson().fromJson(jsonArray.get(0).toString(), RoutinesModel.class));
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
