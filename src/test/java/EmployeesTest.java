import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Employee;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmployeesTest {

    @Test
    public void addEmployee() throws SQLException {
        EmployeeManager em = new EmployeeManager();
        DepartmentManager dm = new DepartmentManager();
        JobManager jm = new JobManager();
        Employee e = new Employee();
        e.setFirstName("Ed");
        e.setLastName("Hojlund");
        e.setHireDate(LocalDate.now());
        e.setDepartment(dm.getAllDepts().get(0));
        e.setJob(jm.getAllJobs().get(0));
        e.setSalary(1000.0);
        List<Employee> oldList = em.getAllEmployees();

        em.addNewEmployee(e);

        assertEquals(em.getAllEmployees().size(), oldList.size() + 1);
    }
}
