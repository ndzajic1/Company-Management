package ba.unsa.etf.rpr.bll;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Job;

import java.sql.SQLException;
import java.util.List;

public class JobManager implements Manageable<Job>{
    @Override
    public Job getById(int id) throws SQLException {
        return DaoFactory.jobDao().getById(id);
    }

    @Override
    public void add(Job j) {
        try{
            validateJobTitle(j);
            DaoFactory.jobDao().add(j);
        } catch(SQLException e){
            // my except
        }
    }

    private void validateJobTitle(Job j) throws SQLException {
        for(Job job : getAll()){
            if(job.getTitle().equals(j.getTitle()))
                throw new RuntimeException("duplicate");
        }
    }

    @Override
    public void update(Job j) {
        DaoFactory.jobDao().update(j);
    }

    @Override
    public void delete(int id) {
        try {
            DaoFactory.jobDao().delete(id);
        } catch (RuntimeException e){
            // foreign key constraint
        }
    }

    @Override
    public List<Job> getAll() throws SQLException {
        return DaoFactory.jobDao().getAll();
    }
}
