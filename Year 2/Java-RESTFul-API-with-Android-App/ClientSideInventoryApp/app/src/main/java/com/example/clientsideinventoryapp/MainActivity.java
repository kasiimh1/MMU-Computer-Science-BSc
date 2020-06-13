package com.example.clientsideinventoryapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] vehicleNames;
    ArrayList<Vehicle> allVehicle = new ArrayList();
    Button InsertV;
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //run network on main thread hack
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //send get request to server to populate the app with data
        refresh = findViewById(R.id.refreshList);

        //set a way to refresh the list view
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //fetch the data from the server
                getAll();
                //set the refreshing to false otherwise it will keep displaying it's refreshing and continue to send requests
                refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume(){
        //when the intent returns us back from another activity
        super.onResume();
        //fetch the data from the server
        getAll();
    }

    public void getAll() {
        ListView vehicleList = (ListView) findViewById(R.id.vehicleList);

        //Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null;
        try {
            // the url we wish to connect to with the access token added to the URL
            URL url = new URL("http://10.0.2.2:8005/vehicledb/api?access_token="
                    + "7c78247762fc517f216778a0991062315cb5019eb4b997378d654aa3ab0b85916e357ca7cbc37c96e2f459a39c112c4f15bcc805e723e843e19b89bb53e279a2");
            // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // covert the input stream to a string
        String response = convertStreamToString(in);
        // print the response to android monitor/log cat
        System.out.println("Vehicle response = " + response);

        try {
            // declare a new json array and pass it the string response from the server
            // this will convert the string into a JSON array which we can then iterate
            // over using a loop
            JSONArray jsonArray = new JSONArray(response);
            // instantiate the vehicleName array and set the size
            // to the amount of vehicle object returned by the server
            vehicleNames = new String[jsonArray.length()];

            // use a for loop to iterate over the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                // the following line of code will get the name of the vehicle from the
                // current JSON object and store it in a string variable called name
                int vehicle_id = Integer.parseInt(jsonArray.getJSONObject(i).get("vehicle_id").toString());
                String make = jsonArray.getJSONObject(i).get("make").toString();
                String model = jsonArray.getJSONObject(i).get("model").toString();
                int year = Integer.parseInt(jsonArray.getJSONObject(i).get("year").toString());
                int price = Integer.parseInt(jsonArray.getJSONObject(i).get("price").toString());
                String license_number = jsonArray.getJSONObject(i).get("license_number").toString();
                String colour = jsonArray.getJSONObject(i).get("colour").toString();
                int number_doors = Integer.parseInt(jsonArray.getJSONObject(i).get("number_doors").toString());
                String transmission = jsonArray.getJSONObject(i).get("transmission").toString();
                int mileage = Integer.parseInt(jsonArray.getJSONObject(i).get("mileage").toString());
                String fuel_type = jsonArray.getJSONObject(i).get("fuel_type").toString();
                int engine_size = Integer.parseInt(jsonArray.getJSONObject(i).get("engine_size").toString());
                String body_style = jsonArray.getJSONObject(i).get("body_style").toString();
                String condition = jsonArray.getJSONObject(i).get("condition").toString();
                String notes = jsonArray.getJSONObject(i).get("notes").toString();
                String sold = jsonArray.getJSONObject(i).get("sold").toString();

                Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style,
                        condition, notes, sold);
                allVehicle.add(v);
                // add the name of the current vehicle to the vehicleName array
                vehicleNames[i] = make + " " + model + " (" + year + ")\n" + license_number;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vehicleNames);
        vehicleList.setAdapter(arrayAdapter);

        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "you pressed Vehicle " + allVehicle.get(i).getMake(), Toast.LENGTH_SHORT).show();
                // declare a new intent and give it the context and
                // specify which activity you want to open/start
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                // add/put the selected vehicle object in to the intent which will
                // be passed over to the activity that is started
                // note we use a KEY:VALUE structure to pass variable/objects
                // between activities. Here the key is ‘make’ and the value is
                // the vehicle object from the vehicle array list using the position
                // which is specified by the ‘i’ variable.
                intent.putExtra("make", allVehicle.get(i));
                // launch the activity
                startActivity(intent);
            }
        });

        InsertV = findViewById(R.id.insertVeh);
        InsertV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the button is clicked we navigate to the InsertActivity class
                Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(intent);
            }
        });
    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
