package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for fetching Employee objects.
 */
public interface EmployeeDao extends Dao<Employee>{
    /**
     * Returns list of employees from given department.
     * @param dept
     * @throws CompanyException
     */
    List<Employee> searchByDepartment(Department dept) throws CompanyException;

    /**
     * Searches for employees by name, case insensitive search.
     * @param name
     * @throws CompanyException
     */
    List<Employee> searchByName(String name) throws CompanyException;

    /**
     * Returns list of employees with given job.
     * @param j
     * @throws CompanyException
     */
    List<Employee> searchByJob(Job j) throws CompanyException;

    /**
     * Gets desired Employee object by its id, with Department null, to break circular references between these two entiiies.
     * @param id
     * @throws CompanyException
     */
    Employee getByIdWithoutDepartment(int id) throws CompanyException;
}
