package application.controllers.subTasks;

import application.dto.task.UpdateTitleAndDescriptionDto;
import application.models.SubModel;
import application.models.UserModel;
import application.services.controller.models.TaskService;
import application.services.security.SecurityContextUserHolder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPutControllers {
    @Autowired
    TaskService taskService;

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
}






