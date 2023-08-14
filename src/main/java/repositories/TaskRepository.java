package repositories;

import models.TaskModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    @Query(value = "SELECT * FROM task WHERE user_id = :param", nativeQuery = true)
    List<TaskModel> findByUserId(@Param("param") UUID userId);
}
