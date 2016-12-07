package com.example;

/**
 * Created by Alen on 23.5.2016.
 */
public class Osebe {
    int ID;
    String ime;

    public Osebe(int ID, String ime) {
        this.ID = ID;
        this.ime = ime;
    }

    public void Osebe(){
        ID=0;
        ime="";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime;
    }
}
