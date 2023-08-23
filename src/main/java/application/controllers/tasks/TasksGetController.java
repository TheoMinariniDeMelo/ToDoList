package application.controllers.tasks;

import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.security.SecurityContextUserHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import application.models.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksGetController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Get get;

    @GetMapping("/source")
    public ResponseEntity<Page<TaskModel>> getTask(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        try {
            Page<TaskModel> task;
            PageRequest pagination = PageRequest.of(page, pageSize); // Use pageSize instead of offSet

            UserModel userModel = SecurityContextUserHolder.securityUserHolder();
            UUID id = get.findByEmail(userModel.getEmail()).orElseThrow(NotFoundDataException::new).getId();
            if (title == null)
                task = get.findByUserId(id, pagination);
            else
                task = get.findByUserIdAndTitleContaining(id, title, pagination);

            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
