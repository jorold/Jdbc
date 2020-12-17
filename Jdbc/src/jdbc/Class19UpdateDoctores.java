package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;
//<editor-fold defaultstate="collapsed" desc="procedimiento out">
/*create or replace procedure subidadoctor
(p_nombre hospital.nombre%type
,p_incremento int
,p_suma out int
,p_media out int)
as
begin
  update doctor set salario = salario + p_incremento
  where hospital_cod =
  (select hospital_cod from hospital where nombre = p_nombre);
  commit;
  select sum(salario), avg(salario) into p_suma, p_media
  from plantilla;
end;*/
//</editor-fold>
public class Class19UpdateDoctores {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca nombre hospital");
        String hospital = teclado.nextLine();
        System.out.println("Introduzca incremento salario");
        String datosalario = teclado.nextLine();
        int salario = Integer.parseInt(datosalario);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{ call subidadoctor (?, ?, ?, ?) }";
        CallableStatement cst = cn.prepareCall(consulta);
        cst.setString(1, hospital);
        cst.setInt(2, salario);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.registerOutParameter(4, java.sql.Types.INTEGER);
        cst.executeUpdate();
        int suma = cst.getInt(3);
        int media = cst.getInt(4);
        System.out.println("Hospital " + hospital + " Suma salarial " + suma + " Media salaria " + media);
        cn.close();
        System.out.println("Fin programa");
    }
}
