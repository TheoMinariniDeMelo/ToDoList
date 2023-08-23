package application.services.controller.repositoriesByAspects;

import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.JpaRepositoriesService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class Get {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public UserModel findById(@NotNull UUID id) {
        return jpaRepositoriesService.getUserRepository().findById(id).get();
    }

    public boolean existsById(UUID id) {
        return jpaRepositoriesService.getUserRepository().existsById(id);
    }

    public Page<TaskModel> findByUserId(UUID id, PageRequest page) {
        return jpaRepositoriesService.getTaskRepository().findByUserId(id, page);
    }


    public Optional<TaskModel> findTaskById(UUID id) {
        return jpaRepositoriesService.getTaskRepository().findById(id);
    }

    public Optional<SubModel> findSubTaskById(UUID id) {
        return jpaRepositoriesService.getSubTaskRepository().findById(id);
    }

    public Optional<UserModel> findByEmail(String email) {
        return jpaRepositoriesService.getUserRepository().findByEmail(email);
    }

    public Page<SubModel> findByTaskIdAndTitleWithPagination(UUID id, String title, PageRequest pageRequest) {
        return jpaRepositoriesService.getSubTaskRepository().findByTaskIdAndTitleWithPagination(id, title, pageRequest);
    }

    public Page<TaskModel> findByUserIdAndTitleContaining(UUID userId, String title, PageRequest pageRequest) {
        return jpaRepositoriesService.getTaskRepository().findByUserIdAndTitleContaining(userId, title, pageRequest);
    }

    ;

    public Page<SubModel> findByTaskIdWithPagination(UUID id, PageRequest pageRequest) {
        return jpaRepositoriesService.getSubTaskRepository().findByTaskId(id, pageRequest);
    }
}
