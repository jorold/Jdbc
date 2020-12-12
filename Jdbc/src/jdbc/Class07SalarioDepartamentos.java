package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class07SalarioDepartamentos {

    public static void main(String[] args) throws SQLException {
        //incrementamos el salario y el departamento
        //mostraremos los datos de los empleados incrementados
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introducir número de departamento");
        String datodept = teclado.nextLine();
        int deptno = Integer.parseInt(datodept);
        System.out.println("Introducir incremento");
        String datoincremento = teclado.nextLine();
        int incremento = Integer.parseInt(datoincremento);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        Statement st = cn.createStatement();
        //update emp set salario = salario + 1 where dept_no = 10
        String consulta = "update emp set salario = salario + " + incremento
                + " where dept_no = " + deptno;
        int afectados = st.executeUpdate(consulta);
        System.out.println("Empleados modificados: " + afectados);
        System.out.println("-----------------------------");
        //ahora leemos los registros
        //select apellido, salario from emp where dept_no = 10
        String consultaselect = "select apellido, salario from emp where dept_no=" + deptno;
        ResultSet rs = st.executeQuery(consultaselect);
        while (rs.next()) {
            String apellido = rs.getString("APELLIDO");
            String salario = rs.getString("SALARIO");
            System.out.println(apellido + "-------" + salario);
        }
        rs.close();
        cn.close();
        System.out.println("Fin de programa");
    }
}
