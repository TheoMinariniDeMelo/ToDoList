package application.controllers.tasks;

import application.dto.task.UpdateTitleAndDescriptionDto;
import application.models.TaskModel;
import application.services.controller.models.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskPutController {
    @Autowired
    TaskService taskService;

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskModel> updateTask(
            @PathVariable UUID taskId,
            @RequestBody @Valid UpdateTitleAndDescriptionDto updateTitleAndDescriptionDto) {
        TaskModel updatedTask = taskService.updateTaskTitleAndDescription(taskId, updateTitleAndDescriptionDto.title(), updateTitleAndDescriptionDto.description());
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
}
