package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Department;

import java.util.List;

public interface DepartmentDao extends Dao<Department>{

    List<Department> getByName(String txt);
}
