package com.example.aplikacija.organization;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Activity_Spreedsheats extends AppCompatActivity {

    TextView tvDownload;
    MyHttpClientResponseGoogleSheet m;
    MyDataList all;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__spreedsheats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        all = MyDataList.getTestScenario();
        m = new MyHttpClientResponseGoogleSheet(all);
        tvDownload = (TextView) findViewById(R.id.nzv);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onClickDownload(View view) {
        System.out.println("Start onClickImport");
        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute(m);
    }


    class RetrieveFeedTask extends AsyncTask<MyHttpClientResponseGoogleSheet, Void, MyHttpClientResponseGoogleSheet> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected MyHttpClientResponseGoogleSheet doInBackground(MyHttpClientResponseGoogleSheet... params) {
            MyHttpClientResponseGoogleSheet trenutni = params[0];
            MyHttpClient.get(MyHttpClient.getGoogleSpreadsheets(all.documentNameID),null, trenutni);
            return params[0];
        }

        protected void onPostExecute(MyHttpClientResponseGoogleSheet md) {
            if (m.ok) {
                tvDownload.setText(m.all.toString());
            } else
                tvDownload.setText("Ne dela :(" + m.resp);
            spinner.setVisibility(View.INVISIBLE);
        }
    }
    public void odpriZ(View v){
        EditText etLokacija = (EditText)findViewById(R.id.etLokacija);
        String text = etLokacija.getText().toString();

        Intent returnIntent = new Intent(Activity_Spreedsheats.this, MapsActivity.class);
        //returnIntent.putExtra(ActivityDogodekIzvajalec.DOGODEK_ID,idDog);
        returnIntent.putExtra("result", text);
        setResult(Activity.RESULT_OK, returnIntent);

        startActivityForResult(returnIntent,1);
    }

}
