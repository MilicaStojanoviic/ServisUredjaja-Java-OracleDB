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
public class Dobavljac extends DomainObject{
    
    private int sifraDobavljaca;
    private long PIB;
    private String ime;
    private String tip;
    private String adresa;
    private Mesto mesto;

    public Dobavljac() {
    }

    public Dobavljac(int sifraDobavljaca, long PIB, String ime, String tip, String adresa, Mesto mesto) {
        this.sifraDobavljaca = sifraDobavljaca;
        this.PIB = PIB;
        this.ime = ime;
        this.tip = tip;
        this.adresa = adresa;
        this.mesto = mesto;
    }
    

    @Override
    public String getTableName() {
        return "dobavljac d";
    }

    @Override
    public String getAllColumnNames() {
        return "d.SifraDobavljaca AS SifraDobavljaca, d.PIB.get_brojPIBa() AS PIB, d.NazivDob.get_ime() AS NazivDob, d.NazivDob.get_tip() AS TipPreduzeca, d.Adresa AS Adresa, m.SifraMesta AS SifraMesta, m.NazivMesto AS NazivMesto ";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();
        
        while(rs.next()){
            //System.out.println(rs.getObject(1));
            int sifra = rs.getInt("SifraDobavljaca");
            long PIB = rs.getLong("PIB");
            String ime = rs.getString("NazivDob");
            String tip = rs.getString("TipPreduzeca");
            String adresa = rs.getString("Adresa");
            
            int mestoId = rs.getInt("SifraMesta");
            String nazivMesta = rs.getString("NazivMesto");
            Mesto mesto = new Mesto(mestoId, nazivMesta);
            
            Dobavljac d = new Dobavljac(sifra, PIB, ime, tip,adresa, mesto);
            list.add(d);
        }
        
        return list;
    }

    @Override
    public String joinTable1() {
        return "Mesto m";
    }

    @Override
    public String joinTableClause1() {
        return "d.SifraMesta = m.SifraMesta";
    }


    @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;
        
        while(rs.next()){
            id = rs.getInt("COUNT(1)");
        }
        
        return id;
    }

    @Override
    public String getIdWhereClause() {
        return "SifraDobavljaca = " + sifraDobavljaca;
    }

    @Override
    public String getInsertColumnNames() {
        return "SifraDobavljaca, PIB, NazivDob, Adresa, SifraMesta";
    }

    @Override
    public String getColumnValues() {
        return  " PIB(" + PIB + "), Naziv('" + ime + "', '" + tip + "'), '"+adresa+"', "+mesto.getSifraMesta();
    }

    @Override
    public String getUpdateClause() {
        return "d.PIB = PIB(" + PIB + "), d.nazivDob = Naziv('" + ime + "', '" + tip +  "'), d.adresa = '" + adresa + "', SifraMesta = " + mesto.getSifraMesta();
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        return "SifraDobavljaca = " + sifraDobavljaca;
    }

    @Override
    public String getDeleteWhereClause() {
        return "SifraDobavljaca = " + sifraDobavljaca;
    }

    public int getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(int sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public long getPIB() {
        return PIB;
    }

    public void setPIB(long PIB) {
        this.PIB = PIB;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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
        return "d.sifraDobavljaca";
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
    public String joinTable6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause6() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
