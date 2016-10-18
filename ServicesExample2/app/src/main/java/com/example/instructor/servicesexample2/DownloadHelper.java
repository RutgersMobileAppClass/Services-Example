package com.example.instructor.servicesexample2;

/**
 * Created by instructor on 10/18/2016.
 */
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadHelper {

    public static void downloadFile(String link, int delay){

        File file = null;
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            // extract the end of the URL file name
            // Example : www.google.com/keylogger.exe, filename = keylogger.exe

            String filename = new File(url.getFile()).getName();
            file =  new File(Environment.getExternalStorageDirectory(),filename);

            OutputStream output = null;

            try {
                output = new PrintStream(file);
            } catch (FileNotFoundException e) {
                Log.wtf("File writing", e);
                e.printStackTrace();
            }

            try {
                InputStream input = urlConnection.getInputStream();
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = input.read(buffer)) > 0 ) {
                    output.write(buffer, 0, bufferLength);
                    Thread.sleep(1000*delay);
                }
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            Log.wtf("URL Download",e);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}