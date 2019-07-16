package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

public class NetworkStateListener extends BroadcastReceiver{
    private static final String TAG = NetworkStateListener.class.getSimpleName();

    FileWriter fileWriter = new FileWriter();
    DataHandler dataHandler = new DataHandler();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "NetworkStateChanged", Toast.LENGTH_LONG).show();
        
        String action = intent.getAction();
        String extrasString = dataHandler.getExtrasString(intent);


        StringBuilder sb = new StringBuilder();
        sb.append("{'action':'networkstate',");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        sb.append("'date':'");
        sb.append(currentDateTimeString);
        sb.append("',");
        sb.append(extrasString);
        sb.append("}");
        //append separator
        sb.append(";");
        fileWriter.writeToFile(context, context.getString(R.string.NetworkStateFilename),sb.toString());

/*

        StringBuilder sb = new StringBuilder();
        sb.append("--------networkstate--------");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        sb.append(currentDateTimeString);

        intent.getAction();
        //sb.append(intent.getData());
        fileWriter.writeToFile(context, context.getString(R.string.NetworkStateFilename),sb.toString());
*/

    }





}
