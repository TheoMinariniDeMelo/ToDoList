package models;


import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(schema = "list_primary")
public class ListPrimary extends ListenerDate implements Serializable {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "user")
    protected UUID user;


}
