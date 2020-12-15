package jdbc;
//<editor-fold defaultstate="collapsed" desc="procedimiento almacenado">
/*create or replace procedure insertardepartamento
(p_dept dept.dept_no%type,
p_dnombre dept.dnombre%type,
p_loc dept.loc%type)
as
begin
  insert into dept values(p_dept, p_dnombre, p_loc);
  commit;
end; */
//</editor-fold>
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class14ProcedimientoInsertarDepartamento {

    public static void main(String[] args) throws SQLException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Creación de nuevo departamento");
        System.out.println("Inserte número departamento");
        String datonumero = teclado.nextLine();
        int numerodep = Integer.parseInt(datonumero);
        System.out.println("Inserte nombre departamento");
        String nombredep = teclado.nextLine();
        System.out.println("Inserte localidad del departamento");
        String localidad = teclado.nextLine();
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{call insertardepartamento(?, ?, ?)}";
        CallableStatement cst = cn.prepareCall(consulta);
        cst.setInt(1, numerodep);
        cst.setString(2, nombredep);
        cst.setString(3, localidad);
        cst.executeUpdate();
        String consultaselect = "select * from dept";
        Statement st = cn.createStatement();
        ResultSet rs = cst.executeQuery(consultaselect);
        while (rs.next()) {
            String d = rs.getString("dept_no");
            String dn = rs.getString("dnombre");
            String l = rs.getString("loc");
            System.out.println(d + "---" + dn + "---" + l);
        }
        rs.close();
        cn.close();
    }
}
