package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "task")
public class TaskModel extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 300)
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "user")
    protected UserModel user;

    @OneToMany(mappedBy = "task")
    @JsonIgnore
    protected List<SubTaskModel> subtasks;


    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

// Getters and setters


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String describe_task) {
        this.taskDescription = describe_task;
    }

    public List<SubTaskModel> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTaskModel> subtasks) {
        this.subtasks = subtasks;
    }
}
