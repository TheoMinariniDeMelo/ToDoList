package application.models;


import application.models.listener.DataListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sub_task_model")
public class SubModel extends DataListener implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    @Column
    protected String title;

    @Column
    protected String describe;

    @ManyToOne
    @JoinColumn(name = "task", nullable = false)
    protected TaskModel task;
}
