package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class10ModificarSalarioPlantilla {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca función");
        String funcion = teclado.nextLine();
        System.out.println("Introduzca incremento salario");
        String dato = teclado.nextLine();
        int incremento = Integer.parseInt(dato);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        //update plantilla set salario = salario + incremento where funcion = enfermero
        String consulta = "update plantilla set salario = salario + ? where upper(funcion) =upper(?)";
        PreparedStatement pst = cn.prepareStatement(consulta);
        pst.setInt(1, incremento);
        pst.setString(2, funcion);
        int insertados = pst.executeUpdate();
        System.out.println("Salarios modificados: " + insertados);
        String consultaselect = "Select * from plantilla where upper(funcion) = upper(?)";
        //reutilizamos el pst
        pst = cn.prepareStatement(consultaselect);
        pst.setString(1, funcion);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String fun = rs.getString("funcion");
            String salario = rs.getString("salario");
            System.out.println(apellido + "---" + fun + "---" + salario);
        }
        rs.close();
        cn.close();
        System.out.println("Fin de Programa");
    }
}
