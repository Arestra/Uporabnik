package com.example;

import java.util.ArrayList;

/**
 * Created by Alen on 20.3.2016.
 */
public class Dogodek {

    int ID;
    String naziv;
    String cas;
    String datum;
    String kraj;
    String cena;
    String prizorisce;
    ArrayList<Izvajalci> list;
    ArrayList<Skladbe> listSkl;
    public Izvajalci getIzvajalca(int id){
       for(int i = 0; i<list.size();i++){
           if(list.get(i).idIzv== id){
               return list.get(i);
           }
       }
        return null;
    }
    public ArrayList<Izvajalci> getList() {
        return list;
    }
    public ArrayList<Skladbe>getListSkl(){return listSkl;}
    public Dogodek() {
        ID=0;
        naziv="";
        cas="";
        datum="";
        kraj="";
        cena="";
        prizorisce="";
        list = new ArrayList<>();
        listSkl = new ArrayList<>();
    }

    /*public Dogodek(String tip) {
        this.tip = tip;
    }*/

    public int getID(){return ID;}

    public String getNaziv() {
        return naziv;
    }

    public String getCas() {
        return cas;
    }

    public String getKraj() {
        return kraj;
    }

    public String getDatum() {
        return datum;
    }

    public String getCena(){return cena;}



    public String getPrizorisce(){return prizorisce;}


    public Dogodek(int ID,String naziv, String kraj,String prizorisce, String cas,String datum, String cena) {
        this.ID = ID;
        this.naziv = naziv;
        this.kraj = kraj;
        this.prizorisce = prizorisce;
        this.cas = cas;
        this.cena = cena;
        this.datum = datum;
        list = new ArrayList<>();
        listSkl = new ArrayList<>();
    }

    public void add(Izvajalci a){
        list.add(a);
    }

    public void addS(Skladbe s){
        listSkl.add(s);
    }
    @Override
    public String toString() {
        return "Dogodek{" +
                "naziv='" + naziv + '\'' +
                ", cas='" + cas + '\'' +
                ", datum='" + datum + '\'' +
                ", kraj='" + kraj + '\'' +
                ", cena='" + cena + '\'' +
                ", prizorisce='" + prizorisce + '\''+
                '}';
    }
}
