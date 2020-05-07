package com.example.mason.visfitness.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mason on 06/12/2019.
 */

class MakeRequest {
    /* A class to host a highly reusable method of building and executing a request */
    Response performRequest(String suffix) throws IOException {
        String url = "http://visfitness.org"+suffix;
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }
    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /* A class to host a highly reusable method of building and executing a request */
    Response postRequest(String postbody) throws IOException {
        String url = "http://visfitness.org/post_routine.php";
        RequestBody body = RequestBody.create(JSON,postbody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }
}
