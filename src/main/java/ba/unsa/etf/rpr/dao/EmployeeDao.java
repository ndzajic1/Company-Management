package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;
import ba.unsa.etf.rpr.Employee;
import ba.unsa.etf.rpr.Job;

import java.util.Date;
import java.util.List;

public interface EmployeeDao extends Dao<Employee>{

    List<Employee> searchByDepartment(Department dept);

    List<Employee> searchByName(String name);

    List<Employee> searchByJob(Job j);
}
