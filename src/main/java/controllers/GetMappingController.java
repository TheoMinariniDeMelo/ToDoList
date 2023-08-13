package controllers;

import exceptions.NotFoundRequestException;
import models.TaskModel;
import models.UserModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import repositories.TaskRepository;
import repositories.SubTaskRepository;
import repositories.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.GET)
public class GetMappingController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SubTaskRepository subTaskRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<Object> getUserController(
    ) {
        var users = userRepository.findAll();
        users.forEach(x ->
                x.add(linkTo(methodOn(GetMappingController.class).getUserIdController(x.getId())).withSelfRel())
        );
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserIdController(@PathVariable(value = "id") UUID id) {
        try {
            UserModel user = userRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            user.add(linkTo(methodOn(GetMappingController.class).getUserIdController(id)).withSelfRel());

            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: not possible to find user with the provided id");
        }
    }


    @GetMapping("/user/{id}/tasks")
    public ResponseEntity
            <Object> getTaskForUser(
            @PathVariable(value = "id") UUID id
    ) {
        var tasks = taskRepository.findByUserId(id);
        tasks.forEach(x -> x.add(linkTo(methodOn(GetMappingController.class).getTaskById(x.getId())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") UUID id) {
        try {
            var task = Optional.of(taskRepository.findById(id)).orElseThrow().stream().toList();
            task.forEach(x -> x.add(linkTo(methodOn(GetMappingController.class)
                    .getTaskForUser(id)).withRel(IanaLinkRelations.COLLECTION)));
            return ResponseEntity.status(HttpStatus.OK).body(task);

        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

    @GetMapping("/tasks/{id}/subtasks")
    public ResponseEntity<Object> getSubTaskController(@PathVariable(value = "id") UUID id) {
        try {
            var subtask = Optional.of(subTaskRepository.findByTaskId(id))
                    .orElseThrow(
                            () -> new NotFoundRequestException("Error: Not Found subtask for task id")
                    )
                    .stream().toList();
            subtask.forEach(x -> x.add(linkTo(methodOn(GetMappingController.class).getSubTaskController(x.getId())).withSelfRel()));
            return ResponseEntity.accepted().body(subtask);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }

    }

    @GetMapping("/tasks/subtasks/{id}")
    public ResponseEntity<Object> getSubTaskIdController(@PathVariable("id") UUID id) {
        try {
            var subTask = Optional.of(subTaskRepository.findById(id)).orElseThrow().stream().toList();
            subTask.forEach(x -> x.add(linkTo(methodOn(GetMappingController.class)
                    .getSubTaskIdController(x.getTaskId())).withRel(IanaLinkRelations.COLLECTION)));
            return ResponseEntity.status(HttpStatus.OK).body(subTask);
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }
}
