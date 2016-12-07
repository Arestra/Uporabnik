package com.example.aplikacija.organization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Dogodek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alen on 13.4.2016.
 */
public class ActivityDogodkiMainAdapter extends ArrayAdapter{
    List dogodek = new ArrayList<Dogodek>();
    public ActivityDogodkiMainAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        dogodek.add(object);
    }

    static class DataHandler{
        ImageView slika;
        TextView txtN;
        TextView ura;
        TextView locate;

    }

    @Override
    public Object getItem(int position) {
        return this.dogodek.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_dogodek_view,parent,false);
            handler = new DataHandler();
            handler.slika = (ImageView)row.findViewById(R.id.imgViewDogodek);
            handler.txtN = (TextView)row.findViewById(R.id.textDogodek);
            handler.ura = (TextView)row.findViewById(R.id.urca);
            handler.locate = (TextView)row.findViewById(R.id.location);
            row.setTag(handler);
        }

        else{
            handler = (DataHandler)row.getTag();
        }

        Dogodek dog;
        dog = (Dogodek)this.getItem(position);
        //handler.slika.setImageResource(getContext().getResources().getIdentifier("drawable/podomace",null,null));
        handler.txtN.setText(dog.getNaziv());
        handler.ura.setText(dog.getCas());
        handler.locate.setText(dog.getKraj());
        return row;
    }
}
