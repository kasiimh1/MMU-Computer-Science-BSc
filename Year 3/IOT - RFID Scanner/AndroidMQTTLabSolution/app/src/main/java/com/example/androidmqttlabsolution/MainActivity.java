package com.example.androidmqttlabsolution;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidmqttdemo.R;
import com.example.androidmqttlabsolution.Mqtt.Publisher;
import com.example.androidmqttlabsolution.Mqtt.Subscriber;
import com.example.androidmqttlabsolution.Server.SensorData;
import com.example.androidmqttlabsolution.Server.ServerConnection;
import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    static final int PERMISSION_READ_STATE = 123;
    /* same broker as client side project */
    Button publishDoorUnlockBtn;
    Publisher mqttconn;
    Gson gson = new Gson();
    String resp;
    String oneSensorJson = new String();
    Subscriber sub = new Subscriber();
    SensorData sensor = new SensorData(35824005, "", "unknown", "unknown", "unknown");
    SensorData reply;
    String strDeviceType = "";
    int deviceID = 0;

    {
        try {
            mqttconn = new Publisher();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* start the subscriber as a service and start listening */
        startService(new Intent(getApplicationContext(), Subscriber.class));
        setContentView(R.layout.activity_main);
        publishDoorUnlockBtn = (Button) findViewById(R.id.publishDoorUnlockBtn);
        publishDoorUnlockBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                /*

                    Commented out this function so it works on all emulators as i do not know their IMEI's to add to my database
                    for production remove commented lines on 70,135,134,192

                 */

                /* get IMEI to uses as the readervalue */
                //  getIMEI();


                /* hard code this as all mobile devices need a specific value for authentication */
                String tagValue = "isMobileDevice";
                System.out.println("[DEBUG] Tag value = " + tagValue);
                String valid = "";
                String roomid = "";
                String motorid = "";


                /* debug device serial same as readervalue in clientside*/
                System.out.println("Device Serial is = " + deviceID);
                /* setup object values */
                sensor.setTagvalue(tagValue);
                //  sensor.setReaderserial(deviceID);
                /* convert to object */
                oneSensorJson = gson.toJson(sensor);
                /* send req to server using Asynchronous calls*/
                try {
                    ServerConnection serverconn = new ServerConnection();
                    resp = serverconn.execute(oneSensorJson).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[ASYNC] REPLY FROM SERVER " + resp);
                /* convert received reply from server */
                reply = gson.fromJson(resp, SensorData.class);
                System.out.println("REPLY FROM SERVER " + reply);

                /*get resp values from server reply */
                roomid = reply.getRoomid();
                valid = reply.getValid();
    //            motorid = reply.getMotorid();

                if (valid.equalsIgnoreCase("true")) {
                    // display red light because access is not granted!
                    try {
                        System.out.println("[APP] Authorization granted");
                        /* display feedback to user */
                        Toast.makeText(MainActivity.this, "Authentication Granted", Toast.LENGTH_SHORT).show();
                        /* publish the unlock req message over mqtt */
                        mqttconn.publisher(roomid, resp);
                        /* publish access req message over mqtt */
                        mqttconn.publisher("access", resp);
                    } catch (MqttException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    /* display feedback to user */
                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    /* publish access req message over mqtt */
                    try {
                        mqttconn.publisher("access", resp);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[APP] Authorization failed");
                }
            }
        });
    }
/*
    // grab a unique value from the device to auth against db
    public void getIMEI() {
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permCheck == PackageManager.PERMISSION_GRANTED) {
            MyTelephonyManager();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyTelephonyManager();
                } else {
                    System.out.println("You don't have permission to do this action");
                }
            }
        }
    }

     //get unique device IMEI for checking against the DB

    private void MyTelephonyManager() {
        TelephonyManager man = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int deviceType = man.getPhoneType();
        switch (deviceType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strDeviceType = "CDMA";
                break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strDeviceType = "GSM";
                break;

            case (TelephonyManager.PHONE_TYPE_NONE):
                strDeviceType = "NONE";
                break;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String temp;
        // IMEI is too long to store as int, take the first 8 digits and store
        temp = String.valueOf(man.getImei()).substring(0, 8);
        deviceID = Integer.valueOf(temp);
    }*/
}