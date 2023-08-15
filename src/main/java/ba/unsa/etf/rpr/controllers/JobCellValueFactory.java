package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class JobCellValueFactory implements Callback<TableColumn.CellDataFeatures<Job, String>, String> {

    // mora ovo bolje
    private JobManager jobManager = new JobManager();
    private String field;

    public JobCellValueFactory(String e){
        field = e;
    }

    @Override
    public String call(TableColumn.CellDataFeatures<Job, String> features) {
        Job e = features.getValue();
        return switch (field) {
            case "Job Title" -> e.getTitle();
            case "Minimal Salary" -> Double.toString(e.getMinSalary());
            case "Maximal Salary" -> Double.toString(e.getMaxSalary());
            case "Employees" -> Integer.toString(17);
            default -> throw new RuntimeException();
        };
    }
}