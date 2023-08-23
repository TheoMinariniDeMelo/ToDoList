package application.services.controller.repositoriesByAspects;

import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.JpaRepositoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Put {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public UserModel putUser(UserModel userModel) {
        return jpaRepositoriesService.getUserRepository().save(userModel);
    }

    public SubModel putSubTask(SubModel subModel) {
        return jpaRepositoriesService.getSubTaskRepository().save(subModel);
    }

    public TaskModel putTask(TaskModel taskModel) {
        return jpaRepositoriesService.getTaskRepository().save(taskModel);
    }
}
