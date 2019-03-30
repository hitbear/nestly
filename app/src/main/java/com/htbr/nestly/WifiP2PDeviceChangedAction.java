package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class WifiP2PDeviceChangedAction extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "WifiP2PDevicesChanged", Toast.LENGTH_LONG).show();


        intent.getAction();
        
    }
}
