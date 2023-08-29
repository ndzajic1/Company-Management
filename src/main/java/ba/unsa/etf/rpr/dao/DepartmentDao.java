package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.CompanyException;

import java.util.List;

/**
 * Interface to be implemented by DaoSQLImpl for the Departments table
 */
public interface DepartmentDao extends Dao<Department>{

     List<Department> searchByName(String name) throws CompanyException;
}
