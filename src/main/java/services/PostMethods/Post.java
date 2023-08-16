package services.PostMethods;

import jakarta.validation.constraints.NotNull;
import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.JpaRepositoriesService;

@Service
public class Post {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public UserModel saveUser(UserModel user) {
        return jpaRepositoriesService.getUserRepository().save(user);
    }

    public TaskModel saveTask(TaskModel task) {
        return jpaRepositoriesService.getTaskRepository().save(task);
    }

    public SubTaskModel saveSubTask(SubTaskModel subTask) {
        return jpaRepositoriesService.getSubTaskRepository().save(subTask);
    }

}
