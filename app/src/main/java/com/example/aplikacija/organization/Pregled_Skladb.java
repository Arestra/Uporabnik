package com.example.aplikacija.organization;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Dogodek;
import com.example.Skladbe;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Pregled_Skladb extends AppCompatActivity {
    public static final String SKLADBE_POS = "SKLADBE_POS";
    public static final String SKLADBE_ID = "SKLADBE_ID";
    public static final String TAG = "Pregled Skladb";
    private Skladbe trenutna;
    ApplicationMy app;

    EditText Naziv_Skladbe;
    EditText Zvrst_Skladbe;

    private RatingBar barOcena;
    private TextView txtOcena;
    private Button btnOceni;
    int pos;
    int id;
    ImageView img;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled__skladb);

        Ratings();
        Oceni_Klik();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (ApplicationMy) getApplication();
        Naziv_Skladbe = (EditText) findViewById(R.id.skl_Nzv);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        if (i != null) {
            Bundle extras = i.getExtras();
            id=   extras.getInt(Pregled_Skladb.SKLADBE_ID,0);
            pos = i.getIntExtra(SKLADBE_POS, 0);
            Log.i(TAG,"id:"+pos);
            //update(app.getAllData().getSkladbe(pos));

            Dogodek a = app.getAllData().getDogodek(id);
            update(a.getListSkl().get(pos));
        }
    }

    public void update(Skladbe s) {
        trenutna = s;
        Naziv_Skladbe.setText(trenutna.getNaziv());
    }



    public void Ratings() {
        barOcena = (RatingBar) findViewById(R.id.rate);
        txtOcena = (TextView) findViewById(R.id.ocena);

        barOcena.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                txtOcena.setText(String.valueOf(rating));
            }
        });
    }

    public void Oceni_Klik() {

        barOcena = (RatingBar) findViewById(R.id.rate);
        btnOceni = (Button) findViewById(R.id.ocenaBtn);

        btnOceni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pregled_Skladb.this, String.valueOf(barOcena.getRating()), Toast.LENGTH_SHORT).show();
                app.save();
                finish();
            }
        });
    }

    public void YOU_klik(View v) {
        img = (ImageView) findViewById(R.id.you_ID);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
                startActivity(splet);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Pregled_Skladb Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aplikacija.organization/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Pregled_Skladb Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.aplikacija.organization/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
