package main.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="ExecutorsSubscriptions",
            joinColumns = {@JoinColumn(name="employee")},
            inverseJoinColumns = {@JoinColumn(name="task")})
    private List<Employee> executors;

    private int daysAmount;

    private String successDescription;

    private boolean isComplete;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public List<Employee> getExecutors() {
        return executors;
    }

    public void setExecutors(List<Employee> executors) {
        this.executors = executors;
    }

    public int getDaysAmount() {
        return daysAmount;
    }

    public void setDaysAmount(int daysAmount) {
        this.daysAmount = daysAmount;
    }

    public String getSuccessDescription() {
        return successDescription;
    }

    public void setSuccessDescription(String successDescription) {
        this.successDescription = successDescription;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
