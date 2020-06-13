package com.example.clientsideinventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
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

public class DetailsActivity extends AppCompatActivity {

    Button removeVeh, updateVehicle;
    int vehicleID_remove;
    Vehicle veh;
    HashMap<String, String> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // get the intent
        Bundle extras = getIntent().getExtras();
        // create a cheese object from the cheese object that was passed over from
        // the MainActivity. Notice you use the key ('cheese') to retrieve the value/variable needed.
        veh = (Vehicle) extras.get("make");
        System.out.println("received from the intent: " + veh.getMake());

        TextView heading = findViewById(R.id.vehicle_heading);

        heading.setText(veh.getMake() + " " + veh.getModel());

        /* set each edit text to the respected values, doing this allows showing and updating of the vehicle within the same activity */
        final EditText vehicle_id = findViewById(R.id.vehicle_id_update);
        final EditText make = findViewById(R.id.make_update);
        final EditText model = findViewById(R.id.model_update);
        final EditText year = findViewById(R.id.year_update);
        final EditText price = findViewById(R.id.price_update);
        final EditText license_number = findViewById(R.id.license_number_update);
        final EditText colour = findViewById(R.id.colour_update);
        final EditText number_doors = findViewById(R.id.number_doors_update);
        final EditText transmission = findViewById(R.id.transmission_update);
        final EditText mileage = findViewById(R.id.mileage_update);
        final EditText fuel_type = findViewById(R.id.fuel_type_update);
        final EditText engine_size = findViewById(R.id.engine_size_update);
        final EditText body_style = findViewById(R.id.body_style_update);
        final EditText condition = findViewById(R.id.condition_update);
        final EditText notes = findViewById(R.id.notes_update);
        final EditText sold = findViewById(R.id.sold_update);

        vehicle_id.setText("" + veh.getVehicle_id());
        make.setText(veh.getMake());
        model.setText(veh.getModel());
        year.setText(Integer.toString(veh.getYear()));
        price.setText(Integer.toString(veh.getPrice()));
        license_number.setText(veh.getLicense_number());
        colour.setText(veh.getColour());
        number_doors.setText(Integer.toString(veh.getNumber_doors()));
        transmission.setText(veh.getTransmission());
        mileage.setText(Integer.toString(veh.getMileage()));
        fuel_type.setText(veh.getFuel_type());
        engine_size.setText(Integer.toString(veh.getEngine_size()));
        body_style.setText(veh.getBody_style());
        condition.setText(veh.getCondition());
        notes.setText(veh.getNotes());
        sold.setText(veh.getSold());

        updateVehicle = findViewById(R.id.updateVehicle);
        updateVehicle.setOnClickListener(new View.OnClickListener() {
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

                params.put("access_token", "7c78247762fc517f216778a0991062315cb5019eb4b997378d654aa3ab0b85916e357ca7cbc37c96e2f459a39c112c4f15bcc805e723e843e19b89bb53e279a2");
                params.put("json", vehicleJSON);
                String url = "http://10.0.2.2:8005/vehicledb/api";
                performPutCall(url, params);
            }
        });

        removeVeh = findViewById(R.id.removeVehicle);
        removeVeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleID_remove = veh.getVehicle_id();
                System.out.println(">>>>>>>>>>>User wants to remove vehicle " + veh.getVehicle_id());

                String url = "http://10.0.2.2:8005/vehicledb/api?access_token=7c78247762fc517f216778a0991062315cb5019eb4b997378d654aa3ab0b85916e357ca7cbc37c96e2f459a39c112c4f15bcc805e723e843e19b89bb53e279a2"
                        + "&vehicle_id=" + Integer.valueOf(veh.getVehicle_id()).toString();
                performDeleteCall(url, params);
                System.out.println("DELETE Req URL + params is = " + url);
            }

            public String performDeleteCall(String requestURL, HashMap<String, String> postDataParams) {
                URL url;
                String response = "";
                try {
                    url = new URL(requestURL);

                    //create the connection object
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("DELETE");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    //write/send/post data to the connection using output stream and buffered writer
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    //
                    writer.write(getDataString(postDataParams));

                    //clear the writer
                    writer.flush();
                    writer.close();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    System.out.println("responseCode = " + responseCode);

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        Toast.makeText(getApplicationContext(),"Vehicle Removed :)",Toast.LENGTH_LONG).show();
                        String line;
                        BufferedReader br = new BufferedReader((new InputStreamReader(conn.getInputStream())));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                        //add redirect intent to main activity on success
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error failed to Remove Vehicle:(",Toast.LENGTH_LONG).show(); response="";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("response =" + response);
                return response;
            }//close post call
        });
    }

    public String performPutCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //write/send/post data to the connection using output stream and buffered writer
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getDataString(postDataParams));
            //clear the writer
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                Toast.makeText(this, "Vehicle Updated :)", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader((new InputStreamReader(conn.getInputStream())));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error failed to update vehicle:(", Toast.LENGTH_LONG).show();
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response =" + response);
        return response;
    }//close post call

    private String getDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
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