package ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static boolean muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
        return false;
    }
    public static void main(String[] args) {
        String basedatos = "libro_ad", host = "localhost", port = "3306", parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad", pwd = "libro_ad";
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
            System.out.printf("Conexión establecida con %s, BD %s, servidor %s versión %d.\n",
                    urlConnection, c.getCatalog(), c.getMetaData().getDatabaseProductName(),
                    c.getMetaData().getDatabaseMajorVersion());
        } catch (SQLException ex) { muestraErrorSQL(ex); }
    }
}