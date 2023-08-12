package models;

import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "taskPrimary")
public class ListSecondary extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(nullable = false, length = 50)
    protected String title;
    @Column(length = 300)
    protected String describe_task;

    @Column(length = 32, nullable = false)
    protected UUID task_id;


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
        return describe_task;
    }

    public void setDescribe_task(String describe) {
        this.describe_task = describe;
    }
}
