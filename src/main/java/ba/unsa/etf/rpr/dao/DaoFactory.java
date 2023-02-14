package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();
    private static final DepartmentDao departmentDao = DepartmentDaoSQLImpl.getInstance();
    private static final JobDao jobDao = JobDaoSQLImpl.getInstance();

    public DaoFactory(){}

    public static EmployeeDao employeeDao(){ return employeeDao;}
    public static DepartmentDao departmentDao(){ return departmentDao;}
    public static JobDao jobDao(){ return jobDao;}
}
