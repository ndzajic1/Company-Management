package ba.unsa.etf.rpr;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        Properties p = new Properties();
        p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
        String url = p.getProperty("db.connection_string");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st=con.createStatement();
       ResultSet rs= st.executeQuery(
                "select * from Employees"
        );

       while(rs.next()){
           System.out.print(rs.getInt(1)+" "+
           rs.getString(2)+" "+ rs.getString(3)+" \n");
       }
    }
}