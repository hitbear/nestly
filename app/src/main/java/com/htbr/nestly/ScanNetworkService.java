package com.htbr.nestly;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.util.List;

public class ScanNetworkService extends Service {

    Context context;
    WifiManager wifiManager;
    //constructor
    public ScanNetworkService(){

        //wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

    }



    BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            boolean success = intent.getBooleanExtra(
                    WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
            } else {
                // scan failure handling
                scanFailure();
            }
        }
    };



    private Looper serviceLooper;
    private ScanNetworkService serviceHandler;

    public ScanNetworkService(Looper serviceLooper) {
    }


    @Override
    public void onCreate() {
        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block. We also make it
        // background priority so CPU-intensive work doesn't disrupt our UI.
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Thread.NORM_PRIORITY);
        //  Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        serviceLooper = thread.getLooper();
        serviceHandler = new ScanNetworkService(serviceLooper);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "scanning starting", Toast.LENGTH_SHORT).show();

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        //Message msg = serviceHandler.obtainMessage();
        //msg.arg1 = startId;
        //serviceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart


        //Context context = getApplicationContext();

        // Scan for available WIFI NETWORKS


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);


        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            scanFailure();
        }



        return START_STICKY;
    }


    private void scanSuccess() {
        WifiConnector wifiConnector = new WifiConnector(this);
        List<ScanResult> results = wifiManager.getScanResults();
        //... use new scan results ...

        System.out.println("Scanned networks: " +results.size());

        for(ScanResult result : results){
            System.out.println("NETWORK_TO_STRING:  "+result.toString() + result.SSID);
            if (result.SSID.equals("AndroidAP")){
                wifiConnector.connectToNetwork(result);
            }
        }
    }

    private void scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        List<ScanResult> results = wifiManager.getScanResults();
        // ... potentially use older scan results ...
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Scan Network Service done", Toast.LENGTH_SHORT).show();
    }
}
