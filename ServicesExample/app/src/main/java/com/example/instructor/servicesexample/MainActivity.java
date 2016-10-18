package com.example.instructor.servicesexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Output;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText linkText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkText = (EditText) findViewById(R.id.linkText);
        resultText = (TextView) findViewById(R.id.resultText);

    }

    public void downloadClick(View view) {
        final String link = linkText.getText().toString();

        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("link",link);

        startService(intent);

    }

    private class DownloadBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            resultText.setText("Download Finished!");



        }
    }



}
