package ba.unsa.etf.rpr.controllers.EmployeePanel.JobsTab;

import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RemoveJobController {
    private Job job;
    private JobManager jobManager = new JobManager();

    private JobsTabController mainController;

    public RemoveJobController(Job j, Object o){
        this.job = j;
        this.mainController = (JobsTabController) o;
    }

    public void removeJob(ActionEvent actionEvent) throws SQLException {
        try {
            jobManager.deleteJob(job.getId());
            mainController.refreshTable();

            Node n = (Node) actionEvent.getSource();
            Stage currStage = (Stage) n.getScene().getWindow();
            currStage.close();
        } catch(CompanyException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
