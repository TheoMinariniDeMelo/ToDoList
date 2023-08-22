package application.services.controller.repositoriesByAspects;

import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.JpaRepositoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SubModel saveSubTask(SubModel subTask) {
        return jpaRepositoriesService.getSubTaskRepository().save(subTask);
    }

}