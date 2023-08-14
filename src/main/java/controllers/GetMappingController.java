package controllers;

import exceptions.NotFoundRequestException;
import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.GetMethods.Get;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.GET)
public class GetMappingController {

    @Autowired
    Get get;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        List<UserModel> users = get.findAll();
        users.forEach(user -> user.add(linkTo(methodOn(GetMappingController.class).getUserById(user.getId())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID id) {
        try {
            UserModel user = Optional.of(get.findById(id)).orElseThrow(
                    () -> new NotFoundRequestException("Not Found User with the provided id")
            );
            user.add(linkTo(methodOn(GetMappingController.class).getAllUsers())
                    .withRel(IanaLinkRelations.COLLECTION)
            );
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/users/{id}/tasks")
    public ResponseEntity<Object> getTasksForUser(@PathVariable(value = "id") UUID id) {
        try {
            if (!get.existsById(id)) throw new NotFoundRequestException("User not found");

            List<TaskModel> tasks = get.findByUserId(id);
            tasks.forEach(task -> task.add(linkTo(methodOn(GetMappingController.class).getTaskById(task.getId())).withSelfRel()));

            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") UUID id) {
        try {
            Optional<TaskModel> task = get.findTaskById(id);
            task.orElseThrow(() -> new NotFoundRequestException("Task not found with the provided id"));
            task.get().add(linkTo(methodOn(GetMappingController.class).getTasksForUser(task.get().getUserId())).withRel(IanaLinkRelations.COLLECTION));
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/tasks/{id}/subtasks")
    public ResponseEntity<Object> getSubTasksForTask(@PathVariable(value = "id") UUID id) {
        List<SubTaskModel> subtasks = get.findSubTaskByIdTask(id);
        if (subtasks.isEmpty()) return ResponseEntity.badRequest().body("Not found subtasks");
        return ResponseEntity.status(HttpStatus.OK).body(subtasks);
    }

    @GetMapping("/subtasks/{id}")
    public ResponseEntity<Object> getSubTaskById(@PathVariable(value = "id") UUID id) {
        try {
            SubTaskModel subtask = get.findSubTaskById(id).orElseThrow(() -> new NotFoundRequestException("Not found subtask"));
            subtask.add(linkTo(methodOn(GetMappingController.class).getSubTasksForTask(subtask.getTaskId())).withRel(IanaLinkRelations.COLLECTION));
            return ResponseEntity.status(HttpStatus.OK).body(subtask);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
