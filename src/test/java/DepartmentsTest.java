import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing Department business logic, with mocked DepartmentManager instance.
 */
public class DepartmentsTest {

    private final DepartmentManager departmentManager = Mockito.mock(DepartmentManager.class);
    private final List<Department> departments = new ArrayList<>();

    @Before
    public void prepareForTesting() throws CompanyException {
        departments.add(new Department(1,"IT", "Tokyo", null));
        departments.add(new Department(2,"Sales", "Tokyo", null));
        departments.add(new Department(3,"Finance", "Tokyo", null));

        // getById setup
        Mockito.doAnswer(args -> {
            for(Department e : departments){
                if(e.getId() == (int) args.getArgument(0)) {
                    return e;
                }
            }
            return null;
        }).when(departmentManager).getDeptById(Mockito.anyInt());
        // add Department setup
        Mockito.doAnswer(args -> {
            for(Department d : departments){
                if(d.getName().equals(((Department) args.getArgument(0)).getName()))
                    throw new CompanyException("Duplicates");
            }
            departments.add(args.getArgument(0));
            return null;
        }).when(departmentManager).addNewDept(Mockito.any());

    }
    @Test
    public void allDepartments() throws CompanyException {
        Mockito.when(departmentManager.getAllDepts()).thenReturn(departments);
        assertEquals(departments.size(), 3);
        Mockito.doAnswer(args -> {
            Department e = departmentManager.getDeptById(args.getArgument(0));
            departments.remove(e);
            return e;
        }).when(departmentManager).deleteDept(Mockito.anyInt());
        departmentManager.deleteDept(1);
        assertEquals(departmentManager.getAllDepts().size(), 2);
    }

    @Test
    public void addValidDepartment(){
        Department d = new Department(4,"Administration", "Tokyo", null) ;
        int oldSize = departments.size();
        assertDoesNotThrow(() -> {
            departmentManager.addNewDept(d);
        });
        assertEquals(oldSize + 1, departments.size());
    }

    @Test
    public void addDuplicate(){
        Department d = new Department(4,"Sales", "Tokyo", null) ;
        int oldSize = departments.size();
        assertThrows(CompanyException.class, () -> {
            departmentManager.addNewDept(d);
        });
        assertEquals(oldSize, departments.size());

    }
}
