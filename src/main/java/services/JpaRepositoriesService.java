package services;

import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SubTaskRepository;
import repositories.TaskRepository;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JpaRepositoriesService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public SubTaskRepository getSubTaskRepository() {
        return subTaskRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}
