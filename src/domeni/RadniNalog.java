/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeni;

import DO.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class RadniNalog extends DomainObject{
    
    private int brojNaloga;
    private Date datumIzdavanja;
    private Date datumIzvrsenja;
    private Date datumPrometa;
    private String opisGreske;
    private String napomena;
    private String dodatneInformacije;
    private String komentarIzvrsioca;
    private Klijent klijent;
    private Radnik radnik;
    private Uredjaj uredjaj;
    private Radnja radnja;
    private Marka marka;
    private boolean markaIzmena=false;
    private int particija;
    private double ukupanIznos;
    private boolean izmenaIznos=false;

    public RadniNalog() {
    }

    public RadniNalog(int brojNaloga, Date datumIzdavanja, Date datumIzvrsenja, Date datumPrometa, String opisGreske, String napomena, String dodatneInformacije, String komentarIzvrsioca, Klijent klijent, Radnik radnik, Uredjaj uredjaj, Radnja radnja, Marka marka, double ukupanIznos) {
        this.brojNaloga = brojNaloga;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIzvrsenja = datumIzvrsenja;
        this.datumPrometa = datumPrometa;
        this.opisGreske = opisGreske;
        this.napomena = napomena;
        this.dodatneInformacije = dodatneInformacije;
        this.komentarIzvrsioca = komentarIzvrsioca;
        this.klijent = klijent;
        this.radnik = radnik;
        this.uredjaj = uredjaj;
        this.radnja = radnja;
        this.marka = marka;
        this.ukupanIznos = ukupanIznos;
    }
    
    
    

    @Override
    public String getTableName() {
        switch(this.particija){
            case 1:
                return "RadniNalog PARTITION(pruzene_usluge_2022) n";
            case 2:
                return "RadniNalog PARTITION(pruzene_usluge_2023) n";
            default:
                return "RadniNalog n";
        }
    }

    @Override
    public String getAllColumnNames() {
        return "n.brojNaloga, n.datumIzdavanja, n.datumIzvrsenja, n.datumPrometa, n.opisGreske, n.napomena, n.dodatneInformacije, n.komentarIzvrsioca, "
                + "k.sifraKlijenta, k.imePrezimeK, k.kontakt, r.sifraRadnika, r.imePrezime, u.sifraUredjaja, u.naziv, u.tip,"
                + " ra.sifraRadnje, ra.nazivR, ra.adresa, me.sifraMesta, me.nazivMesto, m.sifraMarke, m.nazivMarka, n.UkupanIznos";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int broj = rs.getInt("BrojNaloga");
            Date datumIzdavanja = rs.getDate("DatumIzdavanja");
            Date datumIzvrsenja = rs.getDate("DatumIzvrsenja");
            Date datumPrometa = rs.getDate("DatumPrometa");
            String opisGreske=rs.getString("OpisGreske");
            String napomena=rs.getString("napomena");
            String dodatneInformacije=rs.getString("dodatneInformacije");
            String komentarIzvrsioca=rs.getString("komentarIzvrsioca");

            int idM = rs.getInt("SifraMarke");
            String naziv1 = rs.getString("NazivMarka");
            Marka marka = new Marka(idM, naziv1);

            int su = rs.getInt("SifraUredjaja");
            String nazivU=rs.getString("Naziv");
            String tip=rs.getString("Tip");
            Uredjaj u = new Uredjaj(su, nazivU, tip, marka);

            int idk = rs.getInt("sifraKlijenta");
            String nazivk = rs.getString("ImePrezimeK");
            String kontakt = rs.getString("Kontakt");
            Klijent k=new Klijent(idk, nazivk, kontakt);
            
            int idr = rs.getInt("sifraRadnika");
            String nazivr = rs.getString("ImePrezime");
            Radnik r=new Radnik(idr, nazivr);
            
            int mestoId = rs.getInt("SifraMesta");
            String nazivMesta = rs.getString("NazivMesto");
            Mesto mesto = new Mesto(mestoId, nazivMesta);
            
            int sifra = rs.getInt("sifraRadnje");
            String naziv = rs.getString("NazivR");
            String adr = rs.getString("Adresa");
            Radnja ra=new Radnja(sifra, naziv, adr, mesto);
            
            int ukupanIznos = rs.getInt("ukupanIznos");
            
            RadniNalog rn=new RadniNalog(broj, datumIzdavanja, datumIzvrsenja, datumPrometa, opisGreske, napomena, dodatneInformacije, komentarIzvrsioca, k, r, u, ra, marka, ukupanIznos);

            
            list.add(rn);
        }
        return list;
    }

    @Override
    public String joinTable1() {
        return "Klijent k";
    }

    @Override
    public String joinTableClause1() {
        return "k.sifraKlijenta=n.sifraKlijenta";
    }

    @Override
    public String joinTable2() {
        return "Radnik r";
    }

    @Override
    public String joinTableClause2() {
        return "r.sifraRadnika=n.sifraRadnika";
    }

    @Override
    public String joinTable3() {
        return "Uredjaj u";
    }

    @Override
    public String joinTableClause3() {
        return "u.sifraUredjaja=n.sifraUredjaja";
    }

    @Override
    public String joinTable4() {
        return "Radnja ra";
    }

    @Override
    public String joinTableClause4() {
        return "ra.sifraRadnje=n.sifraRadnje";
    }

    @Override
    public String joinTable5() {
        return "Marka m";
    }

    @Override
    public String joinTableClause5() {
        return "m.sifraMarke=n.sifraMarke";
    }
    
    
    @Override
    public String joinTable6() {
        return "Mesto me";
    }

    @Override
    public String joinTableClause6() {
        return "ra.sifraMesta=me.sifraMesta";
    }

    @Override
    public String getIdColumnName() {
        return "n.brojNaloga";
    }

    @Override
    public String getMaxIdWhere() {
        return "";
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;

        while (rs.next()) {
            id = rs.getInt("Max");
        }

        return id;
    }

    @Override
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInsertColumnNames() {
        return "n.BrojNaloga, n.DatumIzdavanja, n.DatumIzvrsenja, n.DatumPrometa, n.OpisGreske, n.Napomena, n.DodatneInformacije, n.KomentarIzvrsioca, n.SifraKlijenta, n.SifraRadnika, n.SifraUredjaja, n.SifraRadnje, n.SifraMarke";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String izdavanje = formatter.format(datumIzdavanja.getTime());
        String izvrsenje = formatter.format(datumIzvrsenja.getTime());
        String promet = formatter.format(datumPrometa.getTime());
        return "'"+izdavanje+"', '"+izvrsenje+"', '"+promet+"', '"+opisGreske+"', '"+napomena+"','"+dodatneInformacije+"', '"+komentarIzvrsioca+"', "+klijent.getSifraKlijenta()+", "+radnik.getSifraRadnika()+", "+uredjaj.getSifraUredjaja()+", "+radnja.getSifraRadnje()+", "+marka.getSifraMarke();
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String izdavanje = formatter.format(datumIzdavanja.getTime());
        String izvrsenje = formatter.format(datumIzvrsenja.getTime());
        String promet = formatter.format(datumPrometa.getTime());
        String iznos = izmenaIznos?(", n.UkupanIznos = " + ukupanIznos):"";
        String marka = markaIzmena?(", n.SifraMarke = " + this.marka.getSifraMarke()):"";
        return "n.datumIzdavanja='"+izdavanje+"', n.datumIzvrsenja='"+izvrsenje+"', n.datumPrometa='"+promet+"', n.opisGreske='"+opisGreske+"', n.napomena='"+napomena+"', n.dodatneInformacije='"+dodatneInformacije+"', n.komentarIzvrsioca='"+komentarIzvrsioca+"', n.sifraKlijenta="+klijent.getSifraKlijenta()+", n.sifraRadnika="+radnik.getSifraRadnika()+", n.sifraUredjaja="+uredjaj.getSifraUredjaja()+", n.sifraRadnje="+radnja.getSifraRadnje()+marka+iznos;
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        return "n.brojNaloga="+brojNaloga;
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBrojNaloga() {
        return brojNaloga;
    }

    public void setBrojNaloga(int brojNaloga) {
        this.brojNaloga = brojNaloga;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIzvrsenja() {
        return datumIzvrsenja;
    }

    public void setDatumIzvrsenja(Date datumIzvrsenja) {
        this.datumIzvrsenja = datumIzvrsenja;
    }

    public Date getDatumPrometa() {
        return datumPrometa;
    }

    public void setDatumPrometa(Date datumPrometa) {
        this.datumPrometa = datumPrometa;
    }

    public String getOpisGreske() {
        return opisGreske;
    }

    public void setOpisGreske(String opisGreske) {
        this.opisGreske = opisGreske;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getDodatneInformacije() {
        return dodatneInformacije;
    }

    public void setDodatneInformacije(String dodatneInformacije) {
        this.dodatneInformacije = dodatneInformacije;
    }

    public String getKomentarIzvrsioca() {
        return komentarIzvrsioca;
    }

    public void setKomentarIzvrsioca(String komentarIzvrsioca) {
        this.komentarIzvrsioca = komentarIzvrsioca;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public Uredjaj getUredjaj() {
        return uredjaj;
    }

    public void setUredjaj(Uredjaj uredjaj) {
        this.uredjaj = uredjaj;
    }

    public Radnja getRadnja() {
        return radnja;
    }

    public void setRadnja(Radnja radnja) {
        this.radnja = radnja;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public boolean isMarkaIzmena() {
        return markaIzmena;
    }

    public void setMarkaIzmena(boolean markaIzmena) {
        this.markaIzmena = markaIzmena;
    }

    public int getParticija() {
        return particija;
    }

    public void setParticija(int particija) {
        this.particija = particija;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public boolean isIzmenaIznos() {
        return izmenaIznos;
    }

    public void setIzmenaIznos(boolean izmenaIznos) {
        this.izmenaIznos = izmenaIznos;
    }


    
}
