package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Accessing Department objects from database.
 */
public class DepartmentDaoSQLImpl extends AbstractDao<Department> implements DepartmentDao{

    private static DepartmentDaoSQLImpl instance = null;

    private DepartmentDaoSQLImpl() {
        super("Departments");
    }

    /**
     * @return instance
     */
    public static DepartmentDaoSQLImpl getInstance()  {
        if(instance == null)
            instance = new DepartmentDaoSQLImpl();
        return instance;
    }

    /**
     * Get object from ResultSet obtained by executing query.
     * @param rs
     * @return Department
     * @throws CompanyException
     */
    @Override
    public Department row2object(ResultSet rs) throws CompanyException {
       try{
           Department d = new Department();
           d.setId(rs.getInt(1));
           d.setName(rs.getString(2));
           d.setLocation(rs.getString(3));
           d.setManager(DaoFactory.employeeDao().getByIdWithoutDepartment(rs.getInt(4)));
           d.getManager().setDepartment(d);
           return d;
       } catch (SQLException e) {
           throw new CompanyException(e.getMessage(),e);
       }
    }

    /**
     * Convert object to suitable map for db operations.
     * @param d
     * @return
     */
    @Override
    public Map<String, Object> object2row(Department d) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", d.getId());
        row.put("department_name", d.getName());
        row.put("location", d.getLocation());
        row.put("manager_id", d.getManager().getId());
        return row;
    }

    /**
     * Search for departments by name.
     * @param name
     * @return list
     * @throws CompanyException
     */
    @Override
    public List<Department> searchByName(String name) throws CompanyException {
            return super.executeQuery("select * from Departments where lower(name) = '?%'", new Object[]{name.toLowerCase()});

    }
}
