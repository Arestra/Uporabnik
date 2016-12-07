package com.example.aplikacija.organization;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Dogodek;
import com.example.Izvajalci;
public class ActivityDogodekIzvajalec extends AppCompatActivity {
    public static final String IZVAJALCI_POS = "IZVAJALCI_POS";
    public static final String DOGODEK_ID = "DOGODEK_ID";
    public static final String TAG = "Pregled Izvajalcev";
    Izvajalci trenutni;
    ApplicationMy app;

    EditText NazivIzvajalca;
    ImageView img;
    ImageView you;

    private RatingBar Ocena;
    private TextView vasaOcena;
    private Button oceniBtn;
    int pos;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled__izvajalcev);

        Ratings();

        app = (ApplicationMy)getApplication();
        NazivIzvajalca = (EditText)findViewById(R.id.izv_Text);

    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        if(i!= null){
            Bundle extras = i.getExtras();
            id=   extras.getInt(ActivityDogodekIzvajalec.DOGODEK_ID,0);
            //id= (int)  extras.getLong(ActivityDogodekIzvajalec.DOGODEK_ID,0);
            pos = extras.getInt(ActivityDogodekIzvajalec.IZVAJALCI_POS,0);
            Log.i(TAG,"pos:"+pos+" id:"+id);
            Dogodek a = app.getAllData().getDogodek(id);
            update(a.getList().get(pos));
        }
    }

    public void update(Izvajalci izva){
        if(izva!=null) {
            trenutni = izva;
            NazivIzvajalca.setText(trenutni.getNaziv());
        }else  NazivIzvajalca.setText("Ne najdem");

    }



    public void Ratings() {
        Ocena = (RatingBar) findViewById(R.id.rate);
        vasaOcena = (TextView) findViewById(R.id.vasaOcena);

        Ocena.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                vasaOcena.setText(String.valueOf(rating));
            }
        });
    }
    public void OKlik(View v){
        Ocena = (RatingBar)findViewById(R.id.rate);
        oceniBtn = (Button)findViewById(R.id.ocene);
        oceniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDogodekIzvajalec.this,String.valueOf(Ocena.getRating()), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void slikaKlik(View v){
        img = (ImageView)findViewById(R.id.slikca);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splet = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.veselica.info/page/ansambli"));
                startActivity(splet);
            }
        });
    }

    public void Youtube(View v){
        you = (ImageView)findViewById(R.id.img);
        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent splet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
                startActivity(splet);
            }
        });
    }
}
