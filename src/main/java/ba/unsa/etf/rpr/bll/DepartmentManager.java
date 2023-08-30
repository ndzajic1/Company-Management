package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

/**
 * Department business logic
 */
public class DepartmentManager {
    /**
     * Get department by its unique id.
     * @param id
     * @throws CompanyException
     */
    public Department getDeptById(int id) throws CompanyException {
        return DaoFactory.departmentDao().getById(id);
    }

    /**
     * Saves created department in db.
     * @param d
     * @throws CompanyException
     */
    public void addNewDept(Department d) throws CompanyException {
        try{
            validateDeptName(d);
            DaoFactory.departmentDao().add(d);
            Employee manager = d.getManager();
            manager.setDepartment(d);
            DaoFactory.employeeDao().update(manager);
        } catch(CompanyException e){
            throw new CompanyException(e.getMessage(), e);
        }
    }

    /**
     * Checks if there exists another department with the same name.
     * @param d
     * @throws CompanyException if its duplicate
     */
    private void validateDeptName(Department d) throws CompanyException{
        for(Department dept : getAllDepts()){
            if(dept.getName().equals(d.getName()))
                throw new CompanyException("Department with the same name already exists.");
        }
    }

    /**
     * Updates object d.
     * @param d
     * @throws CompanyException
     */
    public void updateDept(Department d) throws CompanyException {

        DaoFactory.departmentDao().update(d);
    }

    /**
     * Delete department with given id.
     * @param id
     * @throws CompanyException
     */
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

    /**
     * @return list of all departments
     * @throws CompanyException
     */
    public List<Department> getAllDepts() throws CompanyException {
        return DaoFactory.departmentDao().getAll();
    }
}
