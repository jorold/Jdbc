package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.internal.OracleTypes;

//<editor-fold defaultstate="collapsed" desc="procedimiento cursor">
/*create or replace package pqtipos
is
type cursorempleados is ref cursor return emp%rowtype;
end pqtipos;

create or replace procedure selectempleados
(consulta out pqtipos.cursorempleados)
as
begin
  open consulta for
  select * from emp;
end;*/
//</editor-fold>
public class Class20CursoresProcedimientos {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consulta = "{ call selectempleados (?) }";
        //los cursores de salida son propios de oracle
        //por los que no están dentro de los tipos de sql
        //para definir del cursor de oracle se necesita
        //la numeración OracleTypes.CURSOR
        CallableStatement cst = cn.prepareCall(consulta);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        //nuestro procedimiento no ejecuta ninguna consulta
        //que no sea el cursor (no tiene update, delete...)
        //si sólo queremos ejecutar el cursor se
        //utiliza el método execute()
        cst.execute();
        //una vez ejecutado tenemos el cursor cargado dentro del parámetro 1
        //no existe un método para recuperar el cursor de forma
        //explícita getCursor(1)
        //debemos utilizar el método genérico getObject(1)
        //y convertir el cursor a un tipo de dato para recorrer
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ape = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            String dept = rs.getString("dept_no");
            System.out.println(ape + "---" + ofi + "---" + dept);
        }
        rs.close();
        cn.close();
    }
}
