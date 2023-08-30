package ba.unsa.etf.rpr;
import ba.unsa.etf.rpr.bll.DepartmentManager;
import ba.unsa.etf.rpr.bll.EmployeeManager;
import ba.unsa.etf.rpr.bll.JobManager;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Job;
import ba.unsa.etf.rpr.exceptions.CompanyException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

/**
 * CLI User Interface.
 */
public class App {

    private static final Option showEmployees = new Option("se", "show-employees", false, "Shows all employees");
    private static final Option addEmployee = new Option("ae", "add-employee", false, "Adds new employee");
    private static final Option updateEmployee = new Option("ue", "update-employee", true, "Updates employee");
    private static final Option deleteEmployee = new Option("de", "delete-employee", true, "Deletes employee");

    private static final Option showDepartments = new Option("sd", "show-departments", false, "Shows all departments");
    private static final Option addDepartment = new Option("ad", "add-department", false, "Adds new department");
    private static final Option updateDepartment = new Option("ud", "update-department", true, "Updates department");
    private static final Option deleteDepartment = new Option("dd", "delete-department", true, "Deletes department");

    private static final Option showJobs = new Option("sj", "show-jobs", false, "Shows all jobs");
    private static final Option addJob = new Option("aj", "add-job", false, "Adds new job");
    private static final Option updateJob = new Option("uj", "update-job", true, "Updates job");
    private static final Option deleteJob = new Option("dj", "delete-job", true, "Deletes job");


    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final DepartmentManager departmentManager = new DepartmentManager();
    private static final JobManager jobManager = new JobManager();

    /**
     * Main for CLI.
     * @param args
     */
    public static void main(String[] args){
        try {
            Options options = new Options();
            options.addOption(showEmployees).addOption(addEmployee).addOption(updateEmployee).addOption(deleteEmployee)
                    .addOption(showDepartments).addOption(addDepartment).addOption(updateDepartment).addOption(deleteDepartment)
                    .addOption(showJobs).addOption(addJob).addOption(updateJob).addOption(deleteJob);
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);
            //employ
            if (commandLine.hasOption("show-employees")) {
                showEmployees();
            } else if (commandLine.hasOption("add-employee")) {
                Employee e = enterEmployee();
                employeeManager.addNewEmployee(e);
                System.out.println("Successfully added.");
            } else if (commandLine.hasOption("update-employee")) {
                int id = Integer.parseInt(commandLine.getOptionValue("update-employee"));
                Employee e = enterEmployee();
                e.setId(id);
                employeeManager.updateEmployee(e);
                System.out.println("Successfully updated.");
            } else if (commandLine.hasOption("delete-employee")) {
                int id = Integer.parseInt(commandLine.getOptionValue("delete-employee"));
                employeeManager.deleteEmployee(id);
                System.out.println("Successfully deleted.");
            }
                // departments options
              else if (commandLine.hasOption("show-departments")) {
                showDepartments();
            } else if (commandLine.hasOption("add-department")) {
                Department d = enterDepartment();
                departmentManager.addNewDept(d);
                System.out.println("Successfully added.");
            } else if (commandLine.hasOption("update-department")) {
                int id = Integer.parseInt(commandLine.getOptionValue("update-department"));
                Department d = enterDepartment();
                d.setId(id);
                departmentManager.updateDept(d);
                System.out.println("Successfully updated.");
            } else if (commandLine.hasOption("delete-department")) {
                int id = Integer.parseInt(commandLine.getOptionValue("delete-department"));
                departmentManager.deleteDept(id);
                System.out.println("Successfully deleted.");
            } else if (commandLine.hasOption("show-jobs")) {
                showJobs();
            } else if (commandLine.hasOption("add-job")) {
                Job j = enterJob();
                jobManager.addNewJob(j);
                System.out.println("Successfully added.");
            } else if (commandLine.hasOption("update-job")) {
                int id = Integer.parseInt(commandLine.getOptionValue("update-job"));
                Job j = enterJob();
                j.setId(id);
                jobManager.updateJob(j);
                System.out.println("Successfully updated.");
            } else if (commandLine.hasOption("delete-job")) {
                int id = Integer.parseInt(commandLine.getOptionValue("delete-job"));
                jobManager.deleteJob(id);
                System.out.println("Successfully deleted.");
            } else {
                HelpFormatter hf = new HelpFormatter();
                PrintWriter pw = new PrintWriter(System.out);
                hf.printUsage(pw, 100, "java -jar company-management-cli-jar-with-dependencies.jar [option] [arg]");
                hf.printOptions(pw, 100, options, 2, 5);
                pw.close();
            }
        } catch (CompanyException | ParseException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * User entering parameters of Job object.
     * @return cretaed Job instance
     */
    private static Job enterJob() {
        Job j = new Job();
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Job Title: ");
        j.setTitle(in.nextLine());

        System.out.print("Minimal salary: ");
        j.setMinSalary(in.nextDouble());

        System.out.print("Maximal salary: ");
        j.setMaxSalary(in.nextDouble());

        return j;
    }

    /**
     * Lists all jobs.
     * @throws CompanyException
     */
    private static void showJobs() throws CompanyException {
        for(Job j : jobManager.getAllJobs()){
            System.out.println(j.getId() + " - " + j.getTitle() + ", " + j.getMinSalary() + " - " + j.getMaxSalary());
        }
    }

    /**
     * User entering Department object attributes.
     * @return Department
     * @throws CompanyException
     */
    private static Department enterDepartment() throws CompanyException {
        Department d = new Department();
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Department Name: ");
        d.setName(in.nextLine());

        System.out.print("Department Location: ");
        d.setLocation(in.nextLine());

        System.out.print("Manager ID: ");
        d.setManager(employeeManager.getById(in.nextInt()));


        return d;
    }

    /**
     * Lists all departments.
     * @throws CompanyException
     */
    private static void showDepartments() throws CompanyException {
        for (Department d : departmentManager.getAllDepts()){
            System.out.println(d.getId() + " - " + d.getName() + ", " + d.getLocation());
        }
    }

    /**
     * User entering Employee object attributes.
     * @return created Employee object.
     * @throws CompanyException
     */
    private static Employee enterEmployee( ) throws CompanyException {
        Employee e = new Employee();
        Scanner in = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("First Name: ");
        e.setFirstName(in.nextLine());

        System.out.print("Last Name: ");
        e.setLastName(in.nextLine());

        System.out.print("Hire Date (YYYY-MM-DD): ");
        e.setHireDate(LocalDate.parse(in.next()));

        System.out.print("Department ID: ");
        e.setDepartment(departmentManager.getDeptById(in.nextInt()));

        System.out.print("Job ID: ");
        e.setJob(jobManager.getJobById(in.nextInt()));

        System.out.print("Salary: ");
        e.setSalary(in.nextDouble());

        return e;
    }

    /**
     * Lists all employees in terminal.
     * @throws CompanyException
     */
    private static void showEmployees() throws CompanyException {
        for(Employee e : employeeManager.getAllEmployees()){
            System.out.println(e.getId() + "-" + e.getFirstName() + " " + e.getLastName() + ", " + e.getDepartment().getName() + " Department, " + e.getJob().getTitle());
        }
    }
}


