package main.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "ExecutorsSubscriptions")
public class ExecutorsSubscriptions {

    @EmbeddedId
    private ExecutorsSubscriptionsKey id;

    @ManyToOne
    @JoinColumn(name = "employee", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task", insertable = false, updatable = false)
    private Task task;

}
