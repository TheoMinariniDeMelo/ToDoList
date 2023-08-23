package application.services.controller.repositoriesByAspects;

import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Delete {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public void deleteTask(TaskModel taskModel) {
        jpaRepositoriesService.getTaskRepository().delete(taskModel);
    }

    public void deleteSubTask(SubModel subModel) {
        jpaRepositoriesService.getSubTaskRepository().delete(subModel);
    }

    public void deleteUser(UserModel userModel) {
        jpaRepositoriesService.getUserRepository().delete(userModel);
    }
}
