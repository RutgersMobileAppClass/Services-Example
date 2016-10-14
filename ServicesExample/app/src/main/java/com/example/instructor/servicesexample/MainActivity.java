package com.example.instructor.servicesexample;

import android.content.Intent;
import android.icu.util.Output;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkText = (EditText) findViewById(R.id.linkText);

    }

    public void downloadClick(View view) {
        final String link = linkText.getText().toString();

        //Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();

        File file =  new File(Environment.getExternalStorageDirectory(),"hey.text");
        try {
            PrintWriter print = new PrintWriter(file);
            print.println("aoooo");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(file.getAbsolutePath());

        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("link",link);

        System.out.println("1234");
        startService(intent);

    }



}
