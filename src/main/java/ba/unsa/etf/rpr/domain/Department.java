package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Java Bean for Department entity, referencing Employee entity via manager:Employee attribute.
 */
public class Department implements Idable {
    int id;
    String name;
    String location;
    Employee manager;

    public Department(){

    }

    public Department(Department d, Employee e){
        this.id = d.getId();
        this.name = d.getName();
        this.location = d.getLocation();
        this.manager = e;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", manager='" + manager.getFirstName() + " " + manager.getLastName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && name.equals(that.name) && Objects.equals(location, that.location) && Objects.equals(manager.getFirstName(), that.manager.getFirstName())  && Objects.equals(manager.getLastName(), that.manager.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, manager);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department(int id, String name, String location, Employee manager) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.manager = manager;
    }
}
