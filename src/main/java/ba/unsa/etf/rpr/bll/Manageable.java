package ba.unsa.etf.rpr.bll;

import java.util.List;

public interface Manageable<T> {

    void add(T o);

    void update(T o);

    void delete(T o);

    List<T> getAll();

}
