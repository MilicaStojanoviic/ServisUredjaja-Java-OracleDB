/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domeni;

import DO.DomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Cena extends DomainObject{
    
    private VrstaDobra vrstaDobra;
    private Date datumOd;
    private double iznos;

    public Cena() {
    }

    public Cena(VrstaDobra vrstaDobra, Date datumOd, double iznos) {
        this.vrstaDobra = vrstaDobra;
        this.datumOd = datumOd;
        this.iznos = iznos;
    }
    
    

    @Override
    public String getTableName() {
        return "Cena c";
    }

    @Override
    public String getAllColumnNames() {
        return "c.SifraDobra, c.datumOd, c.Iznos";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int sd = rs.getInt("SifraDobra");
            VrstaDobra vd = new VrstaDobra();
            vd.setSifraDobra(sd);
            
            Date datumOd =  rs.getDate("DatumOd");
            double iznos = rs.getDouble("Iznos");
            
            Cena c = new Cena( vd, datumOd, iznos);
            list.add(c);
        }

        return list;
    }

    @Override
    public String joinTable1() {
        return "VrstaDobra vd";
    }

    @Override
    public String joinTableClause1() {
        return "vd.SifraDobra = c.SifraDobra WHERE vd.SifraDobra = " + vrstaDobra.getSifraDobra();
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
         return "vd.SifraDobra";
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
        return "SifraDobra, datumOd, Iznos";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String datumOd = formatter.format(this.datumOd.getTime());
        return vrstaDobra.getSifraDobra()+ ", '" + datumOd + "', " + iznos;
    }

    @Override
    public String getUpdateClause() {
        return " c.iznos = " + iznos;
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdateWhereClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String datumOd = formatter.format(this.datumOd.getTime());
        return "c.SifraDobra="+vrstaDobra.getSifraDobra()+" AND c.DatumOd='"+datumOd+"'";
    }

    @Override
    public String getDeleteWhereClause() {
        return "c.SifraDobra="+vrstaDobra.getSifraDobra()+" AND c.DatumOd='"+datumOd+"'";
    }

    public VrstaDobra getVrstaDobra() {
        return vrstaDobra;
    }

    public void setVrstaDobra(VrstaDobra vrstaDobra) {
        this.vrstaDobra = vrstaDobra;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
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
