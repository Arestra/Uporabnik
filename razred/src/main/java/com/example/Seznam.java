package com.example;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alen on 20.3.2016.
 */
public class Seznam {

    private HashMap<Integer,Dogodek> dog;
    private HashMap<Integer,Skladbe> skl;
    private ArrayList<Osebe>osebes;


    public Seznam(){
        dog = new HashMap<Integer,Dogodek>();
        skl = new HashMap<Integer, Skladbe>();
        osebes=new ArrayList<>();

    }

    public void dodaj(Dogodek d){
        dog.put(d.getID(),d);
    }
    public void dodaj(Skladbe s){
        skl.put(s.getSeIgrajo(),s);
    }
    public void dodaj(Osebe o){
        osebes.add(o);
    }
    public int velikostSkl(){return skl.size();}
    public int velikostOs(){return osebes.size();}
    public Skladbe getSkladbe(int i){return skl.get(i);}
    public Osebe getOsebe(int i){
        return osebes.get(i);
    }

    public ArrayList<Skladbe>najdi(String something)
    {
        ArrayList<Skladbe>najdeni = new ArrayList<>();
        int i = 0;
        while(i<skl.size())
        {
            if(skl.get(i).IzbraneS(something))najdeni.add(skl.get(i));
            i++;
        }
        return najdeni;
    }
    public Dogodek getDogodek(Integer id){
        return dog.get(id);
    }
    public Skladbe getSklad(Integer id){return skl.get(id);}

    public ArrayList<Dogodek> getDog() {
            return new ArrayList(dog.values());
    }
    public ArrayList<Skladbe> getSkl(){return new ArrayList<>(skl.values());}


    public ArrayList<Osebe>getOS(){
        return osebes;
    }

    public static Seznam getScenarijSkladb()
    {
        Seznam vseH = new Seznam();
        Dogodek a = new Dogodek(1,"Vecer domace glasbe","Celje","Kulturni dom","12:22","1.1.2016","10 eur");


        a.add(new Izvajalci(1,"Ansambel Lun'ca",1));
        a.add(new Izvajalci(1,"Ansambel Mladi Upi",2));
        a.add(new Izvajalci(1,"Ansambel Stil",3));

        a.addS(new Skladbe(1,"Sve je ona meni",1));
        a.addS(new Skladbe(1,"Zgodba najina",1));
        a.addS(new Skladbe(1,"Pozabila te bom",1));
        a.addS(new Skladbe(1,"Ta sosedov Francelj",2));
        a.addS(new Skladbe(1,"Ta noc bo najina",2));
        a.addS(new Skladbe(1,"Z njim bo lepse",2));
        a.addS(new Skladbe(1,"Zdaj me poglej",3));
        a.addS(new Skladbe(1,"V disko morem it",3));
        a.addS(new Skladbe(1,"Le tvoj",3));
        vseH.dodaj(a);

        a = new Dogodek(2,"Znana harmonika ima svoj glas","Maribor","Trust","21:22","1.2.2016","7 eur");


        a.add(new Izvajalci(2,"GADI",1));
        a.add(new Izvajalci(2,"Ansambel Nemir",2));
        a.add(new Izvajalci(2,"Pogum",3));
        a.add(new Izvajalci(2,"Ansambel Spev",4));
        a.add(new Izvajalci(2,"Poskocni muzikantje",5));


        a.addS(new Skladbe(2,"A si kej nora name",1));
        a.addS(new Skladbe(2,"Z njo",2));
        a.addS(new Skladbe(2,"Tako punco rad bi mel",3));
        a.addS(new Skladbe(2,"Ne diraj mojo ljubav",4));
        a.addS(new Skladbe(2,"Reci mi da moja si",5));
        a.addS(new Skladbe(2,"Moja punca",1));
        a.addS(new Skladbe(2,"Cao Adijo",2));
        a.addS(new Skladbe(2,"Adijo madam",3));
        a.addS(new Skladbe(2,"Super punca",4));
        a.addS(new Skladbe(2,"Poslovni oglas",2));
        a.addS(new Skladbe(2,"Daj mi daj mi daj",4));
        vseH.dodaj(a);


        a = new Dogodek(3,"Najvecja zabava letosnjega leta","Ptuj","Gostilna PP","13:30","3.7.2016","Vstopnine ni!");

        a.add(new Izvajalci(3,"Ansambel UNIKAT",1));
        a.add(new Izvajalci(3,"Ansambel Smeh",2));
        a.add(new Izvajalci(3,"Tanja Zagar",3));
        a.add(new Izvajalci(3,"Domen Kumer",4));

        a.addS(new Skladbe(3,"Kar je moje, je tvoje",1));
        a.addS(new Skladbe(3,"Srce ne razume",1));
        a.addS(new Skladbe(3,"Ne cukaj me za kitki",1));
        a.addS(new Skladbe(3,"Dovolj mi je",2));
        a.addS(new Skladbe(3,"Le ti",2));
        a.addS(new Skladbe(3,"Zame ustvarjena",2));
        a.addS(new Skladbe(3,"Podaj mi roko",2));
        a.addS(new Skladbe(3,"Zame zakantaj",3));
        a.addS(new Skladbe(3,"Ladi ladi da da",3));
        a.addS(new Skladbe(3,"Številka 3",3));
        a.addS(new Skladbe(3,"Najprej stalca, pol pa kravca",4));
        a.addS(new Skladbe(3,"Tvoja mama",4));
        a.addS(new Skladbe(3,"Narodni Hit-mix",4));
        vseH.dodaj(a);

        a = new Dogodek(4,"Najvecja PRVA","Celje","Disco Planet","22:00","15.7.2016","10 eur");

        a.add(new Izvajalci(4,"Ansambel Modrijani",1));
        a.add(new Izvajalci(4,"Simpl Band",2));
        a.add(new Izvajalci(4,"Ansambel Strajk",3));
        a.add(new Izvajalci(4,"Severina",4));

        a.addS(new Skladbe(4,"Kiss me",1));
        a.addS(new Skladbe(4,"Ti moja rozica",1));
        a.addS(new Skladbe(4,"Ti znas",3));
        a.addS(new Skladbe(4,"Hvala ker ljubis me",3));
        a.addS(new Skladbe(4,"Gazdalin",4));
        a.addS(new Skladbe(4,"Mesano-mix",2));
        a.addS(new Skladbe(4,"UNO-MOMENTO",4));
        a.addS(new Skladbe(4,"Moja stikla",4));
        a.addS(new Skladbe(4,"Italiana",4));
        vseH.dodaj(a);

        a = new Dogodek(5,"Martinovanje na otoku","Bled","Otok na Bledu","13:40","22.11.2016","Vstopnine ni!");

        a.add(new Izvajalci(5,"Ansambel Sekstakord",1));
        a.add(new Izvajalci(5,"Klapa Kampanel",2));
        a.add(new Izvajalci(5,"Ansambel Spev",3));

        a.addS(new Skladbe(5,"Objemi me",1));
        a.addS(new Skladbe(5,"V srcu si",1));
        a.addS(new Skladbe(5,"Niko se ne smije kao ti",2));
        a.addS(new Skladbe(5,"Nedodirljiva",2));
        a.addS(new Skladbe(5,"Aj ca, volin te",2));
        a.addS(new Skladbe(5,"Ona disi po pomladi",3));
        a.addS(new Skladbe(5,"Pel bi zate",3));
        a.addS(new Skladbe(5,"Spev za godca",3));
        vseH.dodaj(a);

        a = new Dogodek(6,"Koncert glasbene sole Sentjur","Sentjur","Kulturni dom Sentjur","18:00","21.6.2016","Prostovolnji prispevki");

        a.add(new Izvajalci(6,"Pihalni orkester Sentjur",1));
        a.add(new Izvajalci(6,"Moski pevski zbor planina",2));
        a.add(new Izvajalci(6,"Tolkalni band Murska Sobota",3));

        a.addS(new Skladbe(6,"ABBA - Money Money",1));
        a.addS(new Skladbe(6,"StarWars Intro Song",2));
        a.addS(new Skladbe(6,"Samo ljubezen mi je dala",3));
        a.addS(new Skladbe(6,"Thunderstruck",3));
        a.addS(new Skladbe(6,"Planincani veseli",2));
        a.addS(new Skladbe(6,"To mora da je ljubav",1));
        vseH.dodaj(a);

        vseH.dodaj(new Osebe(1,"Janez Potrc"));
        vseH.dodaj(new Osebe(1,"Stefka Zofic"));
        vseH.dodaj(new Osebe(1,"Ludmila Novak"));
        vseH.dodaj(new Osebe(2,"Marjetka Ulaga"));
        vseH.dodaj(new Osebe(2,"Janez Bobnar"));
        vseH.dodaj(new Osebe(2,"Marinka Metec"));
        vseH.dodaj(new Osebe(3,"Amadej Pintaric"));
        vseH.dodaj(new Osebe(3,"Jasmina Dedic"));
        vseH.dodaj(new Osebe(3,"Lojze Peternel"));
        vseH.dodaj(new Osebe(4,"Tinka Marinka"));
        vseH.dodaj(new Osebe(4,"Lenart Mackovc"));
        vseH.dodaj(new Osebe(4,"Izak Najboljsi"));
        vseH.dodaj(new Osebe(5,"Ranko Babic"));
        vseH.dodaj(new Osebe(5,"Mojca Meterlin"));
        vseH.dodaj(new Osebe(5,"Katija Meznar"));
        vseH.dodaj(new Osebe(6,"Matias Monjec"));
        vseH.dodaj(new Osebe(6,"Domen Lahtu"));
        vseH.dodaj(new Osebe(6,"Miranda Metin"));

        return vseH;

    }

    @Override
    public String toString() {
        return "Seznam{" +
                "dog=" + dog +
                ", skl=" + skl +
                ", izv="  +
                ", osebes=" + osebes +
                '}';
    }


}
