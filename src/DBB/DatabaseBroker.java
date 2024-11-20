/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBB;

import DO.DomainObject;
import domeni.Cena;
import domeni.RadniNalog;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class DatabaseBroker {

    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseBroker() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("properties.config");

            properties.load(fileInputStream);

            this.url = properties.getProperty("url");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");

            fileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connect() throws Exception {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom uspostavljanja konekcije sa bazom!");
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom raskidanja konekcije sa bazom!");
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska - commit!");
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska - rollback!");
            }
        }
    }

    public List<DomainObject> getAll(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined1(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined2(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined3(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined4(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3()
                    + " JOIN " + object.joinTable4() + " ON " + object.joinTableClause4();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined5(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3()
                    + " JOIN " + object.joinTable4() + " ON " + object.joinTableClause4()
                    + " JOIN " + object.joinTable5() + " ON " + object.joinTableClause5();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<DomainObject> getJoined6(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName()
                    + " JOIN " + object.joinTable1() + " ON " + object.joinTableClause1()
                    + " JOIN " + object.joinTable2() + " ON " + object.joinTableClause2()
                    + " JOIN " + object.joinTable3() + " ON " + object.joinTableClause3()
                    + " JOIN " + object.joinTable4() + " ON " + object.joinTableClause4()
                    + " JOIN " + object.joinTable5() + " ON " + object.joinTableClause5()
                    + " JOIN " + object.joinTable6() + " ON " + object.joinTableClause6();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getObjectsFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int maxId(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(" + object.getIdColumnName() + ") AS Max FROM " + object.getTableName() + object.getMaxIdWhere();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getMaxIdFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert(DomainObject object) throws SQLException {
        try {
            int id = maxId(object);
            id++;
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + " (" + object.getInsertColumnNames() + ") VALUES (" + id + ", " + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert2(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + object.getTableName() + " (" + object.getInsertColumnNames() + ") VALUES (" + object.getColumnValues() + ")";
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert3(Cena cena) throws SQLException {
        String upit = "INSERT INTO CENA (SifraDobra, datumOd, Iznos) "
                + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, cena.getVrstaDobra().getSifraDobra());
            ps.setDate(2, new Date(cena.getDatumOd().getTime()));
            ps.setDouble(3, cena.getIznos());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int update1(Cena cena) throws SQLException {
        String upit = "UPDATE CENA "
                + "SET IZNOS=? "
                + "WHERE SIFRADOBRA=? AND DATUMOD=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setDouble(1, cena.getIznos());
            ps.setInt(2, cena.getVrstaDobra().getSifraDobra());
            ps.setDate(3, new Date(cena.getDatumOd().getTime()));

            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int insert4(RadniNalog r) throws SQLException {
        String upit = "INSERT INTO RadniNalog (BrojNaloga, DatumIzdavanja, DatumIzvrsenja, DatumPrometa, OpisGreske, Napomena, "
                + "DodatneInformacije, KomentarIzvrsioca, SifraKlijenta, SifraRadnika, SifraUredjaja, SifraRadnje, SifraMarke, UkupanIznos) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            int id = maxId(r);
            id++;
            r.setBrojNaloga(id);
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, r.getBrojNaloga());
            ps.setDate(2, new Date(r.getDatumIzdavanja().getTime()));
            ps.setDate(3, new Date(r.getDatumIzvrsenja().getTime()));
            ps.setDate(4, new Date(r.getDatumPrometa().getTime()));
            ps.setString(5, r.getOpisGreske());
            ps.setString(6, r.getNapomena());
            ps.setString(7, r.getDodatneInformacije());
            ps.setString(8, r.getKomentarIzvrsioca());
            ps.setInt(9, r.getKlijent().getSifraKlijenta());
            ps.setInt(10, r.getRadnik().getSifraRadnika());
            ps.setInt(11, r.getUredjaj().getSifraUredjaja());
            ps.setInt(12, r.getRadnja().getSifraRadnje());
            ps.setInt(13, r.getMarka().getSifraMarke());
            ps.setDouble(14, r.getUkupanIznos());

            return ps.executeUpdate();

        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int update2(RadniNalog r) throws SQLException {
        if (r.isIzmenaIznos() && r.isMarkaIzmena()) {
            String upit = "UPDATE RadniNalog "
                    + "SET DatumIzdavanja=?, DatumIzvrsenja=?, DatumPrometa=?, OpisGreske=?, Napomena=?, DodatneInformacije=?, KomentarIzvrsioca=?, "
                    + " SifraKlijenta=?, SifraRadnika=?, SifraUredjaja=?, SifraRadnje=?, SifraMarke=?, UkupanIznos=? "
                    + "WHERE BROJNALOGA=? ";
            try {
                PreparedStatement ps = connection.prepareStatement(upit);

                ps.setDate(1, new Date(r.getDatumIzdavanja().getTime()));
                ps.setDate(2, new Date(r.getDatumIzvrsenja().getTime()));
                ps.setDate(3, new Date(r.getDatumPrometa().getTime()));
                ps.setString(4, r.getOpisGreske());
                ps.setString(5, r.getNapomena());
                ps.setString(6, r.getDodatneInformacije());
                ps.setString(7, r.getKomentarIzvrsioca());
                ps.setInt(8, r.getKlijent().getSifraKlijenta());
                ps.setInt(9, r.getRadnik().getSifraRadnika());
                ps.setInt(10, r.getUredjaj().getSifraUredjaja());
                ps.setInt(11, r.getRadnja().getSifraRadnje());
                ps.setInt(12, r.getMarka().getSifraMarke());
                ps.setDouble(13, r.getUkupanIznos());

                ps.setInt(14, r.getBrojNaloga());

                return ps.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        else if (r.isIzmenaIznos() ) {
            String upit = "UPDATE RadniNalog "
                    + "SET DatumIzdavanja=?, DatumIzvrsenja=?, DatumPrometa=?, OpisGreske=?, Napomena=?, DodatneInformacije=?, KomentarIzvrsioca=?, "
                    + " SifraKlijenta=?, SifraRadnika=?, SifraUredjaja=?, SifraRadnje=?, UkupanIznos=? "
                    + "WHERE BROJNALOGA=? ";
            try {
                PreparedStatement ps = connection.prepareStatement(upit);

                ps.setDate(1, new Date(r.getDatumIzdavanja().getTime()));
                ps.setDate(2, new Date(r.getDatumIzvrsenja().getTime()));
                ps.setDate(3, new Date(r.getDatumPrometa().getTime()));
                ps.setString(4, r.getOpisGreske());
                ps.setString(5, r.getNapomena());
                ps.setString(6, r.getDodatneInformacije());
                ps.setString(7, r.getKomentarIzvrsioca());
                ps.setInt(8, r.getKlijent().getSifraKlijenta());
                ps.setInt(9, r.getRadnik().getSifraRadnika());
                ps.setInt(10, r.getUredjaj().getSifraUredjaja());
                ps.setInt(11, r.getRadnja().getSifraRadnje());
                ps.setDouble(12, r.getUkupanIznos());

                ps.setInt(13, r.getBrojNaloga());

                return ps.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        else if (r.isMarkaIzmena()) {
            String upit = "UPDATE RadniNalog "
                    + "SET DatumIzdavanja=?, DatumIzvrsenja=?, DatumPrometa=?, OpisGreske=?, Napomena=?, DodatneInformacije=?, KomentarIzvrsioca=?, "
                    + " SifraKlijenta=?, SifraRadnika=?, SifraUredjaja=?, SifraRadnje=?, SifraMarke=? "
                    + "WHERE BROJNALOGA=? ";
            try {
                PreparedStatement ps = connection.prepareStatement(upit);

                ps.setDate(1, new Date(r.getDatumIzdavanja().getTime()));
                ps.setDate(2, new Date(r.getDatumIzvrsenja().getTime()));
                ps.setDate(3, new Date(r.getDatumPrometa().getTime()));
                ps.setString(4, r.getOpisGreske());
                ps.setString(5, r.getNapomena());
                ps.setString(6, r.getDodatneInformacije());
                ps.setString(7, r.getKomentarIzvrsioca());
                ps.setInt(8, r.getKlijent().getSifraKlijenta());
                ps.setInt(9, r.getRadnik().getSifraRadnika());
                ps.setInt(10, r.getUredjaj().getSifraUredjaja());
                ps.setInt(11, r.getRadnja().getSifraRadnje());
                ps.setInt(12, r.getMarka().getSifraMarke());

                ps.setInt(13, r.getBrojNaloga());

                return ps.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        else {
            String upit = "UPDATE RadniNalog "
                    + "SET DatumIzdavanja=?, DatumIzvrsenja=?, DatumPrometa=?, OpisGreske=?, Napomena=?, DodatneInformacije=?, KomentarIzvrsioca=?, "
                    + " SifraKlijenta=?, SifraRadnika=?, SifraUredjaja=?, SifraRadnje=? "
                    + "WHERE BROJNALOGA=? ";
            try {
                PreparedStatement ps = connection.prepareStatement(upit);

                ps.setDate(1, new Date(r.getDatumIzdavanja().getTime()));
                ps.setDate(2, new Date(r.getDatumIzvrsenja().getTime()));
                ps.setDate(3, new Date(r.getDatumPrometa().getTime()));
                ps.setString(4, r.getOpisGreske());
                ps.setString(5, r.getNapomena());
                ps.setString(6, r.getDodatneInformacije());
                ps.setString(7, r.getKomentarIzvrsioca());
                ps.setInt(8, r.getKlijent().getSifraKlijenta());
                ps.setInt(9, r.getRadnik().getSifraRadnika());
                ps.setInt(10, r.getUredjaj().getSifraUredjaja());
                ps.setInt(11, r.getRadnja().getSifraRadnje());

                ps.setInt(12, r.getBrojNaloga());

                return ps.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }

    public int delete1(Cena cena) throws SQLException {
        String upit = "DELETE FROM CENA "
                + "WHERE SIFRADOBRA=? AND DATUMOD=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setInt(1, cena.getVrstaDobra().getSifraDobra());
            ps.setDate(2, new Date(cena.getDatumOd().getTime()));

            return ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int update(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE " + object.getTableName() + " SET " + object.getUpdateClause() + " WHERE " + object.getUpdateWhereClause();
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int delete(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + object.getTableName() + " WHERE " + object.getDeleteWhereClause();
            System.out.println(query);
            return statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int exists(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT COUNT(1) FROM " + object.getTableName() + " WHERE " + object.getIdWhereClause();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getCountFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public int getId(DomainObject object) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT " + object.getIdColumnName() + " FROM " + object.getTableName() + " WHERE " + object.getIdWhereClause();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            return object.getIdFromResultSet(rs);
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
