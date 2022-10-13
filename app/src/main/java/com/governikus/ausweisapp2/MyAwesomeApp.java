package com.governikus.ausweisapp2;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Build;

public class MyAwesomeApp extends Application
{
    private static final String AA2_PROCESS = "ausweisapp2_service";

    @Override
    public void onCreate()
    {
        super.onCreate();
        if (isAA2Process())
            return;

        // Perform one-time initialization of YOUR app, e.g. Firebase connection
    }

    private boolean isAA2Process()
    {
        if (Build.VERSION.SDK_INT >= 28)
        {
            return Application.getProcessName().endsWith(AA2_PROCESS);
        }

        final int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : manager.getRunningAppProcesses())
        {
            if (appProcess.pid == pid)
            {
                return appProcess.processName.endsWith(AA2_PROCESS);
            }
        }
        return false;
    }
}