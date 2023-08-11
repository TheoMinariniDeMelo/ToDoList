package models;

import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "taskPrimary")
public class ListSecondary extends ListenerDate implements Serializable {
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
    protected String describe;

    @OneToMany(mappedBy = "taskPrimary")
    protected UUID listSuper;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public UUID getListSuper() {
        return listSuper;
    }

    public void setListSuper(UUID listSuper) {
        this.listSuper = listSuper;
    }
}
