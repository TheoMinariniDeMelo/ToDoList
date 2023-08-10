package models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(schema = "taskPrimary")
public class ListSecondary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(length = 50)
    protected String title;
    @Column(length = 300)
    protected String describe;

    @OneToMany(mappedBy = "taskPrimary")
    protected UUID listSuper;

}
