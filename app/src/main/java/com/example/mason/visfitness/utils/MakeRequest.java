package com.example.mason.visfitness.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mason on 06/12/2019.
 */

class MakeRequest {
    /* A class to host a highly reusable method of building and executing a request */
    Response performRequest(String suffix) throws IOException {
        String url = "http://10.0.2.2/diss/"+suffix;
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }
}
