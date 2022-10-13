package com.governikus.ausweisapp2;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    LocalCallback mCallback = new LocalCallback();
    IAusweisApp2Sdk mSdk;
    private static final String AA2_PROCESS = "ausweisapp2_service";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        System.out.println("Starting Project...");
        super.onCreate(savedInstanceState);

        ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {

                try {
                    System.out.println("onServiceConnect preparing mSdk init...");
                    mSdk = IAusweisApp2Sdk.Stub.asInterface(service);
                    System.out.println("onServiceConnect mSdk initialized!");
                    System.out.println("SessionID: " + mCallback.mSessionID);

                } catch (ClassCastException e) {
                    // ...
                }

                try
                {
                    if (!mSdk.connectSdk(mCallback))
                    {
                        // already connected? Handle error...
                    }
                }
                catch (RemoteException e)
                {
                    System.out.println(e.getStackTrace().toString());
                    // handle exception
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName className) {

                System.out.println("onService Disconnected.");
                mSdk = null;
                // ... details below
            }
        };

        String pkg = getApplicationContext().getPackageName();
        String name = "com.governikus.ausweisapp2.START_SERVICE";
        Intent serviceIntent = new Intent(name);
        serviceIntent.setPackage(pkg);
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
        System.out.println("onCreate-Method finished.");

    }
}

