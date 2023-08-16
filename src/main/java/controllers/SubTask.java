package controllers;


import dto.SubTaskDTO;
import exceptions.NotFoundRequestException;
import jakarta.validation.Valid;
import models.SubTaskModel;
import models.TaskModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.GetMethods.Get;
import services.PostMethods.Post;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(name = "/subtasks")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubTask {
    @Autowired
    Get get;
    @Autowired
    Post post;

    @PostMapping("/subtasks")
    public ResponseEntity<Object> postSubTaskController(@RequestBody @Valid SubTaskDTO subTaskDto) {
        try {
            TaskModel task = get.findTaskById(subTaskDto.task())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            SubTaskModel subTask = new SubTaskModel();
            BeanUtils.copyProperties(subTaskDto, subTask);
            subTask.setTask(task);
            SubTaskModel savedSubTask = post.saveSubTask(subTask);

            return ResponseEntity.status(HttpStatus.OK).body(savedSubTask);
        } catch (ChangeSetPersister.NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Throwable error) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: not possible to persist data - " + error.getMessage());
        }
    }

    @GetMapping("/subtasks/{id}")
    public ResponseEntity<Object> getSubTaskById(@PathVariable(value = "id") UUID id) {
        try {
            SubTaskModel subtask = get.findSubTaskById(id).orElseThrow(() -> new NotFoundRequestException("Not found subtask"));
            subtask.add(linkTo(methodOn(Task.class).getSubTasksForTask(subtask.getTask().getId())).withRel(IanaLinkRelations.COLLECTION));
            return ResponseEntity.status(HttpStatus.OK).body(subtask);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
