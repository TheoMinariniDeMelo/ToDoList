package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import listener.ListenerDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "User")
public class UserModel extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(length = 32)
    protected String password;


    @Column(length = 50)
    protected String user;

    @Column(length = 100)
    protected String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    protected List<TaskModel> tasks;

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
