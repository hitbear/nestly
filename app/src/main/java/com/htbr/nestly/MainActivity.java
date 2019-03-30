package com.htbr.nestly;

import android.content.BroadcastReceiver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        networkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "http://192.168.178.12:7070/upload/");


       // listenWifiState();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
               startUpload();

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
