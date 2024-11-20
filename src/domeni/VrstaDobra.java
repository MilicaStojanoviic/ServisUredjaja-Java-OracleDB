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
public class VrstaDobra extends DomainObject{
    
    private int sifraDobra;
    private String naziv;
    private JedinicaMere jm;
    private double aktuelnaCena;
    private boolean cenaIzmena = false;

    public VrstaDobra() {
    }

    public VrstaDobra(int sifraDobra, String naziv, JedinicaMere jm, double aktuelnaCena) {
        this.sifraDobra = sifraDobra;
        this.naziv = naziv;
        this.jm = jm;
        this.aktuelnaCena = aktuelnaCena;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    

    @Override
    public String getTableName() {
        return "VrstaDobra vd";
    }

    @Override
    public String getAllColumnNames() {
        return "vd.SifraDobra, vd.Naziv, jm.SifraJediniceMere, jm.NazivJM, vd.aktuelnaCena";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("SifraDobra");
            String naziv = rs.getString("Naziv");
            
            int idjm = rs.getInt("SifraJediniceMere");
            String nazivJM = rs.getString("NazivJM");
            JedinicaMere jm = new JedinicaMere(idjm, nazivJM);
            
            double aktuelnaCena = rs.getDouble("AktuelnaCena");

            VrstaDobra vd=new VrstaDobra(id, naziv, jm, aktuelnaCena);
            list.add(vd);
        }

        return list;
    }

    @Override
    public String joinTable1() {
         return "JedinicaMere jm";
    }

    @Override
    public String joinTableClause1() {
        return "vd.SifraJediniceMere = jm.SifraJediniceMere";
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
        return "vd.sifraDobra";
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
        return "SifraDobra, Naziv, SifraJediniceMere, aktuelnaCena";
    }

    @Override
    public String getColumnValues() {
        return "'"+naziv+"', "+jm.getSifraJediniceMere()+", "+aktuelnaCena;
    }

    @Override
    public String getUpdateClause() {
        String cena = cenaIzmena?(", vd.AktuelnaCena = " + this.aktuelnaCena):"";
        return "vd.Naziv='"+naziv+"', vd.SifraJediniceMere= "+jm.getSifraJediniceMere()+cena;
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        return "vd.sifraDobra="+sifraDobra;
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSifraDobra() {
        return sifraDobra;
    }

    public void setSifraDobra(int sifraDobra) {
        this.sifraDobra = sifraDobra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public double getAktuelnaCena() {
        return aktuelnaCena;
    }

    public void setAktuelnaCena(double aktuelnaCena) {
        this.aktuelnaCena = aktuelnaCena;
    }

    public boolean isCenaIzmena() {
        return cenaIzmena;
    }

    public void setCenaIzmena(boolean cenaIzmena) {
        this.cenaIzmena = cenaIzmena;
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
