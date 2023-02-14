package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManager implements Manageable<Employee>{
    @Override
    public Employee getById(int id) throws SQLException {
        return DaoFactory.employeeDao().getById(id);
    }

    @Override
    public void add(Employee e) {
        DaoFactory.employeeDao().add(e);
    }

    @Override
    public void update(Employee e) {
        DaoFactory.employeeDao().update(e);
    }

    @Override
    public void delete(int id) {
        DaoFactory.employeeDao().delete(id);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return DaoFactory.employeeDao().getAll();
    }
}
