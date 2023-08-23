package application.controllers.tasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Delete;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Post;
import application.services.security.SecurityContextUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;


@RestController
@RequestMapping("tasks")
public class TaskDeleteController {
    @Autowired
    Delete delete;
    @Autowired
    Get get;
    @Autowired
    Post post;

    @DeleteMapping("/deleted")
    protected ResponseEntity<TaskModel> deletedTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            TaskModel task = get.findTaskById(id).orElseThrow(NotFoundDataException::new);
            if (Objects.equals(task.getUser(), userModel)) {
                throw new IncorrectCredentials("Incorrect Credentials");
            }
            ;
            task.setState(3);
            return ResponseEntity.ok().body(post.saveTask(task));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    protected ResponseEntity<TaskModel> deleteTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            TaskModel task = get.findTaskById(id).orElseThrow(NotFoundDataException::new);
            if (Objects.equals(task.getUser(), userModel)) {
                throw new IncorrectCredentials("Incorrect Credentials");
            }
            ;
            delete.deleteTask(task);
            return ResponseEntity.ok().build();
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
