package application.models.repositories;

import application.models.TaskModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUserId(UUID id, PageRequest page);

    @Query(value = "SELECT * FROM task_model t WHERE t.user.id = :id and t.title = like %:title%   ORDER BY s.id LIMIT :limit, :offset", nativeQuery = true)
    List<TaskModel> findByUserIdAndTitle(@Param("id") UUID id,
                                         @Param("title") String title,
                                         @Param("offset") Integer offset,
                                         @Param("limit") Integer limit);
}
