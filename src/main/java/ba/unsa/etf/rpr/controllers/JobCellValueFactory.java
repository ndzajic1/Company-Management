package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.Map;

public class JobCellValueFactory<T> implements Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>> {

    // mora ovo bolje
    private JobManager jobManager = new JobManager();
    private String field;
    private Map<Integer, Integer> numOfEmployeesPerJob;

    public JobCellValueFactory(String e){
        field = e;
    }

    public JobCellValueFactory(Map<Integer, Integer> map){
        field = "Employees";
        numOfEmployeesPerJob = map;
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> features) {
        Job e = features.getValue();
        return switch (field) {
            case "Job Title" -> new SimpleStringProperty(e.getTitle());
            case "Minimal Salary" -> new SimpleStringProperty(Double.toString(e.getMinSalary()));
            case "Maximal Salary" -> new SimpleStringProperty(Double.toString(e.getMaxSalary()));
            case "Employees" -> new SimpleStringProperty(Integer.toString(numOfEmployeesPerJob.get(e.getId())));
            default -> throw new RuntimeException();
        };
    }
}