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
    public DownloadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final String link = intent.getStringExtra("link");
        Toast.makeText(DownloadService.this, "Test", Toast.LENGTH_SHORT).show();
        System.out.println("check 1 ");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                downloadFile(link);

            }
        });


        return super.onStartCommand(intent, flags, startId);

    }

    public void downloadFile(String link){
        try {
            URL url = new URL(link);

            // extract the end of the URL file name
            // Example : www.google.com/keylogger.exe, filename = keylogger.exe
            String filename = new File(url.getFile()).getName();
            File file =  new File(Environment.getExternalStorageDirectory(),filename);
            Toast.makeText(DownloadService.this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

            OutputStream output = null;

            try {
                output = new PrintStream(file);
            } catch (FileNotFoundException e) {
                Log.wtf("File writing", e);
                e.printStackTrace();
            }

            try {
                InputStream input = new BufferedInputStream(url.openStream());
                byte[] buffer = new byte[512000];
                int numRead;
                while((numRead = input.read(buffer)) != -1){
                    output.write(buffer,0,numRead);
                }
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            Log.wtf("URL Download",e);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {


        return null;

    }


}
