package application.controllers.subTasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Post;
import application.dto.subTask.SubTaskDto;
import application.services.security.SecurityContextUserHolder;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPostControllers {
    @Autowired
    Get get;
    @Autowired
    Post post;

    @PostMapping("")
    protected ResponseEntity<SubModel> createSubTask(@RequestBody @Valid SubTaskDto subModelDto) {
        try {
            TaskModel taskModel = get.findTaskById(subModelDto.task()).orElseThrow(NotFoundDataException::new);
            var email = SecurityContextUserHolder.securityUserHolder().getEmail();
            if (!Objects.equals(email, taskModel.getUser().getEmail())) throw new NotFoundDataException();
            SubModel subModel = new SubModel();
            BeanUtils.copyProperties(subModelDto, subModel);
            subModel.setTask(taskModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveSubTask(subModel));
        } catch (NotFoundDataException | PersistenceException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/done")
    protected ResponseEntity<SubModel> doneSubTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            SubModel subtask = get.findSubTaskById(id).orElseThrow(NotFoundDataException::new);

            if (!Objects.equals(subtask.getTask().getUser(), userModel))
                throw new IncorrectCredentials("Incorrect Credentials");

            subtask.setState(2);

            return ResponseEntity.ok().body(post.saveSubTask(subtask));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
