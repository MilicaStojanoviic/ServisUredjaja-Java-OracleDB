/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeni;

import DO.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Skladiste extends DomainObject{
    
    private int sifraSkladista;
    private String naziv;
    private String adresa;
    private Mesto mesto;
    private long PIB;
    private String nazivFirme;
    private int particija=0;

    public Skladiste() {
    }

    public Skladiste(int sifraSkladista, String naziv, String adresa, Mesto mesto, long PIB, String nazivFirme) {
        this.sifraSkladista = sifraSkladista;
        this.naziv = naziv;
        this.adresa = adresa;
        this.mesto = mesto;
        this.PIB = PIB;
        this.nazivFirme = nazivFirme;
    }
    
    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String getTableName() {
        switch (this.particija) {
            case 1:
                return "Skladisteosnovno s";
            case 2:
                return "Skladistedetalji s";
            default:
                return "Skladiste s";
        }
    }

    @Override
    public String getAllColumnNames() {
        switch (this.particija) {
            case 1:
                return "s.sifraSkladista, s.naziv, s.adresa, m.siframesta, m.nazivmesto";
            case 2:
                return "s.sifraSkladista, s.PIB.get_brojPIBa() AS PIB, s.nazivFirme ";
            default:
                return "s.sifraSkladista, s.naziv, s.adresa, m.siframesta, m.nazivmesto, s.PIB.get_brojPIBa() AS PIB, s.nazivFirme";
        }
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {

            Skladiste s = new Skladiste();

            int sifra = rs.getInt("SifraSkladista");
            s.setSifraSkladista(sifra);

            if (particija != 2) {
                String naziv=rs.getString("Naziv");
                String adresa=rs.getString("Adresa");
                int idm = rs.getInt("SifraMesta");
                String nazivm = rs.getString("NazivMesto");
                Mesto mesto = new Mesto(idm, nazivm);
                
                s.setNaziv(naziv);
                s.setAdresa(adresa);
                s.setMesto(mesto);
            }

            if (particija != 1) {
                long PIB = rs.getLong("PIB");
                String naziv1=rs.getString("NazivFirme");
                
                s.setPIB(PIB);
                s.setNazivFirme(naziv1);
            }

            list.add(s);
        }

        return list;
    }

    @Override
    public String joinTable1() {
        switch (this.particija) {
            case 1:
                return "Mesto m";
            case 2:
                return "";
            default:
                return "Mesto m";
        }
    }

    @Override
    public String joinTableClause1() {
        switch (this.particija) {
            case 1:
                return "s.siframesta=m.siframesta";
            case 2:
                return "";
            default:
                return "s.siframesta=m.siframesta";
        }
    }

    @Override
    public String joinTable2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String getIdColumnName() {
        return "s.sifraSkladista";
    }

    @Override
    public String getMaxIdWhere() {
        return "";
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;
        
        while(rs.next()){
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
        return "sifraSkladista,Naziv, Adresa, siframesta,PIB, NazivFirme ";
    }

    @Override
    public String getColumnValues() {
        return "'"+naziv+"', '"+adresa+"', "+mesto.getSifraMesta()+", PIB("+PIB+"), '"+nazivFirme+"'";
    }

    @Override
    public String getUpdateClause() {
        return "s.naziv='"+naziv+"', s.adresa='"+adresa+"', s.siframesta="+mesto.getSifraMesta()+", s.PIB=PIB("+PIB+"), S.Nazivfirme='"+nazivFirme+"'";
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        return "s.sifraskladista="+sifraSkladista;
    }

    @Override
    public String getDeleteWhereClause() {
        return "s.sifraskladista="+sifraSkladista;
    }

    public int getSifraSkladista() {
        return sifraSkladista;
    }

    public void setSifraSkladista(int sifraSkladista) {
        this.sifraSkladista = sifraSkladista;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public long getPIB() {
        return PIB;
    }

    public void setPIB(long PIB) {
        this.PIB = PIB;
    }

    public String getNazivFirme() {
        return nazivFirme;
    }

    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }

    public int getParticija() {
        return particija;
    }

    public void setParticija(int particija) {
        this.particija = particija;
    }

    @Override
    public String joinTable6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
