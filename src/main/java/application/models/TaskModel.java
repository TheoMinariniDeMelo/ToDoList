package application.models;

import application.models.listener.DataListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel extends DataListener implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(length = 50, nullable = false, updatable = true)
    protected String title;

    @Column(length = 50, nullable = false, updatable = true)
    protected String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    protected UserModel user;

    protected StateByTask state;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    protected List<SubModel> subTask;

    @PrePersist
    public void setStateDefault(){
        state = StateByTask.fromValue(1);
    }
    public void setState(int stateValue) {
        state = StateByTask.fromValue(stateValue);
    }


}
