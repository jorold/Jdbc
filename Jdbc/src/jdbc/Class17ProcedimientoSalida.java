package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;
//<editor-fold defaultstate="collapsed" desc="procedimiento out">
/* create or replace procedure updatesalarioempleado
(p_empno emp.emp_no%type
, p_salario emp.salario%type
, p_comision emp.comision%type
, p_total out int)
as
begin
  update emp set salario = salario + p_salario
  , comision = comision + p_comision
  where emp_no = p_empno;
  commit;
  select salario + comision into p_total
  from emp
  where emp_no = p_empno;
end;*/
//</editor-fold>

public class Class17ProcedimientoSalida {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("APP Procedimiento de Salida");
        System.out.println("---------------------------");
        System.out.println("Número de empleado");
        String datoemp = teclado.nextLine();
        int empno = Integer.parseInt(datoemp);
        System.out.println("Incremento salario");
        String datosalario = teclado.nextLine();
        int salario = Integer.parseInt(datosalario);
        System.out.println("Incremento comisión");
        String datocomision = teclado.nextLine();
        int comision = Integer.parseInt(datocomision);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc = "{ call updatesalarioempleado (?, ?, ?, ?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.setInt(1, empno);
        cst.setInt(2, salario);
        cst.setInt(3, comision);
        //para indicar los tipos tenemos una enumeracion que
        //devuelve el tipo de dato por nombre
        //una enumeracion nos ofrece textos que significan números
        //ejempo: 1.norte 2.sur 3.oeste 4.este
        //.setDireccion(1) eso es que la direccion es el norte (1)
        //la numeración de tipos es java.sql.Types.tipos
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        //ahora se ejecuta el procedimiento
        cst.executeUpdate();
        //recuperamos el valor del parámetro de salida
        int total = cst.getInt(4);
        System.out.println("La suma del salario y comisión de "
                + empno + " es " + total);
        cn.close();
        System.out.println("Fin de Programa");
    }
}
