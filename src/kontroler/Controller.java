/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import DBB.DatabaseBroker;
import DO.DomainObject;
import domeni.Cena;
import domeni.Dobavljac;
import domeni.JedinicaMere;
import domeni.Klijent;
import domeni.Marka;
import domeni.Mesto;
import domeni.RadniNalog;
import domeni.Radnik;
import domeni.Radnja;
import domeni.Skladiste;
import domeni.StavkaRadnogNaloga;
import domeni.Uredjaj;
import domeni.VrstaDobra;
import forme.VrstaDobraForma;
import java.sql.SQLException;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Lenovo
 */
public class Controller {
    
    public static Controller instance;
    private DatabaseBroker db;
    
    private Controller() throws Exception {
        db = new DatabaseBroker();
        db.connect();
    }

    public static Controller getInstance() throws Exception {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<Dobavljac> getDobavljacList() throws Exception {
        Dobavljac po = new Dobavljac();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(po);
        db.disconnect();
        List<Dobavljac> objects = domainObjects.stream().map(Dobavljac.class::cast).collect(toList());
        return objects;
    }

    public List<Uredjaj> getUredjajList() throws Exception {
        Uredjaj u = new Uredjaj();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(u);
        db.disconnect();
        List<Uredjaj> objects = domainObjects.stream().map(Uredjaj.class::cast).collect(toList());
        return objects;
    }

    public List<Marka> getMarkeList() throws Exception {
        Marka m = new Marka();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(m);
        db.disconnect();
        List<Marka> objects = domainObjects.stream().map(Marka.class::cast).collect(toList());
        return objects;
    }

    public int getNextBroj(DomainObject object) throws Exception {
        db.connect();
        int broj = db.maxId(object);
        db.disconnect();
        return broj;
    }

    public int exists(DomainObject object) throws Exception {
        db.connect();
        int exists = db.exists(object);
        db.disconnect();
        return exists;
    }

    public void insert(DomainObject object) throws Exception {
        db.connect();
        db.insert(object);
        db.disconnect();
    }

    public void insert2(DomainObject object) throws Exception {
        db.connect();
        db.insert2(object);
        db.disconnect();
    }
    
    public void insert3(Cena cena) throws Exception {
        db.connect();
        db.insert3(cena);
        db.disconnect();
    }

    public int getId(DomainObject object) throws Exception {
        db.connect();
        int id = db.getId(object);
        db.disconnect();
        return id;
    }
    
    public void insert1(RadniNalog r) throws Exception {
        db.connect();
        db.insert4(r);
        db.disconnect();
    }

    public void update1(RadniNalog r) throws Exception {
        db.connect();
        db.update2(r);
        db.disconnect();
    }

    public void update(DomainObject object) throws Exception {
        db.connect();
        db.update(object);
        db.disconnect();
    }
    
    public void update1(Cena cena) throws Exception {
        db.connect();
        db.update1(cena);
        db.disconnect();
    }

    public void delete(DomainObject object) throws Exception {
        db.connect();
        db.delete(object);
        db.disconnect();
    }
    
    public void delete1(Cena c) throws Exception {
        db.connect();
        db.delete1(c);
        db.disconnect();
    }

    public List<Mesto> getMestaList() throws Exception {
        Mesto m = new Mesto();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(m);
        db.disconnect();
        List<Mesto> objects = domainObjects.stream().map(Mesto.class::cast).collect(toList());
        return objects;
    }

    public List<VrstaDobra> getVrstaDobraList() throws Exception {
        VrstaDobra vd= new VrstaDobra();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(vd);
        db.disconnect();
        List<VrstaDobra> objects = domainObjects.stream().map(VrstaDobra.class::cast).collect(toList());
        return objects;
    }

    public List<Cena> getCenaList(VrstaDobra vd) throws Exception {
        Cena c = new Cena();
        c.setVrstaDobra(vd);
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(c);
        db.disconnect();
        List<Cena> objects = domainObjects.stream().map(Cena.class::cast).collect(toList());
        return objects;
    }

    public List<Skladiste> getSkladisteList(Skladiste s) throws Exception {
        db.connect();
        List<DomainObject> domainObjects;
        switch(s.getParticija()){
            case 1:
                domainObjects = db.getJoined1(s);
                break;
            case 2:
                domainObjects = db.getAll(s);
                break;
            default:
                domainObjects = db.getJoined1(s);
        }
        db.disconnect();
        List<Skladiste> objects = domainObjects.stream().map(Skladiste.class::cast).collect(toList());
        return objects;
    }

    public List<RadniNalog> getNalogList(RadniNalog n) throws Exception {
        db.connect();
        List<DomainObject> domainObjects = db.getJoined6(n);
        db.disconnect();
        List<RadniNalog> objects = domainObjects.stream().map(RadniNalog.class::cast).collect(toList());
        return objects;
    }

    public List<Klijent> getKlijentiList() throws Exception {
        Klijent k = new Klijent();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(k);
        db.disconnect();
        List<Klijent> objects = domainObjects.stream().map(Klijent.class::cast).collect(toList());
        return objects;
    }

    public List<Radnik> getRadniciList() throws Exception {
        Radnik r = new Radnik();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(r);
        db.disconnect();
        List<Radnik> objects = domainObjects.stream().map(Radnik.class::cast).collect(toList());
        return objects;
    }

    public List<Radnja> getRadnjeList() throws Exception {
        Radnja r = new Radnja();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(r);
        db.disconnect();
        List<Radnja> objects = domainObjects.stream().map(Radnja.class::cast).collect(toList());
        return objects;
    }

    public List<Uredjaj> getUredjajiList() throws Exception {
        Uredjaj u = new Uredjaj();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(u);
        db.disconnect();
        List<Uredjaj> objects = domainObjects.stream().map(Uredjaj.class::cast).collect(toList());
        return objects;
    }


    public List<JedinicaMere> getJMList() throws Exception {
        JedinicaMere jm = new JedinicaMere();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(jm);
        db.disconnect();
        List<JedinicaMere> objects = domainObjects.stream().map(JedinicaMere.class::cast).collect(toList());
        return objects;
    }

    public List<StavkaRadnogNaloga> getStavkeNalogaList(RadniNalog nalog) throws Exception {
        StavkaRadnogNaloga sn = new StavkaRadnogNaloga();
        sn.setRadniNalog(nalog);
        db.connect();
        List<DomainObject> domainObjects = db.getJoined3(sn);
        db.disconnect();
        List<StavkaRadnogNaloga> objects = domainObjects.stream().map(StavkaRadnogNaloga.class::cast).collect(toList());
        return objects;
    }

    public List<VrstaDobra> getDobraList() throws Exception {
        VrstaDobra r = new VrstaDobra();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(r);
        db.disconnect();
        List<VrstaDobra> objects = domainObjects.stream().map(VrstaDobra.class::cast).collect(toList());
        return objects;
    }


}
