package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

public interface JobDao extends Dao<Job>{
    List<Job> searchByTitle(String txt) throws CompanyException;

}
