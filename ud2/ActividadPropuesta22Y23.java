package ud2;

import java.sql.*;

import static ud2.JDBCConnection.muestraErrorSQL;

public class ActividadPropuesta22Y23 {
    public static void main(String[] args) {
        String basedatos = "libro_ad", host = "localhost", port = "3306", parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad", pwd = "libro_ad";

        try (Connection con = DriverManager.getConnection(urlConnection, user, pwd);
             Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = s.executeQuery("select * from  clientes");
             ){
            rs.last(); // Llevamos el cursor a la ultima columna
            int columna = rs.getRow(); // Obtenermos el numero de la columna y print
            System.out.println(columna);

            // Imprimimos la actual antes de hacer rs.previous porque sino saltariamos esa última columna
            System.out.printf("\n%s %s %s", rs.getString("dni"),rs.getString("apellidos"),rs.getString("cp"));
            while (rs.previous()){
                System.out.printf("\n%s %s %s", rs.getString("dni"),rs.getString("apellidos"),rs.getString("cp"));
            }

        } catch (SQLException e){
            muestraErrorSQL(e);
        }





    }





}
