package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class JobCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>> {

    // mora ovo bolje
    private static EmployeeManager employeeManager = new EmployeeManager();
    private static JobManager jobManager = new JobManager();
    private String field;
    private static Map<Integer, Integer> employeesPerJob = null;

    public JobCellValueFactory(String e) throws SQLException, CompanyException {
        this.field = e;
        if(employeesPerJob == null){
            setEmployeesPerJobMap();
        }
    }

    public static void setEmployeesPerJobMap() throws SQLException, CompanyException {
        employeesPerJob = new TreeMap<>();
        for(Job d : jobManager.getAllJobs()){
            employeesPerJob.put(d.getId(),0);
        }
        for(Employee e : employeeManager.getAllEmployees()){
            Integer curr = employeesPerJob.get(e.getJob().getId()); // != null ? employeesPerDept.get(e.getDepartment().getId()) : 0;
            employeesPerJob.put(e.getJob().getId(), curr + 1);
        }
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> features) {
        Job e = features.getValue();
        return switch (field) {
            case "Job Title" -> new SimpleStringProperty(e.getTitle());
            case "Minimal Salary" -> new SimpleStringProperty(Double.toString(e.getMinSalary()));
            case "Maximal Salary" -> new SimpleStringProperty(Double.toString(e.getMaxSalary()));
            case "Employees" -> new SimpleStringProperty(Integer.toString(employeesPerJob.get(e.getId())));
            default -> throw new RuntimeException();
        };
    }
}