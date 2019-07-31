package com.htbr.nestly;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends FragmentActivity implements DownloadCallback {


    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment networkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean downloading = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.button);

        networkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), getString(R.string.servip));
        final FileWriter fileWriter = new FileWriter();

        // try to start service
       // Intent intent = new Intent(this, SendService.class);
       // startService(intent);



        // listenWifiState();


        //ask permission


        askpermission();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {





                // show file
                //fileWriter.writeToFile(getApplicationContext(), getString(R.string.WifiP2PFileName),"lalal");


                //Find the view by its id
                //TextView tv = (TextView)findViewById(R.id.textView);

                //Set the text
               // tv.setText(fileWriter.readFile(getApplicationContext(), getString(R.string.WifiP2PFileName)).toString());

                // Code here executes on main thread after user presses button
              // startUpload();

                //send infos to server
               // Intent serviceIntent = new Intent(getApplicationContext(), SendService.class);
                //getApplicationContext().startService(serviceIntent);

                //in this branch we scan the network //this code should only apper in branch "wifiScanner"


                //at first ask permission
                // Here, thisActivity is the current activity




                Intent serviceIntent = new Intent(getApplicationContext(), ScanNetworkService.class);

                getApplicationContext().startService(serviceIntent);



            }
        });

    }


    private void listenWifiState(){

        //startUpload();


    }


    private void startUpload() {
        if (!downloading && networkFragment != null) {
            // Execute the async download.
            networkFragment.startUpload();
            downloading = true;
        }
    }

    private void askpermission(){

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            // No explanation needed; request the permission
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                    Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
            else {
                ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);}
        } else {
            // Permission has already been granted
            System.out.println("ok");
        }
    }

 /*   @Override
    public void updateFromDownload(T result) {
        // Update your UI here based on result of download.
        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();
    }
*/
    @Override
    public void updateFromUpload(Object result) {
        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case DownloadCallback.Progress.ERROR:

                break;
            case DownloadCallback.Progress.CONNECT_SUCCESS:

                break;
            case DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS:

                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:

                break;
            case DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS:

                break;
        }
    }

    @Override
    public void finishDownloading() {
        downloading = false;
        if (networkFragment != null) {
            networkFragment.cancelDownload();
        }
    }





}
