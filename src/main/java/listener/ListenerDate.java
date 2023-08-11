package listener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ListenerDate {
    @CreatedDate
    private LocalDateTime dataCreate;

    @LastModifiedDate
    private LocalDateTime dataUpdate;

    // getters e setters
}