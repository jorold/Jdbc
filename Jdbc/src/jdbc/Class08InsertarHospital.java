package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class08InsertarHospital {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Crear nuevo hospital");
        System.out.println("--------------------");
        System.out.println("Introducir código hospital");
        String datocod = teclado.nextLine();
        int codigohospital = Integer.parseInt(datocod);
        System.out.println("Introducir nombre hospital");
        String nombrehospital = teclado.nextLine();
        System.out.println("Introducir dirección");
        String direccion = teclado.nextLine();
        System.out.println("Introducir teléfono");
        String telefono = teclado.nextLine();
        System.out.println("Introducir número de camas");
        String datocam = teclado.nextLine();
        int camas = Integer.parseInt(datocam);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //insert into hospital values (int, varchar2, varchar2, varchar2, int)
        String consulta = "insert into hospital values (" + codigohospital + ","
                + " '" + nombrehospital + "', '" + direccion + "', "
                + "'" + telefono + "', " + camas + ")";
        int insertados = st.executeUpdate(consulta);
        System.out.println("Hospital insertado: " + insertados);
        System.out.println("---------------------------");
        String consultaselect = "select * from hospital";
        ResultSet rs = st.executeQuery(consultaselect);
        while (rs.next()) {
            String cod = rs.getString("hospital_cod");
            String nom = rs.getString("nombre");
            String dir = rs.getString("direccion");
            String tel = rs.getString("telefono");
            String cam = rs.getString("num_cama");
            System.out.println(cod + "---" + nom + "---" + dir + "---" + tel + "---" + cam);
        }
        rs.close();
        cn.close();
    }
}
