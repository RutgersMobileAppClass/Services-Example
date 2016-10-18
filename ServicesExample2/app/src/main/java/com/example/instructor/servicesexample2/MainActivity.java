package com.example.instructor.servicesexample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText linkText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkText = (EditText) findViewById(R.id.linkText);
        resultText = (TextView) findViewById(R.id.resultText);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.STRING_ACTION);
        registerReceiver(new DownloadBroadcast(),filter);

    }

    public void downloadClick(View view) {
        final String link = linkText.getText().toString();

        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("link",link);

        new DownloadTask().execute(link);



        //startService(intent);

    }

    private class DownloadTask extends AsyncTask<String, Integer, Void>{

        @Override
        protected Void doInBackground(String... links) {
            String link = links[0]; // Access the array parameter from doInBackground
            //DownloadHelper.downloadFile(link,0);

            for (int i = 0; i<5; i++){
                try {
                    Thread.sleep(i*1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            Integer i = values[0];
            if(i!= null) {
                resultText.setText(i);
            }


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            resultText.setText("Finished");
        }
    }

    private class DownloadBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            resultText.setText("Download Finished!");



        }
    }

}
