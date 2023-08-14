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
import services.GetMethods.Get;
import services.PostMethods.Post;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.POST)
public class PostMappingController {

    @Autowired
    Post post;
    @Autowired
    Get get;

    @PostMapping("/users")
    public ResponseEntity<Object> postUserController(@RequestBody @Valid UserDTO userDto) {
        UserModel user = new UserModel();
        try {
            boolean emailExists = Objects.isNull(get.findByEmail(user.getEmail()));
            if (!emailExists) {
                throw new PersistenceException("The User already exist");
            }
            BeanUtils.copyProperties(userDto, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveUser(user));
        } catch (PersistenceException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + " /Error, not possible persistence data");
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> postTaskController(@RequestBody @Valid TaskDTO taskDto) {
        try {
            UserModel user = Optional.of(get.findById(taskDto.userId()))
                    .orElseThrow(() -> new PersistenceException("The User does not exist"));
            TaskModel task = new TaskModel();
            BeanUtils.copyProperties(taskDto, task);
            TaskModel savedTask = post.saveTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (PersistenceException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PostMapping("/subtasks")
    public ResponseEntity<Object> postSubTaskController(@RequestBody @Valid SubTaskDTO subTaskDto) {
        SubTaskModel subTask = new SubTaskModel();
        try {
            if (get.findTaskById(subTaskDto.taskId()).isEmpty())
                throw new PersistenceException("The task not exist");
            BeanUtils.copyProperties(subTaskDto, subTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveSubTask(subTask));
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + "Error: not possible persistence data");
        }
    }
}