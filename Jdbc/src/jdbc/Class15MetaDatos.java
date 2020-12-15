package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleDriver;

public class Class15MetaDatos {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("select * from emp");
        ResultSetMetaData metadata = rs.getMetaData();
        int numerocolumnas = metadata.getColumnCount();
        System.out.println("todas las columnas de emp");
        for (int i = 1; i <= numerocolumnas; i++) {
            String nombrecolumna = metadata.getColumnName(i);
            String tipo = metadata.getColumnTypeName(i);
            System.out.println(i + ".-" + nombrecolumna + "---" + tipo);
        }
        rs.close();
        cn.close();
    }
}
