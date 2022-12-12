package ba.unsa.etf.rpr;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {

        Connection con= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7584126",
         "sql7584126","");
        Statement st=con.createStatement();

       ResultSet rs= st.executeQuery(
                "select * from Employees"
        );
       while(rs.next()){
           System.out.print(rs.getInt(1)+" "+
           rs.getString(2)+" \n");
       }
    }
}