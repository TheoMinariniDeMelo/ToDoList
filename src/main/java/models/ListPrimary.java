package models;


import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "list_primary")
public class ListPrimary extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 300)
    private String describe_task;

    @Column(nullable = false, length = 32)
    private UUID user_id;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<ListSecondary> subtasks;

    // Getters and setters
    public List<ListSecondary> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<ListSecondary> subtasks) {
        this.subtasks = subtasks;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
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
        return describe_task;
    }

    public void setDescribe_task(String describe_task) {
        this.describe_task = describe_task;
    }
}
