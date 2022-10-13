package com.governikus.ausweisapp2;

import android.os.RemoteException;

class LocalCallback extends IAusweisApp2SdkCallback.Stub {
    public String mSessionID = null;

    @Override
    public void sessionIdGenerated(
            String pSessionId, boolean pIsSecureSessionId) throws RemoteException {
        System.out.println("sessionIDGenerated");
        mSessionID = pSessionId;
    }

    @Override
    public void receive(String pJson) throws RemoteException {
        System.out.println("recieve");
        // handle message from SDK
    }

    @Override
    public void sdkDisconnected() throws RemoteException {
        System.out.println("Prozess disconnected");
    }
}