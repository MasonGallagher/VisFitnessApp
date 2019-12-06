package com.example.mason.visfitness.utils;

import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;


import com.example.mason.visfitness.routinesModel;
import com.example.mason.visfitness.workoutsModel;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mason on 04/12/2019.
 */

public class RetrieveRoutine extends AsyncTask<Void, ArrayList<routinesModel>, ArrayList<routinesModel>> {
    private DataHandlerInterface listener;
    int routineID;

    public RetrieveRoutine(DataHandlerInterface listener, int routineID){
        this.listener=listener;
        this.routineID=routineID;
    }

    protected ArrayList<routinesModel> doInBackground(Void... urls) {
        String url = "http://10.0.2.2/diss/db_connect.php";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        ArrayList<routinesModel> routinesModel=null;
        Gson gson = new Gson();
        try{
            final Response response = client.newCall(request).execute();
            routinesModel = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.body().string());
            for(int i=0; i<jsonArray.length();i++){
                routinesModel.add(gson.fromJson(jsonArray.get(i).toString(),routinesModel.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return routinesModel;
    }


    protected void onPostExecute(ArrayList<routinesModel> routinesModel) {
        // TODO: check this.exception
        // TODO: do something with the feed
        listener.DataHandlerInterface(routinesModel);
    }
}
