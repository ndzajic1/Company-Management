package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

/**
 * Interface for fetching Department objects.
 */
public interface DepartmentDao extends Dao<Department>{
     /**
      * Searches for department with given name.
      * @param name
      * @return matched departments list
      * @throws CompanyException
      */
     List<Department> searchByName(String name) throws CompanyException;
}
