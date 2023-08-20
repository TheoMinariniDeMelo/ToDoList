package application.repositories;

import application.models.SubModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubTaskRepository extends JpaRepository<SubModel, UUID> {
    List<SubModel> findByTaskId(UUID id);

    @Query(value = "SELECT * FROM sub_model s WHERE s.task.id = :id and s.title = like %:title%  ORDER BY s.id LIMIT :offset, :limit", nativeQuery = true)
    List<SubModel> findByTaskIdAndTitleWithPagination(@Param(value = "id") UUID id,
                                                      @Param(value = "title") String title,
                                                      @Param("offset") Integer offset,
                                                      @Param("limit") Integer limit);
}
