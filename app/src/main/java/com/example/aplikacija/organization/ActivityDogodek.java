package com.example.aplikacija.organization;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.Dogodek;

import java.util.List;

public class ActivityDogodek extends AppCompatActivity{
    public static final String TAG="Kraj_Dogodka";
    public static final String DOGODEK_POS="DOGODEK_POS";
    Button izvID;

    EditText txtCena;
    EditText txt;
    EditText txtNaziv;
    EditText txtUra;
    EditText etLokacija;
    EditText prizorisce;

    int idDog;

    private Dogodek trenutni;

    ApplicationMy app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kraj__dogodka);
        app = (ApplicationMy) getApplication();

        etLokacija = (EditText) findViewById(R.id.etLokacija);
        txtNaziv =(EditText) findViewById(R.id.nzv);
        txt = (EditText) findViewById(R.id.txtdate);
        txtCena = (EditText)findViewById(R.id.txtCena);
        txtUra = (EditText)findViewById(R.id.hour);
        prizorisce = (EditText)findViewById(R.id.etPrizor);

        izvID = (Button)findViewById(R.id.izvId);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        if (i!=null) {
            idDog = i.getIntExtra(ActivityDogodekIzvajalec.DOGODEK_ID,0);
            Log.i(TAG,"ActivityDogodekIzvajalec.DOGODEK_ID "+DOGODEK_POS);
             update(app.getAllData().getDogodek(idDog));
        }
    }
    public void update(Dogodek d) {
        trenutni = d;
        txtUra.setText(trenutni.getCas());
        txtNaziv.setText(trenutni.getNaziv());
        txt.setText(trenutni.getDatum());
        txtCena.setText(trenutni.getCena());
        prizorisce.setText(trenutni.getPrizorisce());
        etLokacija.setText(trenutni.getKraj());
    }

    public void odpriZ(View v){
        etLokacija = (EditText)findViewById(R.id.etLokacija);
        String text = etLokacija.getText().toString();

        Intent returnIntent = new Intent(ActivityDogodek.this, MapsActivity.class);
        returnIntent.putExtra(ActivityDogodekIzvajalec.DOGODEK_ID,idDog);
        returnIntent.putExtra("result", text);
        setResult(Activity.RESULT_OK, returnIntent);

        startActivityForResult(returnIntent,1);
    }

    public void PCome(View v){
        Intent openGroup = new Intent(this,Pregled_Udelezb.class);
        startActivity(openGroup);
    }

    public void OpenIzv(View v){
        Log.i(TAG,"id:"+trenutni.getID()+" id=");
        Intent openMusicians = new Intent(this, ActivityDogodekIzvajalci.class);
        openMusicians.putExtra(ActivityDogodekIzvajalec.DOGODEK_ID,trenutni.getID());
        startActivity(openMusicians);
    }

    public void OpenMusic(View v){
        Intent openSongs = new Intent(this,ActivityDogodekSkladbe.class);
        openSongs.putExtra(Pregled_Skladb.SKLADBE_ID,trenutni.getID());
        startActivity(openSongs);
    }

    public void odpriScan (View v){
        Intent opnS = new Intent(this,Skeniranje.class);
        startActivity(opnS);
    }
}
