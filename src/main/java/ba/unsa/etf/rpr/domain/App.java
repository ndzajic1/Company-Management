package ba.unsa.etf.rpr.domain;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentDaoSQLImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class App { //extends Application {


    public static void main(String[] args) throws SQLException, IOException {
        // launch(args);
        System.out.println("###");
        List<Object> l = Collections.singletonList(DaoFactory.employeeDao().getAll());

        for (Object e : l) {
            System.out.println(e.toString());
        }
        System.out.println("###");
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


    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Company");
        stage.setScene(new Scene(p, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image(getClass().getResource("/img/logo.jpeg").toExternalForm()));
        stage.setResizable(false);
        stage.show();
    } */
    }
}