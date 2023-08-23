package application.controllers.subTasks;

import application.models.SubModel;
import application.models.UserModel;
import application.services.controller.models.TaskService;
import application.services.security.SecurityContextUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPutControllers {
    @Autowired
    TaskService taskService;

    @PutMapping("/subtasks/update/{id}")
    public ResponseEntity<SubModel> updateSubTask(
            @PathVariable(value = "id") UUID subTaskId,
            @RequestBody String newTitle,
            @RequestBody String newDescription) {
        SubModel updatedSubTask = taskService.updateSubTaskTitleAndDescription(subTaskId, newTitle, newDescription);
        UserModel userModel = SecurityContextUserHolder.securityUserHolder();
        ;
        if (updatedSubTask != null) {
            return ResponseEntity.ok(updatedSubTask);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/subtasks/update/{subTaskId}/move")
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






