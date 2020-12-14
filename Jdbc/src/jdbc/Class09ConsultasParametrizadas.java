package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class09ConsultasParametrizadas {

    public static void main(String[] args) throws SQLException {
        //app para buscar por oficio y departamento
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca oficio");
        String oficio = teclado.nextLine();
        System.out.println("Introduzca número de departamento");
        String datonumero = teclado.nextLine();
        int deptno = Integer.parseInt(datonumero);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "select * from emp where upper(oficio) = upper(?) and dept_no =?";
        PreparedStatement pst = cn.prepareCall(consulta);
        //esblecemos los parámetros de izquierda a derecha empezando en 1
        pst.setString(1, oficio);
        pst.setInt(2, deptno);
        //ejecutamos la consulta sin pasar nada al método
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String datooficio = rs.getString("oficio");
            String dept = rs.getString("dept_no");
            System.out.println(apellido + "---" + oficio + "---" + dept);
        }
        rs.close();
        cn.close();
    }
}
