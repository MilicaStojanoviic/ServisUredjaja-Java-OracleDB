/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeni;

import DO.DomainObject;
import forme.NalogPoGodinamaForma;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class StavkaRadnogNaloga extends DomainObject{
    
    private RadniNalog radniNalog;
    private int rb;
    private int kolicina;
    private int pdvStopa;
    private String opisGreske;
    private VrstaDobra vrstaDobra;
    private boolean izmenaOpisaGreske=false;

    public StavkaRadnogNaloga() {
    }

    public StavkaRadnogNaloga(RadniNalog radniNalog, int rb, int kolicina, int pdvStopa, String opisGreske, VrstaDobra vrstaDobra) {
        this.radniNalog = radniNalog;
        this.rb = rb;
        this.kolicina = kolicina;
        this.pdvStopa = pdvStopa;
        this.opisGreske = opisGreske;
        this.vrstaDobra = vrstaDobra;
    }
    
    

    @Override
    public String getTableName() {
        return "StavkaRadnogNaloga sn";
    }

    @Override
    public String getAllColumnNames() {
        return "n.brojNaloga, sn.rb, sn.kolicina, sn.pdvstopa, sn.opisgreske, d.sifraDobra, d.naziv, d.aktuelnaCena, jm.sifraJediniceMere, jm.NazivJM";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            
            int idjm = rs.getInt("SifraJediniceMere");
            String nazivJM = rs.getString("NazivJM");
            JedinicaMere jm = new JedinicaMere(idjm, nazivJM);
            
            int id = rs.getInt("SifraDobra");
            String naziv = rs.getString("Naziv");
            double aktuelnaCena = rs.getDouble("AktuelnaCena");
            VrstaDobra vd=new VrstaDobra(id, naziv, jm, aktuelnaCena);
            
            int broj=rs.getInt("BrojNaloga");
            RadniNalog n=new RadniNalog();
            n.setBrojNaloga(broj);
            
            int rb = rs.getInt("rb");
            int kolicina = rs.getInt("Kolicina");
            int pdv = rs.getInt("pdvStopa");
            String opis = rs.getString("OpisGreske");
            
            StavkaRadnogNaloga stavka=new StavkaRadnogNaloga(n, rb, kolicina, pdv, opis, vd);
            
            list.add(stavka);
        }

        return list;
    }

    @Override
    public String joinTable1() {
        return "RadniNalog n";
    }

    @Override
    public String joinTableClause1() {
        return "n.brojNaloga=sn.brojNaloga";
    }

    @Override
    public String joinTable2() {
        return "VrstaDobra d";
    }

    @Override
    public String joinTableClause2() {
        return "d.sifraDobra=sn.sifradobra";
    }

    @Override
    public String joinTable3() {
        return "JedinicaMere jm";
    }

    @Override
    public String joinTableClause3() {
        return "jm.sifraJediniceMere=d.sifraJediniceMere WHERE sn.brojNaloga="+radniNalog.getBrojNaloga();
    }

    @Override
    public String joinTable4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable5() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause5() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdColumnName() {
        return "sn.rb";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE sn.brojNaloga="+radniNalog.getBrojNaloga();
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
        return "sn.rb, sn.brojNaloga, sn.kolicina, sn.pdvstopa, sn.sifraDobra";
    }

    @Override
    public String getColumnValues() {
        return radniNalog.getBrojNaloga()+", "+kolicina+", "+pdvStopa+", "+vrstaDobra.getSifraDobra();
    }

    @Override
    public String getUpdateClause() {
        String opis=izmenaOpisaGreske?(", sn.opisGreske='"+this.opisGreske+"'"):"";
        return " sn.kolicina="+kolicina+", sn.pdvstopa="+pdvStopa+", sn.sifraDobra="+vrstaDobra.getSifraDobra()+opis;
    }

    @Override
    public String getWhereIdClause() {
         return "sn.brojNaloga="+radniNalog.getBrojNaloga();
    }

    @Override
    public String getUpdateWhereClause() {
        return "sn.rb="+rb+" AND sn.brojNaloga="+radniNalog.getBrojNaloga();
    }

    @Override
    public String getDeleteWhereClause() {
        return "sn.rb="+rb+" AND sn.brojNaloga="+radniNalog.getBrojNaloga();
    }

    public RadniNalog getRadniNalog() {
        return radniNalog;
    }

    public void setRadniNalog(RadniNalog radniNalog) {
        this.radniNalog = radniNalog;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getPdvStopa() {
        return pdvStopa;
    }

    public void setPdvStopa(int pdvStopa) {
        this.pdvStopa = pdvStopa;
    }

    public String getOpisGreske() {
        return opisGreske;
    }

    public void setOpisGreske(String opisGreske) {
        this.opisGreske = opisGreske;
    }

    public VrstaDobra getVrstaDobra() {
        return vrstaDobra;
    }

    public void setVrstaDobra(VrstaDobra vrstaDobra) {
        this.vrstaDobra = vrstaDobra;
    }

    public boolean isIzmenaOpisaGreske() {
        return izmenaOpisaGreske;
    }

    public void setIzmenaOpisaGreske(boolean izmenaOpisaGreske) {
        this.izmenaOpisaGreske = izmenaOpisaGreske;
    }
    
}
