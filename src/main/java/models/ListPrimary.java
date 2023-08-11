package models;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "list_primary")
public class ListPrimary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = true)
    protected LocalDateTime dataCreate;

    @Column(nullable = true)
    protected LocalDateTime dataUpdate;

    @Column(nullable = false, length = 50)
    protected String title;
    @Column(nullable = true, length = 300)
    protected String describe_task;

    @ManyToOne()
    @JoinColumn()
    protected UserModel user_id;

    public UserModel getUser_id() {
        return user_id;
    }

    public void setUser_id(UserModel user_id) {
        this.user_id = user_id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(LocalDateTime dataCreate) {
        this.dataCreate = dataCreate;
    }

    public LocalDateTime getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(LocalDateTime dataUpdate) {
        this.dataUpdate = dataUpdate;
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
