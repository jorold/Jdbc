package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.internal.OracleTypes;

//<editor-fold defaultstate="collapsed" desc="procedimiento cursor">
/* create or replace package pqtipos
is
    --TENEMOS QUE DECLARAR UN CURSOR CON
    --EL TIPO DE CONSULTA QUE DEVOLVERA
    type cursorempleados is ref cursor return emp%rowtype;
    type cursordepartamentos is ref cursor return dept%rowtype;
    type registroplantilla is record
    (apellido plantilla.apellido%type
    , nombre hospital.nombre%type
    , funcion plantilla.funcion%type
    , turno varchar2(30));
    type cursorplantillahosp is ref cursor return registroplantilla;
end pqtipos;

create or replace procedure datosplantillahospital
(consultaplantilla out pqtipos.cursorplantillahosp)
as
begin
  open consultaplantilla for
  select plantilla.apellido, hospital.nombre
  , plantilla.funcion
  , decode (turno, 'M', 'MAÑANA', 'T', 'TARDE', 'N', 'NOCHE')
as turno
  from plantilla
  inner join hospital
  on plantilla.hospital_cod = hospital.hospital_cod;
end;*/
//</editor-fold>
public class Class22CursorRegistroPlantilla {

    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String consultaproc = "{ call datosplantillahospital(?) }";
        CallableStatement cst = cn.prepareCall(consultaproc);
        cst.registerOutParameter(1, OracleTypes.CURSOR);
        cst.execute();
        ResultSet rs = (ResultSet) cst.getObject(1);
        while (rs.next()) {
            String ape = rs.getString("apellido");
            String nom = rs.getString("nombre");
            String fun = rs.getString("funcion");
            String turno = rs.getString("turno");
            System.out.println(ape + "---" + nom + "---" + fun + "---" + turno);
        }
        rs.close();
        cn.close();
    }
}
