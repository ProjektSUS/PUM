package com.example.budzik;

import static android.content.Context.VIBRATOR_SERVICE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

public class MyAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator vibrator_2 = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vibrator_2.vibrate(2000);

        Uri notification_Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(context, notification_Uri);
        ringtone.play();

        Toast.makeText(context, "Alarm został uruchomiony", Toast.LENGTH_SHORT).show();

//        Intent intent_activity = new Intent (context.getApplicationContext(), MathGame.class);
//        intent_activity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent_activity);

    }
}
