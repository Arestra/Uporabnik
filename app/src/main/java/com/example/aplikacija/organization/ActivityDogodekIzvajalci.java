package com.example.aplikacija.organization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Dogodek;
import com.example.Izvajalci;


public class ActivityDogodekIzvajalci extends AppCompatActivity {

    public static final String TAG="Izvajalci";
    ListView list;
    ApplicationMy app;
    ActivityDogodekIzvajalciAdapter adap;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izvajalci);


        app = (ApplicationMy)getApplication();
        list = (ListView)findViewById(R.id.izvajalciView);

        adap = new ActivityDogodekIzvajalciAdapter(getBaseContext(),R.layout.layout_izvajalci);
       /* for(com.example.Izvajalci i:app.getAllData().getIzv()){
            adap.add(i);
        }*/

        list.setAdapter(adap);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id2) {
                Intent i = new Intent(getApplicationContext(),ActivityDogodekIzvajalec.class);
                Log.i(TAG,"id position:"+position+" id="+id);
                i.putExtra(ActivityDogodekIzvajalec.IZVAJALCI_POS,position);
                i.putExtra(ActivityDogodekIzvajalec.DOGODEK_ID,id);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent a = getIntent();
            if (a != null) {
                id = a.getIntExtra(ActivityDogodekIzvajalec.DOGODEK_ID, 0);
                Log.i(TAG, "Pregled_Izvajalcev.DOGODEK_ID:" + id);
                update(id);
            }

    }

    public void update(int id){
        adap.clear();
        Dogodek my = app.getAllData().getDogodek(id);
        for (Izvajalci i:my.getList())
        adap.add(i);


    }
}
