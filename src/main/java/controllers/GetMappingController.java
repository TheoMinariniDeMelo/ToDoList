package controllers;


import models.TaskModel;

import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int offset) {

        PageRequest pages = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll(pages));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserIdController(@PathVariable UUID id) {
        try {
            UserModel user = userRepository.findById(id).orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity
            <Page<TaskModel>> getPage(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int offset) {
        PageRequest pageRequest = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findAll(pageRequest));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "id") UUID id) {
        try {
            var user = Optional.of(taskRepository.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

    @GetMapping("/tasks/subtasks")
    public ResponseEntity<Object> getSubTaskController(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int offset) {
        PageRequest pageRequest = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(subTaskRepository.findAll(pageRequest));
    }

    @GetMapping("/tasks/subtasks/{id}")
    public ResponseEntity<Object> getSubTaskIdController(@PathVariable("id") UUID id) {
        try {
            var user = Optional.of(subTaskRepository.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

}
