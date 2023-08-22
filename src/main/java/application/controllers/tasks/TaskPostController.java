package application.controllers.tasks;

import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Post;
import application.dto.task.TaskDto;
import application.services.security.SecurityContextUserHolder;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskPostController {
    @Autowired
    Get get;
    @Autowired
    Post post;

    @PostMapping("")
    protected ResponseEntity<TaskModel> createTask(@RequestBody @Valid TaskDto taskDto) {
        try {
            UserModel userContext = SecurityContextUserHolder.securityUserHolder();
            UserModel user = get.findByEmail(userContext.getEmail()).orElseThrow(NotFoundDataException::new);
            TaskModel taskModel = new TaskModel();
            BeanUtils.copyProperties(taskDto, taskModel);
            taskModel.setUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveTask(taskModel));
        } catch (NotFoundDataException | PersistenceException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
