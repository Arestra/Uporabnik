package com.example.aplikacija.organization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.Izvajalci;
/**
 * Created by Alen on 20.4.2016.
 */
public class ActivityDogodekIzvajalciAdapter extends ArrayAdapter {
    List izv = new ArrayList<Izvajalci>();

    public ActivityDogodekIzvajalciAdapter(Context context, int resource){
        super(context,resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        izv.add(object);
    }

    static class DataHandler {
        ImageView slika;
        TextView txtN;
    }

    @Override
    public Object getItem(int position) {
        return this.izv.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_izvajalci, parent, false);
            handler = new DataHandler();
            handler.slika = (ImageView) row.findViewById(R.id.slikaIzv);
            handler.txtN = (TextView) row.findViewById(R.id.izvTxt);
            row.setTag(handler);
        } else {
            handler = (DataHandler) row.getTag();
        }

        Izvajalci izv;
        izv = (Izvajalci) this.getItem(position);
        handler.txtN.setText(izv.getNaziv());
        return row;
    }
}
