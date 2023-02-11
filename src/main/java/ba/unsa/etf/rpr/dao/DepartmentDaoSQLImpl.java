package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentDaoSQLImpl extends AbstractDao<Department> implements DepartmentDao{

    private static DepartmentDaoSQLImpl instance=null;

    private DepartmentDaoSQLImpl() throws SQLException, IOException {
        super("Departments");
    }

    public static DepartmentDaoSQLImpl getInstance() throws SQLException, IOException {
        if(instance == null)
            instance = new DepartmentDaoSQLImpl();
        return instance;
    }

    @Override
    public Department row2object(ResultSet rs) {
       try{
           return new Department(rs.getInt(1), rs.getString(2),
                   rs.getString(3), DaoFactory.employeeDao().getById("employee_id"));
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public Map<String, Object> object2row(Department d) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", d.getId());
        row.put("department_name", d.getName());
        row.put("location", d.getLocation());
        row.put("manager_id", d.getManager().getId());
        return row;
    }

    @Override
    public List<Department> searchByName(String name) {
        try {
            return super.executeQuery("select * from Departments where name like '?%'", new Object[]{name});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
