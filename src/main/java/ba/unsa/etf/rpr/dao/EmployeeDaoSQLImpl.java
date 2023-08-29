package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;


import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl()  {
        super("employees");
    }

    public static EmployeeDaoSQLImpl getInstance() {
        if(instance != null)
            return instance;
        instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    @Override
    public Employee row2object(ResultSet rs) throws CompanyException {
        try{
            Employee obj = new Employee(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("hire_date").toLocalDate(),
                    null, DaoFactory.jobDao().getById(rs.getInt("job_id")), rs.getDouble("salary"));
            try {
                obj.setDepartment(DaoFactory.departmentDao().getById(rs.getInt("department_id")));
            }
            catch(Exception e){
                throw new CompanyException(e.getMessage(),e);
            }
            obj.setUsername(rs.getString("username"));
            obj.setPasswordHash(rs.getString("password_hash"));
            obj.setAdmin(rs.getBoolean("admin"));
           //
            return obj;
        } catch (Exception e) {
            throw new CompanyException(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Employee obj) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", obj.getId());
        row.put("first_name", obj.getFirstName());
        row.put("last_name", obj.getLastName());
        row.put("hire_date", obj.getHireDate());
        row.put("salary", obj.getSalary());
        row.put("department_id", obj.getDepartment().getId());
        row.put("job_id", obj.getJob().getId());
        row.put("username", obj.getUsername());
        row.put("password_hash", obj.getPasswordHash());
        row.put("admin", obj.isAdmin());
        return row;
    }
    @Override
    public List<Employee> searchByName(String name) throws CompanyException {


            return super.executeQuery("SELECT *\n" +
                            "FROM employees\n" +
                            "WHERE lower(first_name) LIKE concat(?, '%') OR\n" +
                            "      lower(last_name) LIKE concat(?, '%') OR\n" +
                            "      lower(concat(first_name, ' ', last_name)) LIKE concat(?, '%')",
                    new Object[]{name.toLowerCase() + "%", name.toLowerCase() + "%", name.toLowerCase() + "%"});


    }

    @Override
    public List<Employee> searchByDepartment(Department dept) throws CompanyException {
            return super.executeQuery("select * from Employees where department_id = ?", new Object[]{dept.getId()});

    }

    @Override
    public List<Employee> searchByJob(Job j) throws CompanyException {
        return super.executeQuery("select * from Employees where job_id = ?", new Object[]{j.getId()});

    }

    @Override
    public Employee getByIdWithoutDepartment(int id) throws CompanyException {
            return super.executeQueryUnique("select id, first_name, last_name, hire_date, null as department_id, job_id, salary, username, password_hash, admin from Employees where id = ?", new Object[]{id});

    }


}
