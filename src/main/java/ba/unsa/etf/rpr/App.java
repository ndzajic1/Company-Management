package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class App extends Application {


   public static void main(String[] args) throws SQLException, IOException {
        launch(args);

       /* Properties p = new Properties();
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

        */
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("Company");
        stage.setScene(new Scene(p, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        //stage.getIcons()
        stage.setResizable(false);
        stage.show();
    }
}