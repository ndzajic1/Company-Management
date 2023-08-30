package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

/**
 * Employee business logic.
 */
public class EmployeeManager{
    /**
     * Finds employee by id.
     * @param id
     * @return Employee
     * @throws CompanyException
     */
    public Employee getById(int id) throws CompanyException {
        return DaoFactory.employeeDao().getById(id);
    }

    /**
     * Generates unique username for new employee.
     * @param user
     * @return generated username
     * @throws CompanyException
     */
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
        }catch(CompanyException e){
            throw new CompanyException(e.getMessage(), e);
        }
    }

    /**
     * Adds new employee to db.
     * @param e
     * @throws CompanyException
     */
    public void addNewEmployee(Employee e) throws CompanyException {
        try {
            String username = generateUsername(e);
            e.setId(0);
            e.setUsername(username);
            e.setAdmin(false);
            System.out.println(e);
            DaoFactory.employeeDao().add(e);
        } catch(CompanyException ee){
            throw new CompanyException(ee.getMessage(), ee);
        }
    }

    /**
     * Updates employee sent as param.
     * @param e
     * @throws CompanyException
     */
    public void updateEmployee(Employee e) throws CompanyException {
        try {
            DaoFactory.employeeDao().update(e);
        } catch(CompanyException ee){
            throw new CompanyException(ee.getMessage(), ee);
        }
    }

    /**
     * Deletes employee with given id.
     * @param id
     * @throws CompanyException
     */
    public void deleteEmployee(int id) throws CompanyException {
        try {
            DaoFactory.employeeDao().delete(id);
        }catch (CompanyException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new CompanyException("Selected employee is the manager of another department.");
            }
            throw e;
        }
    }

    /**
     * @return list of all employees
     * @throws CompanyException
     */
    public List<Employee> getAllEmployees() throws CompanyException {
        try {
            return DaoFactory.employeeDao().getAll();
        }catch (Exception e){
            throw new CompanyException(e.getMessage(), e);
        }
    }

    /**
     * Gets all employees from specific department d.
     * @param d
     * @return list
     * @throws CompanyException
     */
    public List<Employee> getEmployeesFromDepartment(Department d) throws CompanyException {
        return DaoFactory.employeeDao().searchByDepartment(d);
    }

    /**
     * Get employee by username, used while logging in the app.
     * @param username
     * @return matched employee
     * @throws CompanyException
     */
    public Employee getEmployeeByUsername(String username) throws CompanyException {
        List<Employee> employees = getAllEmployees();
        System.out.println("ITSSS " + employees.size());
        for(Employee e : employees){
            if(e.getUsername().equals(username)){
                return e;
            }
        }
        return null;
    }

    /**
     * Search db for employees with given name.
     * @param query
     * @return list
     * @throws CompanyException
     */
    public List<Employee> searchEmployees(String query) throws CompanyException {

        return DaoFactory.employeeDao().searchByName(query);

    }
}
