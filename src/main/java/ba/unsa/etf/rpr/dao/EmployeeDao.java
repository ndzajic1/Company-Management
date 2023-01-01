package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeDao extends Dao<Employee>{

    List<Employee> getByFullName(String txt);
    List<Employee> getByHireDateRange(Date d1, Date d2);

}
