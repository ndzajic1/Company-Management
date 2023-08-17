package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;

/**
 * Abstract class for shared methods of Dao classes.
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName)  {
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection(){
        if(connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
                String url = p.getProperty("db.connection_string");
                String username = p.getProperty("db.username");
                String password = p.getProperty("db.password");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected");
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

    public abstract T row2object(ResultSet rs) throws SQLException;
    public abstract Map<String, Object> object2row(T obj);

    public List<T> executeQuery(String query, Object[] params) throws SQLException {
        try{
            System.out.println(query);
            PreparedStatement ps = getConnection().prepareStatement(query);
            if(params != null){
                for(int i = 1; i <= params.length; i = i + 1){
                   ps.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = ps.executeQuery();
            ArrayList<T> list=new ArrayList<>();
            while(rs.next()){
                list.add(row2object(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public T executeQueryUnique(String query, Object[] params) throws SQLException {
        List<T> list = executeQuery(query, params);
        System.out.println("Result " + list.size());
        if(list != null && list.size() == 1){
            return list.get(0);
        }
        else{
            throw new RuntimeException(new Exception());// throw my exception
        }
    }



    @Override
    public T getById(int id) throws SQLException {
        return executeQueryUnique("select * from " + this.tableName + " where id = ?", new Object[]{id});
    }


    @Override
    public T add(T item) {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> cols = prepareInsertParts(row);
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append("(").append(cols.getKey()).append(") ");
        sb.append("values (").append(cols.getValue()).append(")");

        try{
            PreparedStatement ps=getConnection().prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            int c = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){
                if (entry.getKey().equals("id")) // ID will be generated, and will be assigned from generated key
                    continue;
                ps.setObject(c, entry.getValue());
                c = c + 1;
            }
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys(); // to get the ID from the inserted row
            rs.next();
            item.setId(rs.getInt(1));
            return item;

        } catch (SQLException e) {
            throw new RuntimeException(e); // my exception
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder cols = new StringBuilder(), quests = new StringBuilder();
        int c=0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            c = c + 1;
            cols.append(entry.getKey());
            quests.append("?");
            if(row.size() != c){
                cols.append(",");
                quests.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(cols.toString(), quests.toString());
    }

    @Override
    public T update(T item) {
        Map<String, Object> row = object2row(item);
        StringBuilder sb = new StringBuilder();
        String updateParts = prepareUpdateParts(row);
        sb.append("update ").append(tableName).append(" set ");
        sb.append(updateParts).append(" where id = ?");
        try{
            PreparedStatement ps = getConnection().prepareStatement(sb.toString());
            int c = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){
                c = c + 1;
                if(entry.getKey().equals("id"))
                    continue;
                ps.setObject(c, entry.getValue());
            }
            ps.setObject(c, item.getId());
            ps.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new RuntimeException(e); // my exception
        }
    }

    private  String prepareUpdateParts(Map<String, Object> row){
        StringBuilder cols = new StringBuilder();
        int c=0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            c = c + 1;
            if (entry.getKey().equals("id"))
                continue;
            cols.append(entry.getKey()).append("= ?");
            if (row.size() != c) {
                cols.append(",");
            }
            }
        return  cols.toString();
        }

    @Override
    public void delete(int id) {
        String query = "delete from " + tableName + " where id = ?";
        try{
            PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e); // my exception
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        return executeQuery("select * from " + tableName, null);
    }
}
