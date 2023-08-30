package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.SQLException;
import java.util.List;

/**
 * Job business logic.
 */
public class JobManager{
    /**
     * Gets job from db with given id.
     * @param id
     * @return Job
     * @throws CompanyException
     */
    public Job getJobById(int id) throws CompanyException {
        return DaoFactory.jobDao().getById(id);
    }

    /**
     * Saves job  in db.
     * @param j
     * @throws CompanyException
     */
    public void addNewJob(Job j) throws CompanyException {
        validateJob(j);
        DaoFactory.jobDao().add(j);
    }

    /**
     * Checks if sent object j is valid, in terms of salary range and uniqueness of job title.
     * @param j
     * @throws CompanyException
     */
    private void validateJob(Job j) throws CompanyException {
        if(j.getMinSalary() > j.getMaxSalary())
            throw new CompanyException("Check salaries again.");
        for(Job job : getAllJobs()){
            if(job.getTitle().equals(j.getTitle()))
                throw new CompanyException("Job with the same name already exists.");
        }
    }

    /**
     * Updates object in db.
     * @param j
     * @throws CompanyException
     */
    public void updateJob(Job j) throws CompanyException {
        validateJob(j);
        DaoFactory.jobDao().update(j);
    }

    /**
     * Deletes object with given id.
     * @param id
     * @throws CompanyException
     */
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

    /**
     * @return list of all jobs
     * @throws CompanyException
     */
    public List<Job> getAllJobs() throws CompanyException {
        return DaoFactory.jobDao().getAll();
    }
}
