package jdbc;
//paquete de acceso de datos

import java.sql.*;
import oracle.jdbc.OracleDriver;

public class Class01ConsultaSelect {

    public static void main(String[] args) throws SQLException {
        //registrar el driver de acceso a oracle
        DriverManager.registerDriver(new OracleDriver());
        //necesitamos la cadena de conexión
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        //a partir del driver se recupera la conexion con
        //el metodo getConnection()
        //debemos indicar la cadena, usuario y password
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        //una vez que tenemos una conexión, necesitamos una consulta
        String consulta = "select * from emp";
        //para ejecutar sentencias utilizan objetos Statement
        //un Statement se crea a partir de una conexión
        Statement st = cn.createStatement();
        //dependiendo de la consulta nos devuelve datos o no
        //si la consulta es un select utilizamos el método
        //executeQuery() que nos devuelve un ResultSet
        ResultSet rs = st.executeQuery(consulta);
        //el método next() se mueve fila a fila y devuelve
        //un boolean false cuando no hay más filas
        //¿Existe alguna forma de que java haga algo mientras true?(while)
        //los cursores se leen con bucle while
        //nos posicionamos en una fila
        //rs.next();
        //rs.next();
        //rs.next();
        while (rs.next()) {
            //para recuperar un registro debemos indicar
            //el método getTipoDato()
            String apellido = rs.getString("apellido");
            System.out.println(apellido);
        }
        //una vez que hemos terminado de ejecutar todo
        //debemos liberar los recursos cerrando
        //el lector y la conexión
        rs.close();
        cn.close();
    }
}
