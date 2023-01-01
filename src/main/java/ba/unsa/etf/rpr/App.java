package ba.unsa.etf.rpr;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {

        Connection con= DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_rpr_db",
         "freedb_ndzajic1","");
        Statement st=con.createStatement();
        st.executeUpdate(
                "INSERT INTO `freedb_rpr_db`.`Jobs` (`id`, `job_title`, `min_salary`, `max_salary`) VALUES ('7', 'Public Accountant', '4200', '9000');"
        );
       ResultSet rs= st.executeQuery(
                "select * from Jobs"
        );

       while(rs.next()){
           System.out.print(rs.getInt(1)+" "+
           rs.getString(2)+" \n");
       }


    }
}