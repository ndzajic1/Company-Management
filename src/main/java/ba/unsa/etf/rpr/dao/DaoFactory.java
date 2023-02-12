package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();
    private static final EmployeeDao departmentDao = EmployeeDaoSQLImpl.getInstance();
    private static final EmployeeDao jobDao = EmployeeDaoSQLImpl.getInstance();

    public DaoFactory(){}

    public static EmployeeDao employeeDao(){ return employeeDao;}
    public static EmployeeDao departmentDao(){ return departmentDao;}
    public static EmployeeDao jobDao(){ return jobDao;}
}
