package models;


import com.fasterxml.jackson.annotation.JsonAlias;
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
@Table(name = "task")

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

    @ManyToOne
    @JoinColumn(name = "user")
    protected UserModel user;

    @OneToMany(mappedBy = "task", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    protected List<SubTaskModel> subtasks;

}
