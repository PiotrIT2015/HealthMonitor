package admin.pp.healthmonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/// Klasa impementujca funkcjonalno bazy danych MySQL
/** W klasie tej znajduj si implementacje funkcji
 * bazy danych (pobieranie wartoci, aktualizowanie gosw) */

public class MySQL {

    /// Funkcja poczenia z baz danych
    /** Funkcja ta zapewnia czno pomidzy serwerem a baz danych MySQL. Podawane s tutaj dane dostpowe do bazy oraz jej adres. */

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + databaseName +
                "?useUnicode=true" +
                "&characterEncoding=utf-8" +
                "&user=" + userName +
                "&password=" + password);
        System.out.println("Poczono do bazy " + databaseName);
        return conn;
    }

    Connection databaseConnection;
    String userName = "root",
            password = "pass",
            serverName = "localhost",
            portNumber = "3306",
            databaseName = "srir";


/// Funkcja wywietlania treci uchway
    /**Funkcja ta odpowiada za pobranie treci uchway z bazy danych i zwrcenie jej w postaci Stringa */
    public String viewTable() throws SQLException {
        String query = "select tresc from sr where id=1";
        String tresc = null;

        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Dopki zbir wynikw posiada jakie dane to wypisuj
            while(rs.next())
                tresc = rs.getString("tresc");



        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
        return tresc;
    }

    public void insert(String val) {
        String CONTENT1 = "sid";
        String query = "insert into sr+ (" + CONTENT1 + ") values(" + val + ")";
        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int viewhigh() throws SQLException {
        String query = "select * from sr where id=1";
        int high=0;
        int width=0;

        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Dopki zbir wynikw posiada jakie dane to wypisuj
            while(rs.next()){
                high =rs.getInt("high");
                width =rs.getInt("width");
            }
            System.out.println(high+width);



        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
        return (high);
    }

    public int viewwidth() throws SQLException {
        String query = "select * from sr where id=1";
        int high=0;
        int width=0;

        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Dopki zbir wynikw posiada jakie dane to wypisuj
            while(rs.next()){
                high =rs.getInt("high");
                width =rs.getInt("width");
            }
            System.out.println(high+width);



        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
        return (width);
    }

    public void delete(String val) {
        String CONTENT1 = "sid";
        String query = "delete from sr+ (" + CONTENT1 + ") values(" + val + ")";
        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);


            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

/// Funkcja wywietlania liczby gosw
    /**Funkcja ta odpowiada za pobranie iloci poszczeglnych gosw z bazy danych i zwrcenie ich w postaci Stringa */
    public String viewVotes() throws SQLException {
        String query = "select za, przeciw, wstrz from sr where id=1";
        int za=0;
        int przeciw=0;
        String wstrz =null;

        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Dopki zbir wynikw posiada jakie dane to wypisuj
            while(rs.next()){
                za =rs.getInt("za");
                przeciw =rs.getInt("przeciw");
                wstrz =rs.getString("wstrz");
            }
            System.out.println(za +"   "+ przeciw+ "   " + wstrz);



        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
        return ("WYNIKI: Glosow za: " +za + " Glosow przeciw: " + przeciw + " Wstrzymalo sie od glosow: " + wstrz);
    }

    /// Funkcja gosowania za uchwa
    /**Funkcja ta odpowiada za zaktualizowanie komrki "za" w bazie danych (odnoszce si do uchway, nad ktr trwa gosowanie) */
    public void updateZa() throws SQLException {
        String query = "UPDATE sr SET za=za+1";
        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            int rows = stmt.executeUpdate(query);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
    }
/// Funkcja wstrzymania si od gosu
    /**Funkcja ta odpowiada za zaktualizowanie komrki "Brak zdania" w bazie danych (odnoszce si do uchway, nad ktr trwa gosowanie) */
    public void updateBz() throws SQLException {
        String query = "UPDATE sr SET wstrz=wstrz+1";
        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            int rows = stmt.executeUpdate(query);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
    }
/// Funkcja gosowania przeciw uchwale
    /**Funkcja ta odpowiada za zaktualizowanie komrki "przeciw" w bazie danych (odnoszce si do uchway, nad ktr trwa gosowanie) */
    public void updatePrzeciw() throws SQLException {
        String query = "UPDATE sr SET przeciw=przeciw+1";
        Statement stmt = null;
        try {
            stmt = (Statement) databaseConnection.createStatement();
            int rows = stmt.executeUpdate(query);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // Zamknij obiekt Statement, aby natychmiast zwolni jego pami
            if (stmt != null) { stmt.close(); }
        }
    }

}


