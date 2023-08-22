package application.controllers.subTasks;

import application.models.SubModel;
import application.services.controller.models.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPutControllers {
    @Autowired
    TaskService taskService;

    @PutMapping("/subtasks/{subTaskId}")
    public ResponseEntity<SubModel> updateSubTask(
            @PathVariable UUID subTaskId,
            @RequestParam String newTitle,
            @RequestParam String newDescription) {
        SubModel updatedSubTask = taskService.updateSubTaskTitleAndDescription(subTaskId, newTitle, newDescription);
        if (updatedSubTask != null) {
            return ResponseEntity.ok(updatedSubTask);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/subtasks/{subTaskId}/move")
    public ResponseEntity<SubModel> moveSubTask(
            @PathVariable UUID subTaskId,
            @RequestParam UUID newTaskId) {
        SubModel movedSubTask = taskService.moveSubTaskToNewTask(subTaskId, newTaskId);
        if (movedSubTask != null) {
            return ResponseEntity.ok(movedSubTask);
        }
        return ResponseEntity.notFound().build();
    }
}






