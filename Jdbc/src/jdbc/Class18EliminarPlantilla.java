package jdbc;

//<editor-fold defaultstate="collapsed" desc="procedimiento out">
/*create or replace procedure eliminarempleado
(p_apellido plantilla.apellido%type
,p_empleados  out int
,p_salarios  out int)
as
begin
  delete from plantilla where apellido = p_apellido;
  commit;
  select count(empleado_no), sum(salario) into p_empleados, p_salarios from plantilla;
end;*/
//</editor-fold>
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class18EliminarPlantilla {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca apellido");
        String apellido = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{ call eliminarempleado (?, ?, ?) }";
        CallableStatement cst = cn.prepareCall(consulta);
        cst.setString(1, apellido);
        cst.registerOutParameter(2, java.sql.Types.INTEGER);
        cst.registerOutParameter(3, java.sql.Types.INTEGER);
        cst.executeUpdate();
        int personas = cst.getInt(2);
        int suma = cst.getInt(3);
        System.out.println("Plantilla " + personas + " suma salarios " + suma);
        cn.close();
        System.out.println("Fim de programa");
    }
}
