package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.List;

public class DepartmentManager {

    public Department getDeptById(int id) throws CompanyException {
        return DaoFactory.departmentDao().getById(id);
    }


    public void addNewDept(Department d) throws CompanyException {
        try{
            validateDeptName(d);
            DaoFactory.departmentDao().add(d);
            Employee manager = d.getManager();
            manager.setDepartment(d);
            DaoFactory.employeeDao().update(manager);
        } catch(CompanyException | SQLException e){
            throw new CompanyException(e.getMessage(), e);
        }
    }

    private void validateDeptName(Department d) throws CompanyException, SQLException {
        for(Department dept : getAllDepts()){
            if(dept.getName().equals(d.getName()))
                throw new CompanyException("Department with the same name already exists.");
        }
    }


    public void updateDept(Department d) throws CompanyException {

        DaoFactory.departmentDao().update(d);
    }


    public void deleteDept(int id) throws CompanyException {
        try {
            DaoFactory.departmentDao().delete(id);
        } catch  (CompanyException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new CompanyException("Move employees from this department to another one first!");
            }
            throw e;
        }
    }


    public List<Department> getAllDepts() throws CompanyException {
        return DaoFactory.departmentDao().getAll();
    }
}
