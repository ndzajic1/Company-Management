import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing Employee business logic, with mocked EmployeeManager instance.
 */
public class EmployeesTest {
    private final EmployeeManager employeeManager = Mockito.mock(EmployeeManager.class);
    private final List<Employee> employees = new ArrayList<>();
    @Before
    public void prepareForTesting() throws CompanyException {
        employees.add(new Employee(1,"Zlatan","Ibrahimovic", null, null, null, 1000.0));
        employees.add(new Employee(2,"Leo","Messi", null, null, null, 3000.0));
        employees.add(new Employee(3,"Ronaldo","Nazario", null, null, null, 3000.0));
        Mockito.doAnswer(args -> {
            for(Employee e : employees){
                if(e.getId() == (int) args.getArgument(0)) {
                    return e;
                }
            }
            return null;
        }).when(employeeManager).getById(Mockito.anyInt());
    }
    @Test
    public void getAllEmployees() throws CompanyException {
        Mockito.when(employeeManager.getAllEmployees()).thenReturn(employees);
        assertEquals(employees.size(), 3);
        assertEquals(employeeManager.getAllEmployees().size(), 3);
    }
    @Test
    public void addEmployee() throws CompanyException {
        int oldSize = employees.size();
        Mockito.doAnswer(args -> {
            employees.add(args.getArgument(0));
            return null;
        }).when(employeeManager).addNewEmployee(Mockito.any());
        employeeManager.addNewEmployee(
                new Employee(4,"Cristiano","Ronaldo", null, null, null, 3000.0)
        );
        assertEquals(oldSize + 1, employees.size());
    }

    @Test
    public void removeEmployee() throws CompanyException {
        int oldSize = employees.size();
        Mockito.doAnswer(args -> {
            Employee e = employeeManager.getById(args.getArgument(0));
            employees.remove(e);
            return e;
        }).when(employeeManager).deleteEmployee(Mockito.anyInt());
        employeeManager.deleteEmployee(3);
        assertEquals(oldSize - 1, employees.size());
        assertFalse((employees.contains(new Employee(3,"Ronaldo","Nazario", null, null, null, 3000.0))));
    }

    @Test
    public void editEmployee() throws CompanyException {
        Mockito.doAnswer(args -> {
            Employee e = employeeManager.getById(((Employee) args.getArgument(0)).getId());
            employees.remove(e.getId());
            employees.add(args.getArgument(0));
            return e;
        }).when(employeeManager).updateEmployee(Mockito.any());
        Employee e = employees.get(0);
        e.setSalary(10000.0);
        employeeManager.updateEmployee(e);
        Employee updated = employeeManager.getById(1);
        assertTrue(updated.getSalary() == 10000.0);
    }


}
