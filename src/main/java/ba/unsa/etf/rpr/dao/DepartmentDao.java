package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;

import java.util.List;

/**
 * Interface to be implemented by DaoSQLImpl for the Departments table
 */
public interface DepartmentDao extends Dao<Department>{

     List<Department> getByName(String name);
}
