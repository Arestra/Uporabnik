package com.example.aplikacija.organization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Dogodek;
import com.example.Izvajalci;
import com.example.Seznam;
import com.example.Skladbe;

public class ActivityDogodekSkladbe extends AppCompatActivity {

    public static final String TAG = "Pregled Skladb";
    ListView list;
    ApplicationMy app;
    private ActivityDogodekSkladbeAdapter adap;
    int pos;
    int id;
    Seznam seznam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodajanje_skladb);

        app = (ApplicationMy)getApplication();
        list = (ListView)findViewById(R.id.listView);

        adap = new ActivityDogodekSkladbeAdapter(getBaseContext(),R.layout.layout_skladbe);
        /*for(Skladbe d:app.getAllData().getSkl()){
            adap.add(d);
        }*/
        list.setAdapter(adap);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id2) {
                Intent i = new Intent(getApplicationContext(),Pregled_Skladb.class);
                i.putExtra(Pregled_Skladb.SKLADBE_POS,position);
                i.putExtra(Pregled_Skladb.SKLADBE_ID,id);

                startActivity(i);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent a = getIntent();
        if(a!=null) {
            id = a.getIntExtra(Pregled_Skladb.SKLADBE_ID, 0);
            Log.i(TAG, "id:" + id);
            update(id);
        }
    }

    public void update(int id){
        adap.clear();
        Dogodek second = app.getAllData().getDogodek(id);
        adap.setTrenutni(second);
        for (Skladbe s:second.getListSkl())
            adap.add(s);
    }

}
