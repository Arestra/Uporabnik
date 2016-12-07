package com.example.aplikacija.organization;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Alen on 15.5.2016.
 */
public class Pridejo extends BaseAdapter {

    private Context con;
    private List<Udelezbe>listaU;

    public Pridejo(Context con, List<Udelezbe> listaU) {
        this.con = con;
        this.listaU = listaU;
    }


    @Override
    public int getCount() {
        return listaU.size();
    }

    @Override
    public Object getItem(int position) {
        return listaU.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(con,R.layout.list_item_udelezbe,null);
        TextView name = (TextView)v.findViewById(R.id.imeO);
        name.setText(listaU.get(position).getIme());
        return v;
    }
}
