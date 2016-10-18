package com.example.instructor.servicesexample2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class DownloadService extends Service {

    public static final String STRING_ACTION = "FINISHED";

    public DownloadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final String link = intent.getStringExtra("link");
        Toast.makeText(DownloadService.this, "Test", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //DownloadHelper.downloadFile(link,0);

                Intent broadcast = new Intent();
                broadcast.setAction(STRING_ACTION);
                sendBroadcast(broadcast);


            }
        });
        thread.start();



        return super.onStartCommand(intent, flags, startId);

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
