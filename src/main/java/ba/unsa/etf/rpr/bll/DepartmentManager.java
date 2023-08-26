package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;

import java.sql.SQLException;
import java.util.List;

public class DepartmentManager {

    public Department getDeptById(int id) throws SQLException {
        return DaoFactory.departmentDao().getById(id);
    }


    public void addNewDept(Department d) {
        try{
            validateDeptName(d);
            DaoFactory.departmentDao().add(d);
            Employee manager = d.getManager();
            DaoFactory.employeeDao().update(manager);
        } catch(SQLException e){
            // my except
        }
    }

    private void validateDeptName(Department d) throws SQLException {
        for(Department dept : getAllDepts()){
            if(dept.getName().equals(d.getName()))
                throw new RuntimeException("duplicate");
        }
    }


    public void updateDept(Department d) {

        DaoFactory.departmentDao().update(d);
    }


    public void deleteDept(int id) {
        try {
            DaoFactory.departmentDao().delete(id);
        } catch (RuntimeException e){
            // foreign key constraint
        }
    }


    public List<Department> getAllDepts() throws SQLException {
        return DaoFactory.departmentDao().getAll();
    }
}
