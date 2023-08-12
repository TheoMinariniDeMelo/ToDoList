package models;

import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(schema = "SubTask")
public class SubTaskModel extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    protected String title;
    @Column(length = 300)
    protected String subTaskDescription;

    @Column(length = 32, nullable = false, insertable = false, updatable = false)
    private UUID taskId;

    @ManyToOne
    protected TaskModel task;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTask_id() {
        return taskId;
    }

    public void setTask_id(UUID task_id) {
        this.taskId = task_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe_task() {
        return subTaskDescription;
    }

    public void setDescribe_task(String describe) {
        this.subTaskDescription = describe;
    }
}
