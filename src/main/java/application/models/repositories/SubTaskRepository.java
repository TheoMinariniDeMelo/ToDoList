package application.models.repositories;

import application.models.StateByTask;
import application.models.SubModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubTaskRepository extends JpaRepository<SubModel, UUID> {
    Page<SubModel> findByTaskIdAndState(UUID id, StateByTask state, PageRequest pageRequest);

    @Query("SELECT t FROM TaskModel t WHERE t.user.id = :userId AND t.title LIKE %:title%")
    Page<SubModel> findByTaskIdAndTitleWithPagination(UUID userId, String title, PageRequest pageRequest);
}
