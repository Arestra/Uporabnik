package com.example;

/**
 * Created by Alen on 20.3.2016.
 */
public class Skladbe {

    int seIgrajo;
    String naziv;
    int izv;

    public Skladbe(int seIgrajo, String naziv,int izv) {
        this.seIgrajo = seIgrajo;
        this.naziv = naziv;
        this.izv=izv;
    }

    public Skladbe() {
        seIgrajo = 0;
        naziv="";
        izv=0;
    }

    public int getSeIgrajo(){return seIgrajo;}


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


    @Override
    public String toString() {
        return "Skladbe{" +
                ", naziv='" + naziv + '\'' +
                '}';
    }

    public int getIzv() {
        return izv;
    }

    public void setIzv(int izv) {
        this.izv = izv;
    }

    public boolean IzbraneS(String key)
    {
        if(naziv.contains(key))
        {return true;}

        return false;
    }
}
