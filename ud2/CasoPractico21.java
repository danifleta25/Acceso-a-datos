package ud2;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static ud2.JDBCConnection.muestraErrorSQL;

public class CasoPractico21 {
    public static void main(String[] args) {
        String basedatos = "libro_ad", host = "localhost", port = "3306", parAdic = "";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
        String user = "libro_ad", pwd = "libro_ad";

        try (Connection con = DriverManager.getConnection(urlConnection, user, pwd);
             Statement st = con.createStatement()){
            st.execute("update clientes set apellidos = 'ROJAS' where dni = '89012345E'");
            st.execute("delete from clientes where dni = '09876543K'");


        } catch (SQLException e){
            muestraErrorSQL(e);
        }




    }


}
