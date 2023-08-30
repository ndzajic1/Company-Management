package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class JobDaoSQLImpl extends AbstractDao<Job> implements JobDao{

    private static JobDaoSQLImpl instance = null;

    private JobDaoSQLImpl() {
        super("Jobs");
    }

    /**
     * @return instance
     */
    public static JobDaoSQLImpl getInstance() {
        if(instance != null)
            return instance;
        instance = new JobDaoSQLImpl();
        return instance;
    }

    /**
     * Get Job from provided ResultSet object which is gotten by executing query.
     * @param rs
     * @return Job
     * @throws CompanyException
     */
    @Override
    public Job row2object(ResultSet rs) throws CompanyException {
        try{
            return new Job(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getDouble(4));
        } catch (SQLException e){
            throw new CompanyException(e.getMessage(),e); // my own
        }
    }

    /**
     * Convert object to suitable map for db operations.
     * @param j
     */
    @Override
    public Map<String, Object> object2row(Job j) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", j.getId());
        row.put("job_title", j.getTitle());
        row.put("min_salary", j.getMinSalary());
        row.put("max_salary", j.getMaxSalary());
        return row;
    }

    /**
     * Search for jobs by title.
     * @param txt
     * @return list of matched jobs
     * @throws CompanyException
     */
    @Override
    public List<Job> searchByTitle(String txt) throws CompanyException {
            return super.executeQuery("select * from Jobs where txt = '?%'", new Object[]{txt.toLowerCase()});
    }
}
