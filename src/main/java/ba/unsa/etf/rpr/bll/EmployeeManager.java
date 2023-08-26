package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager{
    public Employee getById(int id) throws SQLException {
        return DaoFactory.employeeDao().getById(id);
    }
    private String generateUsername(Employee user) throws SQLException {
        List<Employee> users = getAllEmployees();
        int counter = 0;
        for(Employee e : users){
            if(e.getFirstName().charAt(0) == user.getFirstName().charAt(0)
                    && e.getLastName().equals(user.getLastName()) )
                counter = counter + 1;
        }
        StringBuilder sb = new StringBuilder(Character.toLowerCase(user.getFirstName().charAt(0))
                + user.getLastName().toLowerCase());
        if(counter == 0)
            return sb.toString();
        else
            return sb.append(counter).toString();
    }

    public void addNewEmployee(Employee e) throws SQLException {
        String username = generateUsername(e);
        e.setId(666);
        e.setUsername(username);
        e.setAdmin(false);
        System.out.println(e.toString());
        DaoFactory.employeeDao().add(e);
    }


    public void updateEmployee(Employee e) {

        DaoFactory.employeeDao().update(e);
    }


    public void deleteEmployee(int id) {

        DaoFactory.employeeDao().delete(id);
    }


    public List<Employee> getAllEmployees() throws SQLException {
        return DaoFactory.employeeDao().getAll();
    }
    public List<Employee> getEmployeesFromDepartment(Department d){
        return DaoFactory.employeeDao().searchByDepartment(d);
    }
    public Employee getEmployeeByUsername(String username) throws SQLException {
        List<Employee> employees = getAllEmployees();
        for(Employee e : employees){
            if(e.getUsername().equals(username)){
                return e;
            }
        }
        return null;
    }

    public List<Employee> searchEmployees(String query) throws SQLException {

        List<Employee> emps = DaoFactory.employeeDao().searchByName(query);
        return emps;
        /*
        System.out.println("dbkveeee" + emps.size());
        if(query.equals("")){
            return employees;
        }
        List<Employee> filtered = new ArrayList<>();
        for(Employee e : employees){
            if(e.getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                    e.getLastName().toLowerCase().contains(query.toLowerCase()) ||
                    (e.getFirstName() + " " + e.getLastName()).toLowerCase().contains(query.toLowerCase()))
                filtered.add(e);

        }
        return filtered;  */
    }
}
