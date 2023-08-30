package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.*;
import java.util.*;

/**
 * Abstract class for shared methods of Dao classes.
 * @param <T>  type implementing Idable
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private static Connection connection = null;
    private String tableName;

    /**
     * Constructor to create connection to db, with respect to table.
     * @param tableName
     */
    public AbstractDao(String tableName)  {
        this.tableName = tableName;
        createConnection();
    }

    /**
     * Creating connection to the database.
     */
    private static void createConnection(){
        if(connection == null) {
            try {
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("application.properties.sample").openStream());
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

    /**
     *
     *
     * @return instance of Connection
     */
    public Connection getConnection(){
        return connection;
    }

    /**
     * Converts db result set to object.
     * @param rs
     * @return converted object <T>
     * @throws CompanyException
     */
    public abstract T row2object(ResultSet rs) throws CompanyException;

    /**
     * Convert object to map key = columnName, value = columnValue, suitable format for db.
     * @param obj
     * @return map of (colName, colValue) pairs
     */
    public abstract Map<String, Object> object2row(T obj);

    /**
     * Get objects resulted in executing query provided with params.
     * @param query
     * @param params objects
     * @return list of objects as query result
     * @throws CompanyException
     */
    public List<T> executeQuery(String query, Object[] params) throws CompanyException {
        try{
            PreparedStatement ps = getConnection().prepareStatement(query);

            if(params != null){

                for(int i = 1; i <= params.length; i = i + 1){
                    if(params[i-1] instanceof String)
                        ps.setString(i, (String)params[i-1]);
                    else
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
            throw new CompanyException(e.getMessage(), e);
        }

    }

    /**
     * Executes query and returns one object as result.
     * @param query
     * @param params
     * @return list of result objects
     * @throws CompanyException
     */
    public T executeQueryUnique(String query, Object[] params) throws CompanyException {
        List<T> list = executeQuery(query, params);
        System.out.println(query + " " + params[0].toString());
        if(list != null && list.size() == 1){
            return list.get(0);
        }
        else{
            throw new CompanyException("No results");// throw my exception
        }
    }


    /**
     * Get object by its primary key.
     * @param id
     * @return
     * @throws CompanyException
     */
    @Override
    public T getById(int id) throws CompanyException {
        return executeQueryUnique("select * from " + this.tableName + " where id = ?", new Object[]{id});
    }

    /**
     * Saves item in database.
     * @param item object to be saved in db
     * @return saved item
     * @throws CompanyException
     */
    @Override
    public T add(T item) throws CompanyException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> cols = prepareInsertParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(cols.getKey()).append(") ");
        builder.append("VALUES (").append(cols.getValue()).append(")");
        System.out.println("QUERY:  "    + builder.toString());
        try{
            PreparedStatement ps=getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int c = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){

                System.out.println("PARAMS: " + entry.getKey() + " : " + entry.getValue().toString());
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
            throw new CompanyException(e.getMessage(), e); // my exception
        }
    }

    /**
     * Returns suitable (colName, colValue) map for executing insert statement to database.
     * @param row
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder cols = new StringBuilder(), quests = new StringBuilder();
        int c = 0;
        for(Map.Entry<String, Object> entry : row.entrySet()){
            c = c + 1;
            if(entry.getKey().equals("id"))
                continue;
            cols.append(entry.getKey());
            quests.append("?");
            if(row.size() != c){
                cols.append(",");
                quests.append(",");
            }

        }
        return new AbstractMap.SimpleEntry<>(cols.toString(), quests.toString());
    }

    /**
     * Updates object in databse.
     * @param item - object to be updated, id must be populated
     * @throws CompanyException
     */
    @Override
    public T update(T item) throws CompanyException {
        Map<String, Object> row = object2row(item);
        StringBuilder sb = new StringBuilder();
        String updateParts = prepareUpdateParts(row);
        sb.append("update ").append(tableName).append(" set ");
        sb.append(updateParts).append(" where id = ?");
        try{
            PreparedStatement ps = getConnection().prepareStatement(sb.toString());
            int c = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){

                if(entry.getKey().equals("id"))
                    continue;
                ps.setObject(c, entry.getValue());
                c = c + 1;
            }
            ps.setObject(c, item.getId());
            ps.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new CompanyException(e.getMessage(), e); // my exception
        }
    }

    /**
     * Returns map of (colName, colValue) pairs to incorporate into update statement to be executed in database.
     * @param row in descriped form
     */
    private  String prepareUpdateParts(Map<String, Object> row){
        StringBuilder cols = new StringBuilder();
        int c = 0;
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

    /**
     * Deletes item from database.
     * @param id - primary key of entity
     * @throws CompanyException
     */
    @Override
    public void delete(int id) throws CompanyException {
        String query = "delete from " + tableName + " where id = ?";
        try{
            PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new CompanyException(e.getMessage(),e); // my exception
        }
    }

    /**
     * Return all objects stored in database.
     * @throws CompanyException
     */
    @Override
    public List<T> getAll() throws CompanyException {
        return executeQuery("select * from " + tableName, null);
    }
}
