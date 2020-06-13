package com.example.androidmqttlabsolution.Mqtt;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.example.androidmqttdemo.R;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber extends Service {

    final String CHANNEL_ID = "hussaink";
    final String BROKER_URL = "tcp://broker.hivemq.com:1883";
    final String userid = "hussaink"; // change this to be your student-id
    final String topic = userid + "/door/access";
    // We have to generate a unique Client id.
    String clientId = userid + "-subb";
    private MqttClient mqttClient;

    public Subscriber() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        onTaskRemoved(intent);
        /* create notification channel to publish notifications on */
        createNotificationChannel();
        /* connect to MQTT */
        try {
            mqttClient = new MqttClient(BROKER_URL, clientId, null);
            mqttClient.connect();
            mqttClient.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    /* craft notification with message when message arrives */
                    createNotification(topic, message.toString());
                    System.out.println("[SUBSCRIBE] Message arrived. Topic: " + topic + "  RoomID: " + message.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Service is running in the background");
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent root) {
        /* if service is stopped or killed this restarts it */
        Intent restartService = new Intent(getApplicationContext(), this.getClass());
        restartService.setPackage(getPackageName());
        System.out.println("Service is Restarting");
        startService(restartService);
        super.onTaskRemoved(restartService);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /* create notification channel to publish notifications on */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MQTT";
            String description = "MQTT ACCESS";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /* create notification with MQTT data that was received */
    private void createNotification(String topic, String message) {
        String title = "[MQTT] Message arrived. Topic: " + topic;
        String text = "Door Unlock attempt made for Door: "+ message;
        NotificationCompat.Builder unauthorizedAccessNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(786, unauthorizedAccessNotification.build());
    }
}