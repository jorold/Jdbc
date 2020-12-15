package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import oracle.jdbc.driver.OracleDriver;

public class Class12UpdateSalariosEmp {

    public static void main(String[] args) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection cn = DriverManager.getConnection(cadena, "system", "oracle");
        String response = "y";
        while (response.equals("y")) {
            System.out.println("Enter department");
            String department = keyboard.nextLine();
            System.out.println("Enter salary rise");
            String data = keyboard.nextLine();
            int wage = Integer.parseInt(data);
            String consultation = "update emp set salario = salario + ? where "
                    + "dept_no = (select dept_no from dept where upper(dnombre) "
                    + "= upper(?) )";
            PreparedStatement pst = cn.prepareStatement(consultation);
            pst.setInt(1, wage);
            pst.setString(2, department);
            int modified = pst.executeUpdate();
            System.out.println("Modified employees " + modified);
            String listing = "select apellido,salario from emp where dept_no ="
                    + "(select dept_no from dept where upper(dnombre) = upper(?))";
            pst = cn.prepareStatement(listing);
            pst.setString(1, department);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String sdname = rs.getString("apellido");
                String salary = rs.getString("salario");
                System.out.println(sdname + "---" + salary);
                System.out.println("-------------------------");
            }
            System.out.println("Do you want to modify another department? (y/n)");
            response = keyboard.nextLine();
        }
        System.out.println("End of program");
    }
}
