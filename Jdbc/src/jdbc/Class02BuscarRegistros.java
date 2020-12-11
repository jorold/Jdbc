package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleDriver;

public class Class02BuscarRegistros {

    public static void main(String[] args) throws SQLException {
        //vamos a mostras los doctores de un hospital
        //apellido y especialidad
        //pedir al ususario el código de hospital
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca código de hospital: ");
        String dato = teclado.nextLine();
        int hospitalcod = Integer.parseInt(dato);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        String consulta = "select apellido, especialidad from doctor where hospital_cod=" + hospitalcod;
        ResultSet rs = st.executeQuery(consulta);
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String especialidad = rs.getString("especialidad");
            System.out.println(apellido + "---" + especialidad);
        }
        rs.close();
        cn.close();
    }
}
