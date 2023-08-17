package models;

import jakarta.persistence.*;
import listener.ListenerDate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(schema = "SubTask")
public class SubTaskModel extends ListenerDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    @Column(nullable = false, length = 50)
    protected String title;

    @Column(length = 300)
    protected String subTaskDescription;

    @ManyToOne
    @JoinColumn(name = "task")
    protected TaskModel task;

}
