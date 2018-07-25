package com.elabs.android.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.logging.Handler;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM WILL RING NOW", Toast.LENGTH_LONG).show();
        Uri notification =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, notification);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }
}
