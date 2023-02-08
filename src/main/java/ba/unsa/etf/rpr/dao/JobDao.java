package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Job;

import java.util.List;

public interface JobDao extends Dao<Job>{
    List<Job> getByTitle(String txt);

}
