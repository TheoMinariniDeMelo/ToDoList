package controllers;

import dto.TaskDTO;
import exceptions.NotFoundRequestException;
import exceptions.PersistenceException;
import jakarta.validation.Valid;
import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.DeleteMethods.Delete;
import services.GetMethods.Get;
import services.PostMethods.Post;
import services.PutMethods.Put;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/tasks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Task {

    @Autowired
    Post post;
    @Autowired
    Get get;
    @Autowired
    Delete delete;
    @Autowired
    Put put;

    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "taskId") UUID id) {
        try {
            Optional<TaskModel> task = get.findTaskById(id);
            task.orElseThrow(() -> new NotFoundRequestException("Task not found with the provided id"));

            task.get().add(linkTo(methodOn(User.class).getTasksForUser(task.get().getUser().getId())).withRel(IanaLinkRelations.COLLECTION));

            task.get().getUser().add(linkTo(methodOn(User.class).getUserById(task.get().getUser().getId())).withSelfRel());

            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/{id}/subtasks")
    public ResponseEntity<Object> getSubTasksForTask(@PathVariable(value = "id") UUID id) {
        List<SubTaskModel> subtasks = get.findSubTaskByIdTask(id);
        if (subtasks.isEmpty()) return ResponseEntity.badRequest().body("Not found subtasks");
        return ResponseEntity.status(HttpStatus.OK).body(subtasks);
    }

    @PostMapping("")
    public ResponseEntity<Object> postTaskController(@RequestBody @Valid TaskDTO taskDto) {
        try {
            UserModel user = Optional.of(get.findById(taskDto.userId()))
                    .orElseThrow(() -> new PersistenceException("The User does not exist"));
            TaskModel task = new TaskModel();
            BeanUtils.copyProperties(taskDto, task);
            task.setUser(user);
            TaskModel savedTask = post.saveTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (PersistenceException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskController(@PathVariable(name = "id") UUID id) throws NotFoundRequestException {
        try {
            TaskModel task = Optional.of(get.findTaskById(id)).orElseThrow(() -> new NotFoundRequestException("Not found task by id provided")).get();
            delete.deleteTask(task.getId());
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
