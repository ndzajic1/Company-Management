package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;


import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl()  {
        super("Employees");
    }

    public static EmployeeDaoSQLImpl getInstance() {
        if(instance != null)
            return instance;
        instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    @Override
    public Employee row2object(ResultSet rs) {
        try{
            Employee obj = new Employee(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("hire_date").toLocalDate(),
                    DaoFactory.departmentDao().getById(rs.getInt("department_id")), DaoFactory.jobDao().getById(rs.getInt("job_id")));
            obj.setUsername(rs.getString("username"));
            obj.setPasswordHash(rs.getString("password_hash"));
            return obj;
        } catch (SQLException | NoSuchAlgorithmException e) {
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
        row.put("username", obj.getUsername());
        row.put("password_hash", obj.getPasswordHash());
        return row;
    }
    @Override
    public List<Employee> searchByName(String name) {
        try{

            return super.executeQuery("select * from employees where lower(first_name) = '?%' " +
                            "or lower(last_name) = '?%' " +
                            "or lower(concat(first_name,' ',last_name)) = '?%'",
                    new Object[]{name.toLowerCase(), name.toLowerCase(), name.toLowerCase()});

        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Employee> searchByDepartment(Department dept) {
        try {
            return super.executeQuery("select * from Employees where department_id = ?", new Object[]{dept.getId()});
        } catch (Exception e) {
            throw new RuntimeException(); // my own
        }
    }

    @Override
    public List<Employee> searchByJob(Job j) {
        try {
            return super.executeQuery("select * from Employees where job_id = ?", new Object[]{j.getId()});
        } catch (Exception e) {
            throw new RuntimeException(); // my own
        }
    }


}
