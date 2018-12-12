package com.wedevelopapps.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    //1. Notification Channel
    //2. Notification Builder
    //3. Notification Manager

    //TODO Notification Channel
    private static final String CHANNEL_ID = "CHATX";
    private static final String CHANNEL_NAME = "CHATX";
    private static final String CHANNEL_DESC = "CHATX Notification";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for Oreo and above version we have to add this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        textView = findViewById(R.id.TextViewToken);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            textView.setText("Token : " + token);
                        } else {
                            textView.setText(task.getException().getMessage());
                        }
                    }
                });

    }

    private void displayNotification() {
        //TODO Notification Builder
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Hurray! Is is working...")
                .setContentText("My first message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //TODO Notification Manager
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, nBuilder.build());
    }
}
