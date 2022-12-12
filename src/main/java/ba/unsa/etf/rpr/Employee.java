package ba.unsa.etf.rpr;

import java.util.Date;
import java.util.Objects;

public class Employee {
    int id;
    String name;
    double salary;
    Date hireDate;
    Employee manager;
    Department department;
    Job job;

    public Employee(int id, String name, double salary, Date hireDate, Employee manager, Department department, Job job) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.manager = manager;
        this.department = department;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", manager=" + manager +
                ", department=" + department +
                ", job=" + job +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Double.compare(employee.salary, salary) == 0 && name.equals(employee.name) && hireDate.equals(employee.hireDate) && Objects.equals(manager, employee.manager) && Objects.equals(department, employee.department) && job.equals(employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, hireDate, manager, department, job);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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
}
