package com.htbr.nestly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

public class DataHandler {
    private static final String TAG = DataHandler.class.getSimpleName();


    public String getExtrasString(Intent pIntent) {
        String extrasString = "";
        Bundle extras = pIntent.getExtras();
        try {
            if (extras != null) {
                Set<String> keySet = extras.keySet();
                for (String key : keySet) {
                    try {
                        String extraValue = pIntent.getExtras().get(key).toString().replace("\"","");
                        extrasString += "'" + key + "':'" + extraValue + "',";
                    } catch (Exception e) {
                        Log.d(TAG, "Exception 2 in getExtrasString():'" + e.toString() + "'");
                        extrasString += "'" + key + "': 'Exception:" + e.getMessage().replace("\"","") + "',";
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception in getExtrasString(): " + e.toString());
            extrasString += "' Exception:" + e.getMessage() + "'";
        }
        Log.d(TAG, "extras=" + extrasString);
        return extrasString.replace("\"" ,"'");
    }
}
