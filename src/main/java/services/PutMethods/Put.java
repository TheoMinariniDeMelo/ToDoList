package services.PutMethods;

import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.JpaRepositoriesService;

import java.util.UUID;


@Service
public class Put {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public TaskModel putTask(TaskModel task) {
        return jpaRepositoriesService.getTaskRepository().save(task);
    }

    public SubTaskModel putSubTask(SubTaskModel Subtask) {
        return jpaRepositoriesService.getSubTaskRepository().save(Subtask);
    }

    public UserModel putUser(UserModel user) {
        return jpaRepositoriesService.getUserRepository().save(user);
    }
}
