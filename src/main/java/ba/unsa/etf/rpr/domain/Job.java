package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Job implements Idable {
    int id;
    String title;
    double minSalary, maxSalary;

    public Job(){

    }

    public Job(int id, String title, double minSalary, double maxSalary) {
        this.id = id;
        this.title = title;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id && Double.compare(job.minSalary, minSalary) == 0 && Double.compare(job.maxSalary, maxSalary) == 0 && title.equals(job.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, minSalary, maxSalary);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
