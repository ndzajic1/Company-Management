package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *  Accessing Employees objects in db.
 */
public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl()  {
        super("employees");
    }

    /**
     * @return instance
     */
    public static EmployeeDaoSQLImpl getInstance() {
        if(instance != null)
            return instance;
        instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    /**
     * Get object from provided ResultSet object which is gotten by executing query.
     * @param rs
     * @throws CompanyException
     */
    @Override
    public Employee row2object(ResultSet rs) throws CompanyException {
        try{
            Employee obj = new Employee(rs.getInt("id"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getDate("hire_date").toLocalDate(),
                    null, DaoFactory.jobDao().getById(rs.getInt("job_id")), rs.getDouble("salary"));
            try {
                System.out.println("BROJJJJJJJJ" + rs.getInt("department_id"));
                obj.setDepartment(DaoFactory.departmentDao().getById(rs.getInt("department_id")));
            }
            catch(Exception e){
                //throw new CompanyException(e.getMessage(),e);
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

    /**
     * Convert object to suitable map for db operations.
     * @param obj
     */
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

    /**
     * Search for employees in db by name, case insensitive search.
     * @param name
     * @return list
     * @throws CompanyException
     */
    @Override
    public List<Employee> searchByName(String name) throws CompanyException {


            return super.executeQuery("SELECT *\n" +
                            "FROM employees\n" +
                            "WHERE lower(first_name) LIKE concat(?, '%') OR\n" +
                            "      lower(last_name) LIKE concat(?, '%') OR\n" +
                            "      lower(concat(first_name, ' ', last_name)) LIKE concat(?, '%')",
                    new Object[]{name.toLowerCase() + "%", name.toLowerCase() + "%", name.toLowerCase() + "%"});


    }

    /**
     * Get all employees from specific department.
     * @param dept
     * @return list
     * @throws CompanyException
     */
    @Override
    public List<Employee> searchByDepartment(Department dept) throws CompanyException {
            return super.executeQuery("select * from Employees where department_id = ?", new Object[]{dept.getId()});

    }

    /**
     * Get all employees with specific job.
     * @param j
     * @return
     * @throws CompanyException
     */
    @Override
    public List<Employee> searchByJob(Job j) throws CompanyException {
        return super.executeQuery("select * from Employees where job_id = ?", new Object[]{j.getId()});

    }

    /**
     * Get employee by primary key, but Department attribute is null.
     * @param id
     * @throws CompanyException
     */
    @Override
    public Employee getByIdWithoutDepartment(int id) throws CompanyException {
            return super.executeQueryUnique("select id, first_name, last_name, hire_date, null as department_id, job_id, salary, username, password_hash, admin from Employees where id = ?", new Object[]{id});

    }


}
