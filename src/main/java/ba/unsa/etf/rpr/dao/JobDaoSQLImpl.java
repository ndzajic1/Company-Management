package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Job;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JobDaoSQLImpl extends AbstractDao<Job> implements JobDao{

    private static JobDaoSQLImpl instance = null;

    private JobDaoSQLImpl() throws SQLException, IOException {
        super("Jobs");
    }

    public static JobDaoSQLImpl getInstance() throws SQLException, IOException {
        if(instance != null)
            return instance;
        instance = new JobDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance != null)
            instance = null;
    }
    @Override
    public Job row2object(ResultSet rs) {
        try{
            return new Job(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getDouble(4));
        } catch (SQLException e){
            throw new RuntimeException(); // my own
        }
    }

    @Override
    public Map<String, Object> object2row(Job j) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", j.getId());
        row.put("job_title", j.getTitle());
        row.put("min_salary", j.getMinSalary());
        row.put("max_salary", j.getMaxSalary());
        return row;
    }

    @Override
    public List<Job> searchByTitle(String txt) {
        try {
            return super.executeQuery("select * from Jobs where txt like '?%'", new Object[]{txt});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
