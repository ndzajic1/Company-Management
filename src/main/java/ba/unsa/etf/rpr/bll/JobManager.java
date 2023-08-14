package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Job;

import java.sql.SQLException;
import java.util.List;

public class JobManager{

    public Job getJobById(int id) throws SQLException {
        return DaoFactory.jobDao().getById(id);
    }


    public void addNewJob(Job j) {
        try{
            validateJobTitle(j);
            DaoFactory.jobDao().add(j);
        } catch(SQLException e){
            // already exists
        }
    }

    private void validateJobTitle(Job j) throws SQLException {
        for(Job job : getAllJobs()){
            if(job.getTitle().equals(j.getTitle()))
                throw new RuntimeException("duplicate");
        }
    }


    public void updateJob(Job j) {

        DaoFactory.jobDao().update(j);
    }


    public void deleteJob(int id) {
        try {
            DaoFactory.jobDao().delete(id);
        } catch (RuntimeException e){
            // foreign key constraint
        }
    }


    public List<Job> getAllJobs() throws SQLException {
        return DaoFactory.jobDao().getAll();
    }
}
