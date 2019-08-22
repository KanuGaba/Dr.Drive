package com.floom.drdrive;

/**
 * Created by 2017kgaba on 2/2/2016.
 */
import java.util.List;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
public class CheckRunningApplicationReceiver extends BroadcastReceiver implements LocationListener {
    public final String TAG = "CRAR";
    public String speed = "";
    @Override
    public void onReceive(Context aContext, Intent anIntent) {
        try {
            LocationManager lm = (LocationManager) aContext.getSystemService(aContext.LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            this.onLocationChanged(null);
            ActivityManager am = (ActivityManager) aContext
                    .getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningTaskInfo> alltasks = am
                    .getRunningTasks(1);

            for (ActivityManager.RunningTaskInfo aTask : alltasks) {
                /*
                if(!aTask.topActivity.getClassName().equals("com.floom.drdrive.MainActivity")) {
                    Intent lockIntent = new Intent(aContext, LockScreen.class);
                    lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    aContext.startActivity(lockIntent);
                }
                */
                // call
                if (aTask.topActivity.getClassName().equals("com.android.phone.InCallScreen")
                        || aTask.topActivity.getClassName().equals("com.android.contacts.DialtactsActivity"))
                {
                    // phone
                    Toast.makeText(aContext, "Phone Call Screen.", Toast.LENGTH_LONG).show();
                }
                // texting
                if (aTask.topActivity.getClassName().equals("com.android.mms.ui.ConversationList")
                        || aTask.topActivity.getClassName().equals("com.android.mms.ui.ComposeMessageActivity"))
                {
                    // texting notif
                    Toast.makeText(aContext, "Send SMS Screen.", Toast.LENGTH_LONG).show();
                }
                // current
                String packageName = "com.example.checkcurrentrunningapplication";
                if (aTask.topActivity.getClassName().equals(
                        packageName + ".Main"))
                {
                    Toast.makeText(aContext, "Current Example Screen.", Toast.LENGTH_LONG).show();
                }

                Log.i(TAG, "===============================");
                Log.i(TAG, "aTask.baseActivity: "
                        + aTask.baseActivity.flattenToShortString());
                Log.i(TAG, "aTask.baseActivity: "
                        + aTask.baseActivity.getClassName());
                Log.i(TAG, "aTask.topActivity: "
                        + aTask.topActivity.flattenToShortString());
                Log.i(TAG, "aTask.topActivity: "
                        + aTask.topActivity.getClassName());
                Log.i(TAG, "===============================");

                if(Double.parseDouble(speed) >= 1) {
                    if (!aTask.topActivity.getClassName().equals("com.floom.drdrive.MainActivity")) {
                        Log.i(TAG, "CLOSED APP");
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(aContext, notification);
                        r.play();
                    }
                }
            }
        } catch (Throwable t) {
            Log.i(TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location == null) {
            speed = "0";
        }
        else {
            float nCurrentSpeed = location.getSpeed();
           speed = nCurrentSpeed + "";
            Log.i(TAG, "SPEED:" + speed);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
