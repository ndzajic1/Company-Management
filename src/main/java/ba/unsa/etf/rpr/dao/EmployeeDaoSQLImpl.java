package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;
import ba.unsa.etf.rpr.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
        return null;
    }

    @Override
    public Employee getById(int id) {
        String query = "SELECT * FROM Employees WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Employee emp = new Employee(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getDate(4), null, null);
                rs.close();
                return emp;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Employee add(Employee item) {
        return null;
    }

    @Override
    public Employee update(Employee item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public List<Employee> searchByName(String txt) {
        return null;
    }

    @Override
    public List<Employee> searchByDepartment(Department dept) {
        return null;
    }


}
