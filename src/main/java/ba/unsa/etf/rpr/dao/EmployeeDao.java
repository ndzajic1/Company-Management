package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;

import java.util.List;

public interface EmployeeDao extends Dao<Employee>{

    List<Employee> searchByDepartment(Department dept);

    List<Employee> searchByName(String name);

    List<Employee> searchByJob(Job j);
}
