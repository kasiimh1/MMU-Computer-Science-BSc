package com.example.clientsideinventoryapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //run network on main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final EditText vehicle_id = findViewById(R.id.vehicle_id_insert);
        final EditText make = findViewById(R.id.make);
        final EditText model = findViewById(R.id.model);
        final EditText year = findViewById(R.id.year);
        final EditText price = findViewById(R.id.price);
        final EditText license_number = findViewById(R.id.license_number);
        final EditText colour = findViewById(R.id.colour);
        final EditText number_doors = findViewById(R.id.number_doors);
        final EditText transmission = findViewById(R.id.transmission);
        final EditText mileage = findViewById(R.id.mileage);
        final EditText fuel_type = findViewById(R.id.fuel_type);
        final EditText engine_size = findViewById(R.id.engine_size);
        final EditText body_style = findViewById(R.id.body_style);
        final EditText condition = findViewById(R.id.condition);
        final EditText notes = findViewById(R.id.notes);
        final EditText sold = findViewById(R.id.sold);

        Button insertV = findViewById(R.id.insertVehicle);
        final HashMap<String, String> params = new HashMap<>();

        insertV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();

                int veh_id = Integer.parseInt(vehicle_id.getText().toString());
                String veh_make = make.getText().toString();
                String veh_model = model.getText().toString();
                int veh_year = Integer.parseInt(year.getText().toString());
                int veh_price = Integer.parseInt(price.getText().toString());
                String veh_license_number = license_number.getText().toString();
                String veh_colour = colour.getText().toString();
                int veh_number_doors = Integer.parseInt(number_doors.getText().toString());
                String veh_transmission = transmission.getText().toString();
                int veh_mileage = Integer.parseInt(mileage.getText().toString());
                String veh_fuel_type = fuel_type.getText().toString();
                int veh_engine_size = Integer.parseInt(engine_size.getText().toString());
                String veh_body_style = body_style.getText().toString();
                String veh_condition = condition.getText().toString();
                String veh_notes = notes.getText().toString();
                String veh_sold = sold.getText().toString();

                Vehicle f = new Vehicle(veh_id, veh_make, veh_model, veh_year, veh_price,
                        veh_license_number, veh_colour, veh_number_doors, veh_transmission, veh_mileage,
                        veh_fuel_type, veh_engine_size, veh_body_style, veh_condition, veh_notes, veh_sold);

                String vehicleJSON = gson.toJson(f);

                /* print to logcat */
                System.out.println(">>>>>>>>>>" + vehicleJSON);
                System.out.print(">>>>>>>>>>>>" + params);

                /* send the request with the vehicle object and access token */
                params.put("access_token", "7c78247762fc517f216778a0991062315cb5019eb4b997378d654aa3ab0b85916e357ca7cbc37c96e2f459a39c112c4f15bcc805e723e843e19b89bb53e279a2");
                params.put("json", vehicleJSON);
                String url = "http://10.0.2.2:8005/vehicledb/api";
                performPostCall(url, params);
            }
        });
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //write/send/post data to the connection using output stream and buffered writer
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            //clear the writer
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                Toast.makeText(this, "Vehicle Inserted :)", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader((new InputStreamReader(conn.getInputStream())));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                //add redirect intent to main activity on success
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error failed to insert vehicle:(", Toast.LENGTH_LONG).show();
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response =" + response);
        return response;

    }//close post call

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }
}