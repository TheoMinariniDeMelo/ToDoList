package models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import listener.ListenerDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
