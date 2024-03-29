package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Java Bean for Employee entity, referencing Department and Job entities.
 */
public class Employee extends LoggableUser implements Idable{
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    private Department department;
    private Job job;
    private Double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hireDate=" + hireDate +
                ", department=" + department +
                ", job=" + job +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.salary, salary) == 0 && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && hireDate.equals(employee.hireDate) && Objects.equals(department, employee.department) && Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, hireDate, department, job, salary);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int id, String firstName, String lastName, LocalDate hireDate, Department department, Job job, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.department = department;
        this.job = job;
        this.salary = salary;
    }

    public Employee(){

    }
}
