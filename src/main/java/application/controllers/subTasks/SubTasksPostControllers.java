package application.controllers.subTasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.TaskModel;
import application.models.UserModel;
import application.models.repositories.SubTaskRepository;
import application.models.repositories.TaskRepository;
import application.dto.subTask.CreateDto;
import application.services.security.SecurityContextUserHolder;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPostControllers {
    @Autowired
    SubTaskRepository subTaskRepository;
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("")
    protected ResponseEntity<SubModel> createSubTask(@RequestBody @Valid CreateDto subModelDto) {
        try {
            TaskModel task = taskRepository.findById(subModelDto.task()).orElseThrow(NotFoundDataException::new);

            var email = SecurityContextUserHolder.securityUserHolder().getEmail() ;

            if (!Objects.equals(email, task.getUser().getEmail())) throw new NotFoundDataException();

            SubModel subModel = new SubModel();
            BeanUtils.copyProperties(subModelDto, subModel);
            subModel.setTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(subTaskRepository.save(subModel));
        } catch (NotFoundDataException | PersistenceException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/done")
    protected ResponseEntity<SubModel> doneSubTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            SubModel subtask = subTaskRepository.findById(id).orElseThrow(NotFoundDataException::new);

            if (!Objects.equals(subtask.getTask().getUser(), userModel))
                throw new IncorrectCredentials("Incorrect Credentials");

            subtask.setState(2);

            return ResponseEntity.ok().body(subTaskRepository.save(subtask));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
