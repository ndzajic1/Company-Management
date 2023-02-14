package ba.unsa.etf.rpr.bll;

import java.sql.SQLException;
import java.util.List;

public interface Manageable<T> {

    T getById(int id) throws SQLException;
    void add(T o);

    void update(T o);

    void delete(T o);

    List<T> getAll();

}
