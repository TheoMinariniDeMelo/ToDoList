package application.services.controller.repositoriesByAspects;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import application.models.repositories.SubTaskRepository;
import application.models.repositories.TaskRepository;
import application.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Getter
@Service
public class JpaRepositoriesService {
    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected SubTaskRepository subTaskRepository;

    @Autowired
    protected UserRepository userRepository;


}
