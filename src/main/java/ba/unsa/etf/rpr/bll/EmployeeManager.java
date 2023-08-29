package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager{
    public Employee getById(int id) throws CompanyException {
        return DaoFactory.employeeDao().getById(id);
    }
    private String generateUsername(Employee user) throws CompanyException {
        try {
            List<Employee> users = getAllEmployees();
            int counter = 0;
            for (Employee e : users) {
                if (e.getFirstName().charAt(0) == user.getFirstName().charAt(0)
                        && e.getLastName().equals(user.getLastName()))
                    counter = counter + 1;
            }
            StringBuilder sb = new StringBuilder(Character.toLowerCase(user.getFirstName().charAt(0))
                    + user.getLastName().toLowerCase());
            if (counter == 0)
                return sb.toString();
            else
                return sb.append(counter).toString();
        }catch(CompanyException | SQLException e){
            throw new CompanyException("Connection lost");
        }
    }

    public void addNewEmployee(Employee e) throws CompanyException {
        try {
            String username = generateUsername(e);
            e.setId(0);
            e.setUsername(username);
            e.setAdmin(false);
            System.out.println(e);
            DaoFactory.employeeDao().add(e);
        } catch(CompanyException ee){
            throw new CompanyException("Connection lost");
        }
    }


    public void updateEmployee(Employee e) throws CompanyException {
        try {
            DaoFactory.employeeDao().update(e);
        } catch(CompanyException ee){
            throw new CompanyException("Connection lost");
        }
    }


    public void deleteEmployee(int id) throws CompanyException {
        try {
            DaoFactory.employeeDao().delete(id);
        }catch (CompanyException e){
            // deleting manager
        }
    }


    public List<Employee> getAllEmployees() throws SQLException, CompanyException {
        try {
            return DaoFactory.employeeDao().getAll();
        }catch (Exception e){
            throw new CompanyException("Connection lost");
        }
    }
    public List<Employee> getEmployeesFromDepartment(Department d) throws CompanyException {
        return DaoFactory.employeeDao().searchByDepartment(d);
    }
    public Employee getEmployeeByUsername(String username) throws SQLException, CompanyException {
        List<Employee> employees = getAllEmployees();
        for(Employee e : employees){
            if(e.getUsername().equals(username)){
                return e;
            }
        }
        return null;
    }

    public List<Employee> searchEmployees(String query) throws CompanyException {

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
