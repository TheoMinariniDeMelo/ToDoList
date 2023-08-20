package application.services.controller;

import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class Get {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public List<UserModel> findAll() {
        return jpaRepositoriesService.getUserRepository().findAll();
    }

    public UserModel findById(@NotNull UUID id) {
        return jpaRepositoriesService.getUserRepository().findById(id).get();
    }

    public boolean existsById(UUID id) {
        return jpaRepositoriesService.getUserRepository().existsById(id);
    }

    public List<TaskModel> findByUserId(UUID id, PageRequest page) {
        return jpaRepositoriesService.getTaskRepository().findByUserId(id, page);
    }

    public List<SubModel> findSubTaskByIdTask(UUID id) {
        return jpaRepositoriesService.getSubTaskRepository().findByTaskId(id);
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

    public List<SubModel> findByTaskIdAndTitleWithPagination(UUID id, String title, int limit, int offSet) {
        return jpaRepositoriesService.getSubTaskRepository().findByTaskIdAndTitleWithPagination(id, title, limit, offSet);
    }
}
