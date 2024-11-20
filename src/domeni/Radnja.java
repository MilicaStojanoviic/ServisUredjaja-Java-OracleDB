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
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Radnja extends DomainObject{
    
    private int sifraRadnje;
    private String nazivR;
    private String adresa;
    private Mesto mesto;

    public Radnja() {
    }

    public Radnja(int sifraRadnje, String nazivR, String adresa, Mesto mesto) {
        this.sifraRadnje = sifraRadnje;
        this.nazivR = nazivR;
        this.adresa = adresa;
        this.mesto = mesto;
    }

    @Override
    public String toString() {
        return nazivR+", "+mesto.getNaziv();
    }
    
    

    @Override
    public String getTableName() {
        return "Radnja r";
    }

    @Override
    public String getAllColumnNames() {
        return "r.sifraRadnje, r.nazivR, r.adresa, m.sifraMesta, m.nazivMesto";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();
        
        while(rs.next()){
            int sifra = rs.getInt("sifraRadnje");
            String naziv = rs.getString("NazivR");
            String adr = rs.getString("Adresa");
            
            int mestoId = rs.getInt("SifraMesta");
            String nazivMesta = rs.getString("NazivMesto");
            Mesto mesto = new Mesto(mestoId, nazivMesta);
            
            Radnja r=new Radnja(sifra, naziv, adr, mesto);
            list.add(r);
        }
        
        return list;
    }

    @Override
    public String joinTable1() {
        return "Mesto m";
    }

    @Override
    public String joinTableClause1() {
        return "r.sifraMesta=m.sifraMesta";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMaxIdWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSifraRadnje() {
        return sifraRadnje;
    }

    public void setSifraRadnje(int sifraRadnje) {
        this.sifraRadnje = sifraRadnje;
    }

    public String getNazivR() {
        return nazivR;
    }

    public void setNazivR(String nazivR) {
        this.nazivR = nazivR;
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

    @Override
    public String joinTable6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
