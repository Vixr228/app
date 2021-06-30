package main.model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExecutorsSubscriptionsKey implements Serializable {

    //@Column(name = "employee")
    private int employee;

   // @Column(name = "task")
    private int task;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutorsSubscriptionsKey that = (ExecutorsSubscriptionsKey) o;
        return employee == that.employee && task == that.task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, task);
    }


    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }
}
