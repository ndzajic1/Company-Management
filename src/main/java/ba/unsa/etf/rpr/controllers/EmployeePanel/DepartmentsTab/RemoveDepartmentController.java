package ba.unsa.etf.rpr.controllers.EmployeePanel.DepartmentsTab;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.domain.Department;
import javafx.event.ActionEvent;

public class RemoveDepartmentController {
    private Department department;
    private DepartmentManager departmentManager = new DepartmentManager();

    public RemoveDepartmentController(Department d){
        this.department = d;
    }

    public void removeDept(ActionEvent actionEvent) {
        // moze baciti exc
        departmentManager.deleteDept(department.getId());
    }
}
