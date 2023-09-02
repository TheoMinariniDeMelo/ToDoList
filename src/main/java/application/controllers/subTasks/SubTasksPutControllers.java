package application.controllers.subTasks;

import application.dto.task.UpdateTitleAndDescriptionDto;
import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.UserModel;
import application.models.repositories.SubTaskRepository;
import application.services.controller.models.TaskService;
import application.services.security.SecurityContextUserHolder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPutControllers {
    @Autowired
    TaskService taskService;

    @Autowired
    SubTaskRepository subTaskRepository;
    @PutMapping("/{id}")
    public ResponseEntity<SubModel> updateSubTask(
            @PathVariable(value = "id") UUID subTaskId,
            @RequestBody @Valid UpdateTitleAndDescriptionDto updateTitleAndDescriptionDto) {
        SubModel updatedSubTask = taskService.updateSubTaskTitleAndDescription(subTaskId, updateTitleAndDescriptionDto.title(), updateTitleAndDescriptionDto.description());
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        ;
        if (updatedSubTask != null) {
            return ResponseEntity.ok(updatedSubTask);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{subTaskId}/move")
    public ResponseEntity<SubModel> moveSubTask(
            @PathVariable UUID subTaskId,
            @RequestParam(value = "task") UUID newTaskId) {
        SubModel movedSubTask = taskService.moveSubTaskToNewTask(subTaskId, newTaskId);
        if (movedSubTask != null) {
            return ResponseEntity.ok(movedSubTask);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/done")
    protected ResponseEntity<SubModel> doneSubTask(@RequestParam(value = "key") UUID id) {
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        try {
            SubModel subtask = subTaskRepository.findById(id).orElseThrow(NotFoundDataException::new);

            if (!Objects.equals(subtask.getTask().getUser(), userModel))
                throw new IncorrectCredentials("Incorrect Credentials");

            subtask.setState(2);

            return ResponseEntity.ok().body(subTaskRepository.save(subtask));
        } catch (NotFoundDataException | IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}






