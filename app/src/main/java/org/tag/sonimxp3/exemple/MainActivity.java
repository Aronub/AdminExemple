package org.tag.sonimxp3.exemple;

import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Parcel;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean isBrowserDisabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        ComponentName cn = new ComponentName(this, AdminReceiver.class);

        try {
            isBrowserDisabled = dpm.isApplicationHidden(cn, "com.android.browser");
        } catch (SecurityException e) {
            Toast.makeText(this, "please set device admin first", Toast.LENGTH_LONG).show();
            finish();
        }

        Button browserButton = findViewById(R.id.button_browser);
        Button deactivateButton = findViewById(R.id.button_deactivate);

        setButtonText(browserButton,isBrowserDisabled);


        browserButton.setOnClickListener(view -> {
            dpm.setApplicationHidden(cn, "com.android.browser", !isBrowserDisabled);
            setButtonText(browserButton, !isBrowserDisabled);
        });

        deactivateButton.setOnClickListener(view -> {
            dpm.clearDeviceOwnerApp("org.tag.sonimxp3.exemple");
            Toast.makeText(this, "We are no more the device owner", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void setButtonText(Button button,boolean disabled) {
        if (disabled)
            button.setText("Enable Browser");
        else
            button.setText("Disable Browser");
    }
}