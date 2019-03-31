package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class NetworkStateListener extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "NetworkStateChanged", Toast.LENGTH_LONG).show();


        //intent.getAction();
    }





}
