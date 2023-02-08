package ba.unsa.etf.rpr;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {

        Connection con = null;
        Statement st=con.createStatement();
       st.executeUpdate(
                "INSERT INTO `freedb_rpr_db`.`Employees` (id, first_name, last_name, hire_date,"+
                        "department_id, job_id, salary) VALUES "+
                "(10, 'Ismael', 'Sciarra', STR_TO_DATE('01 07 2005', '%d %m %Y'),"+
               "2,5,7800);"
        );
       ResultSet rs= st.executeQuery(
                "select * from Employees"
        );

       while(rs.next()){
           System.out.print(rs.getInt(1)+" "+
           rs.getString(2)+" "+ rs.getString(3)+" \n");
       }


    }
}