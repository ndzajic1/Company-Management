package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentManager {

    public Department getById(int id) throws SQLException {
        return DaoFactory.departmentDao().getById(id);
    }


    public void add(Department d) {
        try{
            validateDeptName(d);
            DaoFactory.departmentDao().add(d);
        } catch(SQLException e){
            // my except
        }
    }

    private void validateDeptName(Department d) throws SQLException {
        for(Department dept : getAll()){
            if(dept.getName().equals(d.getName()))
                throw new RuntimeException("duplicate");
        }
    }


    public void update(Department d) {
        DaoFactory.departmentDao().update(d);
    }


    public void delete(int id) {
        try {
            DaoFactory.departmentDao().delete(id);
        } catch (RuntimeException e){
            // foreign key constraint
        }
    }


    public List<Department> getAll() throws SQLException {
        return DaoFactory.departmentDao().getAll();
    }
}
