package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento almacenado">
/*create or replace package subirsalario
as
  procedure subida(p_incremento int, p_oficio emp.oficio%type);
end subirsalario;

create or replace package body subirsalario
as
  procedure subida(p_incremento int, p_oficio emp.oficio%type)
  as
  begin
    update emp set salario = salario + p_incremento where oficio = p_oficio;
  end;
end subirsalario;*/
//</editor-fold>
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.driver.OracleDriver;

public class Class16PaqueteSubirSalarios {

    public static void main(String[] args) throws SQLException {
        int incremento = -2;
        String oficio = "EMPLEADO";
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{call subirsalario.subida(?, ?)}";
        CallableStatement cst = cn.prepareCall(consulta);
        cst.setInt(1, incremento);
        cst.setString(2, oficio);
        cst.executeUpdate();
        String consultaselect = "select apellido, oficio, salario from emp";
        Statement st = cn.createStatement();
        ResultSet rs = cst.executeQuery(consultaselect);
        while (rs.next()) {
            String apellido = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            String salario = rs.getString("salario");
            System.out.println(apellido + "---" + ofi + "---" + salario);
        }
        rs.close();
        cn.close();
    }
}
