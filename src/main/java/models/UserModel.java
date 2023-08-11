package models;


import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "users")

public class UserModel extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(nullable = true)
    protected LocalDateTime dataCreate;

    @Column(nullable = true)
    protected LocalDateTime dataUpdate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(length = 50)
    protected String user;
    @Column(length = 100)
    protected String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}