package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

/**
 * Interface for fetching Job objects.
 */
public interface JobDao extends Dao<Job>{
    /**
     * Searches for job with given string.
     * @param txt
     * @return list of matched jobs
     * @throws CompanyException
     */
    List<Job> searchByTitle(String txt) throws CompanyException;

}
