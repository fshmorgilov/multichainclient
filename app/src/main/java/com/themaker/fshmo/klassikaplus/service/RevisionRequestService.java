package com.themaker.fshmo.klassikaplus.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.themaker.fshmo.klassikaplus.R;

import java.io.IOException;

import static com.themaker.fshmo.klassikaplus.service.NetworkUtils.NotificationTapReceiver.ACTION_TAP;

public class RevisionRequestService extends Worker {

    private static final String TAG = RevisionRequestService.class.getName();

    public static final String WORK_TAG = "Revision_request";
    private final Context context;

    private static final String channelId = "KlassikaplusChannel";
    private static final int notificationId = 123;

    private NotificationManager notificationManager;

    public RevisionRequestService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }

    private void makeNotification() {
        Intent notificationTapIntent = new Intent(context, NetworkUtils.NotificationTapReceiver.class);
        notificationTapIntent.setAction(ACTION_TAP);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationTapIntent, 0);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "abcde")
                .setSmallIcon(R.drawable.logo_main) // TODO: 2/20/2019 set icon
                .setContentTitle(context.getString(R.string.collection_is_updated))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build();
        if (notificationManager == null)
            notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }

    private void logError(Throwable throwable) {
        if (throwable instanceof IOException) {
            Log.e(TAG, "logError: " + throwable.getMessage());
        } else
            Log.e(TAG, "logError: stopped unexpectedly : \n" + throwable.getMessage());
    }
}
