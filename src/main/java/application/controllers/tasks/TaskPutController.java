package application.controllers.tasks;

import application.models.TaskModel;
import application.services.controller.models.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TaskPutController {
    @Autowired
    TaskService taskService;

    @PutMapping("/tasks/update/{taskId}")
    public ResponseEntity<TaskModel> updateTask(
            @PathVariable UUID taskId,
            @RequestBody String newTitle,
            @RequestBody String newDescription) {
        TaskModel updatedTask = taskService.updateTaskTitleAndDescription(taskId, newTitle, newDescription);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
}
