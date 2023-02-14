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

    }

    @Override
    public void update(Job j) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Job> getAll() {
        return null;
    }
}
