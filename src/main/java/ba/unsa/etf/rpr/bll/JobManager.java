package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.SQLException;
import java.util.List;

public class JobManager{

    public Job getJobById(int id) throws CompanyException {
        return DaoFactory.jobDao().getById(id);
    }


    public void addNewJob(Job j) throws SQLException, CompanyException {
        validateJob(j);
        DaoFactory.jobDao().add(j);
    }

    private void validateJob(Job j) throws SQLException, CompanyException {
        if(j.getMinSalary() > j.getMaxSalary())
            throw new CompanyException("Check salaries again.");
        for(Job job : getAllJobs()){
            if(job.getTitle().equals(j.getTitle()))
                throw new CompanyException("Job with the same name already exists.");
        }
    }


    public void updateJob(Job j) throws CompanyException {

        DaoFactory.jobDao().update(j);
    }


    public void deleteJob(int id) throws CompanyException {
        try {
            DaoFactory.jobDao().delete(id);
        } catch (CompanyException e){
            if (e.getMessage().contains("FOREIGN KEY")){
                throw new CompanyException("There are employees on this job! Change their jobs first.");
            }
            throw e;
        }
    }


    public List<Job> getAllJobs() throws CompanyException {
        return DaoFactory.jobDao().getAll();
    }
}
