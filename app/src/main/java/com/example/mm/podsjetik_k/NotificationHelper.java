package com.example.mm.podsjetik_k;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {

    public static final String channel1ID = "channel1ID";
    public static final String channel1Ime = "channel1Ime";
    public static final String channel1IDL = "channel1IDL";
    public static final String channel1ImeL = "channel1ImeL";
    public static final String channel1IDK = "channel1IDK";
    public static final String channel1ImeK = "channel1ImeK";


    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChanels();
        }

    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChanels() {
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Ime, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);

        NotificationChannel channel1L = new NotificationChannel(channel1IDL, channel1ImeL, NotificationManager.IMPORTANCE_DEFAULT);
        channel1L.enableLights(true);
        channel1L.enableVibration(true);
        channel1L.setLightColor(R.color.colorPrimary);
        channel1L.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1L);

        NotificationChannel channel1K = new NotificationChannel(channel1IDK, channel1ImeK, NotificationManager.IMPORTANCE_DEFAULT);
        channel1K.enableLights(true);
        channel1K.enableVibration(true);
        channel1K.setLightColor(R.color.colorPrimary);
        channel1K.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1K);


    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message) {
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.a);
    }

    public NotificationCompat.Builder getChannel2Notification(String naslovL, String porukaL) {
        return new NotificationCompat.Builder(getApplicationContext(), channel1IDL)
                .setContentTitle(naslovL)
                .setContentText(porukaL)
                .setSmallIcon(R.drawable.b);

    }

    public NotificationCompat.Builder getChannel3Notification(String naslovK, String porukaK) {
        return new NotificationCompat.Builder(getApplicationContext(), channel1IDK)
                .setContentTitle(naslovK)
                .setContentText(porukaK)
                .setSmallIcon(R.drawable.shop);

    }
}
