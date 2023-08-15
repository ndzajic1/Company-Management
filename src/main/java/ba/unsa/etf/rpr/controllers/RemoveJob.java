package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Job;
import javafx.event.ActionEvent;

public class RemoveJob {
    private Job job;
    private JobManager jobManager = new JobManager();

    public RemoveJob(Job j){
        this.job = j;
    }

    public void removeJob(ActionEvent actionEvent) {
        // moze baciti exc
        jobManager.deleteJob(job.getId());
    }
}
