package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;
import ba.unsa.etf.rpr.Employee;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class EmployeeDaoSQLImpl implements EmployeeDao{

    private Connection connection;

    public EmployeeDaoSQLImpl(){
        try{
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("resources/application.properties.sample").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
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
