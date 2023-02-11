package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;
import ba.unsa.etf.rpr.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl() throws SQLException, IOException {
        super("Employees");
    }

    public static EmployeeDaoSQLImpl getInstance() throws SQLException, IOException {
        if(instance != null)
            return instance;
        instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    @Override
    public Employee row2object(ResultSet rs) {
        try{
            return new Employee(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("hire_date"),
                    DaoFactory.departmentDao().getById("department_id"), DaoFactory.jobDao().getById("job_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> object2row(Employee obj) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", obj.getId());
        row.put("first_name", obj.getFirstName());
        row.put("last_name", obj.getLastName());
        row.put("hire_date", obj.getHireDate());
        row.put("department_id", obj.getDepartment().getId());
        row.put("job_id", obj.getJob().getId());
        return row;
    }
    @Override
    public List<Employee> searchByName(String name) {
        try{
            List<Employee> list = super.executeQuery("select * from employees where first_name = '?%' " +
                            "or last_name like '?%'",
                    new Object[]{name, name});
            list.addAll(super.executeQuery(
                    "select * from employees where concat(first_name,' ',last_name) like '?%'", new Object[]{name}));
            return list;

        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Employee> searchByDepartment(Department dept) {
        return null;
    }


}
