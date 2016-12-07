package com.example.aplikacija.organization;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.example.Dogodek;

public class ActivityDogodkiMain extends AppCompatActivity {
    public static final String TAG="ActivityDogodkiMain";
    ListView l;
    TextView prijava;
    ApplicationMy app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);

        String username = getIntent().getStringExtra("Username");

        app = (ApplicationMy)getApplication();
        l = (ListView)findViewById(R.id.listView);

        //spinner = (ProgressBar)
        //adapter = new ArrayAdapter<Dogodek>(this,R.layout.single_layout,R.id.textView,app.getAllData().getDog());
        //l.setAdapter(adapter);

        prijava = (TextView)findViewById(R.id.prijava);
        prijava.setText(username);

        ActivityDogodkiMainAdapter adap = new ActivityDogodkiMainAdapter(getBaseContext(),R.layout.layout_dogodek_view);
        for(Dogodek d:app.getAllData().getDog()){
            adap.add(d);
            /*Intent i = new Intent(this,Prijava.class);
            i.putExtra("id",d.getID());
            startActivity(i);*/
        }
        l.setAdapter(adap);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"Click ActivityDogodek.DOGODEK_POS,position"+position+" call ActivityDogodek");
                Intent i = new Intent(getApplicationContext(),ActivityDogodek.class);
                i.putExtra(ActivityDogodekIzvajalec.DOGODEK_ID,app.getAllData().getDog().get(position).getID());
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        l.invalidate();
        //adap.notifyDataSetChanged();
    }

    public void pridobiD(View view){
        Intent i = new Intent(this,Activity_Spreedsheats.class);
        startActivity(i);
    }
}
