package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManager{
    public Employee getById(int id) throws SQLException {
        return DaoFactory.employeeDao().getById(id);
    }

    public void add(Employee e) {
        DaoFactory.employeeDao().add(e);
    }


    public void update(Employee e) {
        DaoFactory.employeeDao().update(e);
    }


    public void delete(int id) {
        DaoFactory.employeeDao().delete(id);
    }


    public List<Employee> getAll() throws SQLException {
        return DaoFactory.employeeDao().getAll();
    }
}
