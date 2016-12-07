package com.example.aplikacija.organization;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Dogodek;

import org.w3c.dom.Text;

public class Skeniranje extends AppCompatActivity {
    ApplicationMy app;
    Dogodek trenutni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeniranje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (ApplicationMy)getApplication();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();*/
    }


    public void skanKlik(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanResult!=null){
            String barkoda;
            //String tip;
            barkoda = scanResult.getContents();
            //tip = scanResult.getFormatName();

            EditText etBarcode = (EditText)findViewById(R.id.etBarcode);
            TextView opis = (TextView)findViewById(R.id.result);
            TextView datum = (TextView)findViewById(R.id.datumD);
            TextView cena = (TextView)findViewById(R.id.cenaD);
            TextView ura = (TextView)findViewById(R.id.UraD);
            TextView lokal = (TextView)findViewById(R.id.lokalD);
            TextView lokacija = (TextView)findViewById(R.id.lokacijaD);

            etBarcode.setText(barkoda);

            for(Dogodek d:app.getAllData().getDog()){
                opis.setText(d.getNaziv().toString());
                datum.setText(d.getDatum().toString());
                cena.setText(d.getCena().toString());
                ura.setText(d.getCas().toString());
                lokal.setText(d.getPrizorisce().toString());
                lokacija.setText(d.getKraj().toString());

            }


        }
    }
}
