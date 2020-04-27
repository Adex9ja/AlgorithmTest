package com.decagon.algorithm.utils;


import com.decagon.algorithm.model.BaseData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    public static BaseData apiCall(String endPoint){
       try {
           URL url = new URL(endPoint);
           HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
           BufferedReader br = new BufferedReader(new InputStreamReader((https.getInputStream())));

           StringBuilder sb = new StringBuilder();
           String output;
           while ((output = br.readLine()) != null) {
               sb.append(output);
           }
           String result = sb.toString();

           return new Gson().fromJson(result, BaseData.class);


       }catch (Exception e){
           return null;
       }
    }
}
