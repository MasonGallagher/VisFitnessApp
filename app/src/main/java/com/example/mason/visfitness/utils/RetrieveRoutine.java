package com.example.mason.visfitness.utils;

import android.os.AsyncTask;
import com.example.mason.visfitness.routinesModel;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;

import okhttp3.Response;

/**
 * Created by Mason on 04/12/2019.
 */

public class RetrieveRoutine extends AsyncTask<Void, ArrayList<routinesModel>, ArrayList<routinesModel>> {
    private DataHandlerInterface listener;
    private int variableID;

    public RetrieveRoutine(DataHandlerInterface listener, int variableID){
        this.listener=listener;
        this.variableID=variableID;
    }
    //In background make the sever request and convert the JSON to a list of Java objects
    protected ArrayList<routinesModel> doInBackground(Void... urls) {
        ArrayList<routinesModel> routinesModel = new ArrayList<>();
        try{
            Response response = new MakeRequest().performRequest("get_routines.php");
            JSONArray jsonArray = new JSONArray(response.body().string());
            for(int i=0; i<jsonArray.length();i++){
                //add each element in the JSON array to the object list
                routinesModel.add(new Gson().fromJson(jsonArray.get(i).toString(),routinesModel.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return routinesModel;
    }

    protected void onPostExecute(ArrayList<routinesModel> routinesModel) {
        //use interface to return the response
        listener.DataHandlerInterface(routinesModel);
    }
}
