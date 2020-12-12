package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class ClassPrueba1 {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP alta en plantilla");
        System.out.println("---------------------");
        System.out.println("Introduzca número hospital");
        String datoh = teclado.nextLine();
        int hospital = Integer.parseInt(datoh);
        System.out.println("Introduzca número de sala");
        String datos = teclado.nextLine();
        int sala = Integer.parseInt(datos);
        System.out.println("Introduzca número de empleado");
        String datoe = teclado.nextLine();
        int empleado = Integer.parseInt(datoe);
        System.out.println("Introduzca apellido empleado");
        String apellido = teclado.nextLine();
        System.out.println("Introduzca función empleado");
        String funcion = teclado.nextLine();
        System.out.println("Introduzca turno");
        String turno = teclado.nextLine();
        System.out.println("Introduzca salario");
        String datosal = teclado.nextLine();
        int salario = Integer.parseInt(datosal);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        String consulta = "insert into plantilla values (" + hospital + ", " + sala + ", "
                + "" + empleado + ", '" + apellido + "',"
                + " '" + funcion + "', '" + turno + "', " + salario + ")";
        int insertados = st.executeUpdate(consulta);
        System.out.println("Empleados insertados: " + insertados);
        System.out.println("--------------------------");
        String consultadatos = "select * from plantilla";
        ResultSet rs = st.executeQuery(consultadatos);
        while (rs.next()) {
            String h = rs.getString("hospital_cod");
            String s = rs.getString("sala_cod");
            String e = rs.getString("empleado_no");
            String a = rs.getString("apellido");
            String f = rs.getString("funcion");
            String t = rs.getString("turno");
            String sa = rs.getString("salario");
            System.out.println(h + "---" + s + "---" + e + "---"
                    + a + "---" + f + "---" + t + "---" + sa);
        }
        rs.close();
        cn.close();
    }
}
