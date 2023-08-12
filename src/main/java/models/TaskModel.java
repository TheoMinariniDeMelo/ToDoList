package models;


import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "Task")
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

    @Column(nullable = false, length = 32)
    private UUID userId;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<SubTaskModel> subtasks;

    // Getters and setters
    public List<SubTaskModel> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTaskModel> subtasks) {
        this.subtasks = subtasks;
    }

    public UUID getUser_id() {
        return userId;
    }

    public void setUser_id(UUID user_id) {
        this.userId = user_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe_task() {
        return taskDescription;
    }

    public void setDescribe_task(String describe_task) {
        this.taskDescription = describe_task;
    }
}
