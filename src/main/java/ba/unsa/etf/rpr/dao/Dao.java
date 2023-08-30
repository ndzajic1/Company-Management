package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.sql.SQLException;
import java.util.List;

/**
 * Main interface for accessing objects from database.
 * @param <T>
 */
public interface Dao <T> {

    /**
     * Gets entity from db based on its primary key
     * @param id
     * @return entity from database
     */
    T getById(int id) throws  CompanyException;

    /**
     * Saves entity into db
     * @param item object to be saved in db
     * @return saved item with id field populated
     */
    T add(T item) throws CompanyException;

    /**
     * Updates entity in db with given id.
     * @param item - object to be updated, id must be populated
     * @return updated object
     */
    T update(T item) throws CompanyException;

    /**
     * Delete object from database with given id
     * @param id - primary key of entity
     */
    void delete(int id) throws CompanyException;

    /**
     * Fetching all entity objects from db.
     * @return list of entities from db
     * @throws CompanyException
     */
    List<T> getAll() throws CompanyException;
}
