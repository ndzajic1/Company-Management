package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Idable;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public abstract T row2object(ResultSet rs);
    public abstract Map<String, Object> object2row(T obj);

    public List<T> executeQuery(String query, Object[] params) throws SQLException {
        try{
            PreparedStatement ps=getConnection().prepareStatement(query);
            if(params != null){
                for(int i = 1; i <= params.length; i = i+1){
                   ps.setObject(i, params[i-1]);
                }
            }
            ResultSet rs=ps.executeQuery();
            ArrayList<T> list=new ArrayList<>();
            while(rs.next()){
                list.add(row2object(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    @Override
    public T getById(int id) {
        return executeQueryUnique("select * from " + this.tableName + " where id = ?", new Object[]{id});
    }

    private T executeQueryUnique(String s, Object[] objects) {
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
    public List<T> getAll() throws SQLException {
        return executeQuery("select * from "+tableName, null);
    }
}
