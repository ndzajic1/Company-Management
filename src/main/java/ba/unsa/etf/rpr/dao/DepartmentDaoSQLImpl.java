package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DepartmentDaoSQLImpl extends AbstractDao<Department> implements DepartmentDao{

    private static DepartmentDaoSQLImpl instance = null;

    private DepartmentDaoSQLImpl() {
        super("Departments");
    }

    public static DepartmentDaoSQLImpl getInstance()  {
        if(instance == null)
            instance = new DepartmentDaoSQLImpl();
        return instance;
    }

    @Override
    public Department row2object(ResultSet rs) {
       try{
           Department d = new Department();
           d.setId(rs.getInt(1));
           d.setName(rs.getString(2));
           d.setLocation(rs.getString(3));
           d.setManager(DaoFactory.employeeDao().getByIdWithoutDepartment(rs.getInt(4)));
           d.getManager().setDepartment(d);
           return d;
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
            return super.executeQuery("select * from Departments where lower(name) = '?%'", new Object[]{name.toLowerCase()});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
