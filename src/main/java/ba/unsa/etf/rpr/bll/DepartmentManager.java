package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;

import java.sql.SQLException;
import java.util.List;

public class DepartmentManager implements Manageable<Department> {
    @Override
    public Department getById(int id) throws SQLException {
        return DaoFactory.departmentDao().getById(id);
    }

    @Override
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

    @Override
    public void update(Department d) {
        DaoFactory.departmentDao().update(d);
    }

    @Override
    public void delete(int id) {
        try {
            DaoFactory.departmentDao().delete(id);
        } catch (RuntimeException e){
            // foreign key constraint
        }
    }

    @Override
    public List<Department> getAll() throws SQLException {
        return DaoFactory.departmentDao().getAll();
    }
}
