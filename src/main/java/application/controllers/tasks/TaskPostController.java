package application.controllers.tasks;

import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.Get;
import application.services.controller.Post;
import application.dto.TaskDto;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
            UserModel user = Optional.of(get.findById(taskDto.user())).orElseThrow(ChangeSetPersister.NotFoundException::new);
            TaskModel taskModel = new TaskModel();
            BeanUtils.copyProperties(taskDto, taskModel);
            taskModel.setUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveTask(taskModel));
        } catch (ChangeSetPersister.NotFoundException | PersistenceException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
