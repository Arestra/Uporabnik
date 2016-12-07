package com.example.aplikacija.organization;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pregled_Udelezb extends AppCompatActivity {
    public static final String UDELEZBE_POS = "UDELEZBE_POS";
    public static final String UDELEZBE_ID = "UDELEZBE_ID";
    public static final String TAG = "Pregled_Udelezb";
    private ListView lvOsebe;
    private Pridejo coming;
    private List<Udelezbe> ude;
    private List<Udelezbe>ja;
    private Osebe trenutna;
    private ArrayAdapter<Osebe> adapter;

    ArrayList<Osebe>ime = new ArrayList<>();
    TextView osebe;
    ApplicationMy app;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled__udelezb);
        app= (ApplicationMy) getApplication();

        osebe = (TextView)findViewById(R.id.imeO);
        lvOsebe = (ListView) findViewById(R.id.udelezbe);
        adapter = new ArrayAdapter<Osebe>(getBaseContext(),R.layout.list_item_udelezbe,R.id.imeO,app.getAllData().getOS());
        lvOsebe.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        lvOsebe.invalidate();
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = getLayoutInflater();
        View rowView = convertView;

        rowView = inflater.inflate(R.layout.list_item_udelezbe, parent, false);

        TextView textview = (TextView) rowView.findViewById(R.id.imeO);
        textview.setText(position);
        if (position == 0) {
            rowView.setBackgroundColor(Color.BLUE);
        } else if (position % 2 == 1) {
            rowView.setBackgroundColor(Color.RED);
        } else if (position % 2 == 0) {
            rowView.setBackgroundColor(Color.BLUE);
        }
        return rowView;

    }
}
