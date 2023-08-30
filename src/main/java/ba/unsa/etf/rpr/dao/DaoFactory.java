package ba.unsa.etf.rpr.dao;

/**
 * Factory design pattern for DAO objects.
 */
public class DaoFactory {
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();
    private static final DepartmentDao departmentDao = DepartmentDaoSQLImpl.getInstance();
    private static final JobDao jobDao = JobDaoSQLImpl.getInstance();

    public DaoFactory(){}

    /**
     * @return EmployeeDao instance
     */
    public static EmployeeDao employeeDao(){ return employeeDao;}

    /**
     * @return DepartmentDao instance
     */
    public static DepartmentDao departmentDao(){ return departmentDao;}

    /**
     * @return EmployeeDao instance
     */
    public static JobDao jobDao(){ return jobDao;}
}
