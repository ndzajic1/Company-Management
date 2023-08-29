package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao extends Dao<Employee>{

    List<Employee> searchByDepartment(Department dept) throws CompanyException;

    List<Employee> searchByName(String name) throws CompanyException;

    List<Employee> searchByJob(Job j) throws CompanyException;

    Employee getByIdWithoutDepartment(int id) throws CompanyException;
}
