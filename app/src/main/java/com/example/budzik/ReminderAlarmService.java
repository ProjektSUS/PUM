package com.example.budzik;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ReminderAlarmService {
    public static PendingIntent getReminderPendingIntent(Context context, Uri uri) {
        Intent action = new Intent(context, ReminderAlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
