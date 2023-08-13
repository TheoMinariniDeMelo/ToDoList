package controllers;

import dto.TaskDTO;
import dto.SubTaskDTO;
import dto.UserDTO;
import exceptions.PersistenceException;
import jakarta.validation.Valid;
import models.TaskModel;
import models.SubTaskModel;
import models.UserModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.TaskRepository;
import repositories.SubTaskRepository;
import repositories.UserRepository;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.POST)
public class PostMappingController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SubTaskRepository subTaskRepository;

    @PostMapping("/users")
    public ResponseEntity<Object> postUserController(@RequestBody @Valid UserDTO userDto) {
        UserModel user = new UserModel();
        try {
            boolean emailExists = Objects.isNull(userRepository.findByEmail(user.getEmail()));
            if (!emailExists) {
                throw new PersistenceException("The User already exist");
            }
            BeanUtils.copyProperties(userDto, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (PersistenceException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + " /Error, not possible persistence data");
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> postTaskController(@RequestBody @Valid TaskDTO taskDto) {
        TaskModel task = new TaskModel();
        try {
            if (userRepository.findById(taskDto.userId()).isEmpty())
                throw new PersistenceException("The User not exist");
            BeanUtils.copyProperties(taskDto, task);
            return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
        } catch (PersistenceException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }


    @PostMapping("/subtasks")
    public ResponseEntity<Object> postSubTaskController(@RequestBody @Valid SubTaskDTO subTaskDto) {
        SubTaskModel subTask = new SubTaskModel();
        try {
            if (userRepository.findById(subTaskDto.taskId()).isEmpty())
                throw new PersistenceException("The task not exist");
            BeanUtils.copyProperties(subTaskDto, subTask);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(subTaskRepository.save(subTask));
        } catch (PersistenceException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + "Error: not possible persistence data");
        }
    }
}
