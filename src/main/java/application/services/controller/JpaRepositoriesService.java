package application.services.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import application.repositories.SubTaskRepository;
import application.repositories.TaskRepository;
import application.repositories.UserRepository;
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
