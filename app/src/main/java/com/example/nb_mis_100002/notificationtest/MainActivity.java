package com.example.nb_mis_100002.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button sendNotice;

    private void assignViews() {
        sendNotice = (Button) findViewById(R.id.send_notice);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification(R.mipmap.ic_launcher, "这是一个通知栏", System.currentTimeMillis());

                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                //点击通知时发生动作
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                notification.setLatestEventInfo(MainActivity.this, "通知", "这是一个通知", pi);

                //在通知发出的时候播放一段音频
                Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
                notification.sound = soundUri;
                //震动
                long[] vibrates = {0, 1000, 1000, 1000};
                notification.vibrate = vibrates;
                //实现 LED 灯以绿色的灯光一闪一闪的效果
                notification.ledARGB = Color.GREEN;
                notification.ledOnMS = 1000;
                notification.ledOffMS = 1000;
                notification.flags = Notification.FLAG_SHOW_LIGHTS;

                notificationManager.notify(1, notification);
            }
        });
    }

}
