package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Idable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Abstract class for shared methods of Dao classes.
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection=null;
    private String tableName;

    public AbstractDao(String tableName) throws SQLException, IOException {
        this.tableName=tableName;
        createConnection();
    }
    private static void createConnection() throws IOException, SQLException {
        if(connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                connection = DriverManager.getConnection(url, username, password);
            }
            catch (Exception e){
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    public Connection getConnection(){
        return connection;
    }
    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public T add(T item) {
        return null;
    }

    @Override
    public T update(T item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }
}
