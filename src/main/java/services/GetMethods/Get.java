package services.GetMethods;

import jakarta.validation.constraints.NotNull;
import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.JpaRepositoriesService;

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

    public List<TaskModel> findByUserId(UUID id) {
        return jpaRepositoriesService.getTaskRepository().findByUserId(id);
    }

    public List<SubTaskModel> findSubTaskByIdTask(UUID id) {
        return jpaRepositoriesService.getSubTaskRepository().findByTaskId(id);
    }

    public Optional<TaskModel> findTaskById(UUID id) {
        return jpaRepositoriesService.getTaskRepository().findById(id);
    }

    public Optional<SubTaskModel> findSubTaskById(UUID id) {
        return jpaRepositoriesService.getSubTaskRepository().findById(id);
    }

    public UserModel findByEmail(String email) {
        return this.jpaRepositoriesService.getUserRepository().findByEmail(email);
    }
}
