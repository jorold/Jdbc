package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class11EliminarEnfermo {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca número inscripción");
        String datonumero = teclado.nextLine();
        int numero = Integer.parseInt(datonumero);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        //delete from enfermo2 where inscripcion = 18004
        String consultaeliminar = "delete from enfermo2 where inscripcion = ?";
        PreparedStatement pst = cn.prepareStatement(consultaeliminar);
        pst.setInt(1, numero);
        int eliminados = pst.executeUpdate();
        System.out.println("Enfermos eliminados: " + eliminados);
        String consultaselect = "select * from enfermo2";
        pst = cn.prepareStatement(consultaselect);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String inscripcion = rs.getString("inscripcion");
            System.out.println(apellido + "---" + inscripcion);
        }
        System.out.println("¿Desea eliminar otro enfermo del listado? (Y/N)");
        String respuesta = teclado.nextLine();

        while (respuesta.equals("y")) {
            System.out.println("Introduzca número de inscripción");
            String dato = teclado.nextLine();
            int num = Integer.parseInt(dato);
            String consulta = "delete from enfermo2 where inscripcion = ?";
            pst = cn.prepareStatement(consulta);
            pst.setInt(1, num);
            int borrados = pst.executeUpdate();
            System.out.println("Enfermos eliminados: " + borrados);
            System.out.println("¿Desea eliminar otro enfermo del listado? (Y/N)");
            respuesta = teclado.nextLine();

        }
        if (respuesta.equals("n")) {
            System.out.println("Fin de Programa");
        }
        rs.close();
        cn.close();
    }
}
