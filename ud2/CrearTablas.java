package ud2;

import java.sql.*;

import static ud2.JDBCConnection.muestraErrorSQL;

public class CrearTablas {
    public static void main(String[] args) {
        String basedatos = "libro_ad", host = "localhost", port = "3306", parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad", pwd = "libro_ad";
        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); Statement s = c.createStatement()) {
            // CREAR TABLA
            s.execute("drop table if exists clientes");//Se borra la tabla si ya existia(para que me permita continuar)
            s.execute("create table clientes(dni char(9) not null, apellidos varchar(32) not null, cp char(5), primary key(dni))");
            // INSERT
            int nFil = s.executeUpdate(
                    "insert into clientes(dni, apellidos,cp) values "
                            + "('78901234X','NADALES','44126'),"
                            + "('89012345E','HOJAS', null),"
                            + "('56789012B','SAMPER','29730'),"
                            + "('09876543K','LAMIQUIZ', null)");
            System.out.printf("%d Filas insertadas.\n", nFil);

            //SELECT CON RESULTSET
            try (ResultSet rs = s.executeQuery("select * from clientes")) {
                while (rs.next()) {
                    System.out.printf("%s %s %s\n", rs.getString("dni"),
                            rs.getString("apellidos"), rs.getString("cp"));
                }
            }
        } catch (SQLException ex) { muestraErrorSQL(ex); }


    }
}
