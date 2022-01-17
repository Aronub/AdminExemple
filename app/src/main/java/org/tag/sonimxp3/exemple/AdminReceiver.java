package org.tag.sonimxp3.exemple;

import android.app.admin.DeviceAdminReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"We are now device Owner",Toast.LENGTH_SHORT);
    }
}