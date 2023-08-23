package application.controllers.subTasks;


import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.SubModel;
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
@RequestMapping("/subTasks")
public class SubTasksDeleteControllers {
    @Autowired
    Get get;
    @Autowired
    Post post;
    @Autowired
    Delete delete;

    @DeleteMapping("/deleted")
    protected ResponseEntity<SubModel> deletedSubTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            SubModel subtask = get.findSubTaskById(id).orElseThrow(NotFoundDataException::new);
            if (Objects.equals(subtask.getTask().getUser(), userModel)) {
                throw new IncorrectCredentials("Incorrect Credentials");
            }
            ;
            subtask.setState(3);
            return ResponseEntity.ok().body(post.saveSubTask(subtask));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    protected ResponseEntity<TaskModel> deleteSubTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            SubModel subtask = get.findSubTaskById(id).orElseThrow(NotFoundDataException::new);
            if (Objects.equals(subtask.getTask().getUser(), userModel)) {
                throw new IncorrectCredentials("Incorrect Credentials");
            }
            ;
            delete.deleteSubTask(subtask);
            return ResponseEntity.ok().build();
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
