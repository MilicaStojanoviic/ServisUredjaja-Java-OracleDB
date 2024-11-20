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
public class Marka extends DomainObject{
    
    private int sifraMarke;
    private String Naziv;

    public Marka() {
    }

    public Marka(int sifraMarke, String Naziv) {
        this.sifraMarke = sifraMarke;
        this.Naziv = Naziv;
    }
    
    @Override
    public String toString() {
        return sifraMarke+", "+Naziv;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Marka marka = (Marka) obj;
        return sifraMarke == marka.sifraMarke && Naziv.equals(marka.Naziv);
    }

    @Override
    public String getTableName() {
        return "Marka";
    }

    @Override
    public String getAllColumnNames() {
        return "SifraMarke, NazivMarka";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("SifraMarke");
            String naziv = rs.getString("NazivMarka");

            Marka m = new Marka(id, naziv);
            list.add(m);
        }

        return list;
    }


    @Override
    public String getIdColumnName() {
        return "SifraMarke";
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
        int id = 0;

        while (rs.next()) {
            id = rs.getInt("SifraMarke");
        }

        return id;
    }

    @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;

        while (rs.next()) {
            id = rs.getInt("COUNT(1)");
        }

        return id;
    }

    @Override
    public String getIdWhereClause() {
        return "NazivMarka = '" + Naziv + "'";
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
    
    @Override
    public String joinTable1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String getMaxIdWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSifraMarke() {
        return sifraMarke;
    }

    public void setSifraMarke(int sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String Naziv) {
        this.Naziv = Naziv;
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
