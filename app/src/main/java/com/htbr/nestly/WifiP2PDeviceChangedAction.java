package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;


public class WifiP2PDeviceChangedAction extends BroadcastReceiver {


    FileWriter fileWriter = new FileWriter();
    DataHandler dataHandler = new DataHandler();

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "WifiP2PDevicesChanged", Toast.LENGTH_LONG).show();


        String action = intent.getAction();
        String extrasString = dataHandler.getExtrasString(intent);


        StringBuilder sb = new StringBuilder();
        sb.append("{'action':'wifip2devicechanged',");
        sb.append("'date':'");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        sb.append(currentDateTimeString);
        sb.append("',");
        sb.append(extrasString);
        sb.append("}");
        //append separator
        sb.append(";");
        fileWriter.writeToFile(context, context.getString(R.string.WifiP2PFileName), sb.toString());


        /*
        String action = intent.getAction();
        StringBuilder sb = new StringBuilder();

        sb.append("--------P2PDeviceChanged--------");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        sb.append(currentDateTimeString+"\n");

       if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            WifiP2pDevice device = (WifiP2pDevice) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);

            String myMac = device.deviceAddress;
            sb.append("Mac: ");
            sb.append(myMac);
            sb.append(" ");
            sb.append(device.deviceName);

            Log.d("WIFIp2p", "Device WiFi P2p MAC Address: " + myMac);
            Toast.makeText(context, "this device changed", Toast.LENGTH_LONG).show();

        }



        WifiManager wifiManager =(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi = wifiManager.getConnectionInfo();
        if (wifi != null) {
            // Get current router MAC address
            String bssid = wifi.getBSSID();
            sb.append("Bssid: "+bssid);
            sb.append(" ");
            //Log.d("BSSID",bssid);
        }
        fileWriter.writeToFile(context, context.getString(R.string.WifiP2PFileName), sb.toString());
        // write data to file
        //writeToFile(context, "test");

        // try to start service
       // Intent serviceIntent = new Intent(context, SendService.class);
        //context.startService(serviceIntent);

        */

    }



}
