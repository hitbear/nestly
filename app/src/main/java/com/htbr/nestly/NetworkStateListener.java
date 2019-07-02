package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class NetworkStateListener extends BroadcastReceiver{

    FileWriter fileWriter = new FileWriter();
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "NetworkStateChanged", Toast.LENGTH_LONG).show();
        


        StringBuilder sb = new StringBuilder();
        sb.append("networkstate-");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        sb.append(currentDateTimeString);

        intent.getAction();
        //sb.append(intent.getData());
        fileWriter.writeToFile(context, context.getString(R.string.NetworkStateFilename),sb.toString());


    }





}
