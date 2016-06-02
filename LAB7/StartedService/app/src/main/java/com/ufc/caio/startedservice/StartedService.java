package com.ufc.caio.startedservice;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.widget.Toast;

public
class StartedService extends Service{
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg){
            long endTime = System.currentTimeMillis() + 5 * 1000;
            while(System.currentTimeMillis() < endTime){
                synchronized(this){
                    try
                    {
                        wait(endTime - System.currentTimeMillis());
                    }
                    catch(Exception e){}
                }
            }
            stopSelf(msg.arg1);
        }
    }
    @Override
    public void onCreate(){
        HandlerThread thread;
        thread = new HandlerThread("ServideStartArgs", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        serviceLooper = thread.getLooper();
        serviceHandler =  new ServiceHandler(serviceLooper);
    }
    @Override
    public    int    onStartCommand(Intent intent,int flags, int startId){
        Toast.makeText(this,"service starting",Toast.LENGTH_SHORT).show();
        Message msg =serviceHandler.obtainMessage();
        msg.arg1=startId;
        serviceHandler.sendMessage(msg);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @Override
    public void onDestroy(){
        Toast.makeText(this, "service done" , Toast.LENGTH_SHORT).show();
    }
}
