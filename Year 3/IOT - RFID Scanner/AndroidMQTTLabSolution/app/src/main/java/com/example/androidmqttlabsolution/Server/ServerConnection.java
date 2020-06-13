package com.example.androidmqttlabsolution.Server;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;

public class ServerConnection extends AsyncTask<String, String, String> {
    // this connects to the localhost server allowing it to send door state and post
    // the values.

    String address = "http://10.0.2.2:8080/IoT-Server-Side/SensorServer";
    Gson gson = new Gson();
    String req;

    /* create a sendToServer function to be called via async calls */
    public String sendToServer(String oneSensorJson) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        // Replace invalid URL characters from json string
        try {
            oneSensorJson = URLEncoder.encode(oneSensorJson, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String fullURL = address + "?sensordata=" + oneSensorJson;
        System.out.println("Sending data to: " + fullURL); // DEBUG confirmation message
        String line;
        String result = "";
        try {
            url = new URL(fullURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // Request response from server to enable URL to be opened
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* call the sendToServer function and return the reply to onPostExecute */
    @Override
    protected String doInBackground(String... oneSensorJson) {
        req = sendToServer(oneSensorJson[0]);
        System.out.println("[ASYNC][Server]" + req);
        return req;
    }

    /* on task completion send back the reply from server */
    @Override
    protected void onPostExecute(String oneSensor) {
        super.onPostExecute(req);
    }

}