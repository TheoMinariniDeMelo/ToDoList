package services.DeleteMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.JpaRepositoriesService;

import java.util.UUID;

@Service
public class Delete {
    @Autowired
    JpaRepositoriesService jpaRepositoriesService;

    public void deleteTask(UUID id) {
        jpaRepositoriesService.getTaskRepository().deleteById(id);
    }

    public void deleteSubTask(UUID id) {
        jpaRepositoriesService.getSubTaskRepository().deleteById(id);
    }

    public void deleteUser(UUID id) {
        jpaRepositoriesService.getUserRepository().deleteById(id);
    }
}
