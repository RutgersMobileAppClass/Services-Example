package com.example.instructor.servicesexample;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final String link = intent.getStringExtra("link");
        Toast.makeText(DownloadService.this, "Test", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                DownloadHelper.downloadFile(link,0);

                Intent broadcast = new Intent();
                broadcast.setAction("FINISHED");
                sendBroadcast(broadcast);


            }
        });

        Toast.makeText(DownloadService.this, "Test 1 2 3", Toast.LENGTH_SHORT).show();
        thread.start();



        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public IBinder onBind(Intent intent) {


        return null;

    }


}
