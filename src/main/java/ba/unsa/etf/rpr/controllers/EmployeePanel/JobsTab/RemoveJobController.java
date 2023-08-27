package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import javafx.event.ActionEvent;

public class RemoveJobController {
    private Job job;
    private JobManager jobManager = new JobManager();

    public RemoveJobController(Job j){
        this.job = j;
    }

    public void removeJob(ActionEvent actionEvent) {
        // moze baciti exc
        jobManager.deleteJob(job.getId());
    }
}
