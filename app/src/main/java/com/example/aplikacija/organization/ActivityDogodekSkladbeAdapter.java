package com.example.aplikacija.organization;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Dogodek;
import com.example.Izvajalci;
import com.example.Seznam;
import com.example.Skladbe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alen on 13.4.2016.
 */
public class ActivityDogodekSkladbeAdapter extends ArrayAdapter{
    ApplicationMy app;
    Context context;
    Dogodek trenutni;

    List skladba = new ArrayList<Skladbe>();


    public ActivityDogodekSkladbeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public Dogodek getTrenutni() {
        return trenutni;
    }

    public void setTrenutni(Dogodek trenutni) {
        this.trenutni = trenutni;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        skladba.add(object);
    }

    static class DataHandler{
        ImageView slika;
        TextView txtN;
        TextView Izv;

    }
    @Override
    public Object getItem(int position) {
        return this.skladba.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_skladbe,parent,false);
            handler = new DataHandler();
            handler.slika = (ImageView)row.findViewById(R.id.slikaAns);
            handler.txtN = (TextView)row.findViewById(R.id.txtView);
            handler.Izv = (TextView)row.findViewById(R.id.izvajalci);
            row.setTag(handler);
        }

        else{
            handler = (DataHandler)row.getTag();
        }

        Skladbe skl;
        skl = (Skladbe) this.getItem(position);
        handler.txtN.setText(skl.getNaziv());
        handler.Izv.setText("(" + trenutni.getIzvajalca(skl.getIzv()).getNaziv()+")");
        return row;
    }

}
