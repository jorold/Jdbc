package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleDriver;

public class ClassPrueba1 {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "select * from emp";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String oficio = rs.getString("oficio");
            System.out.println(apellido + "  " + oficio);
        }
        rs.close();
        cn.close();
    }
}
