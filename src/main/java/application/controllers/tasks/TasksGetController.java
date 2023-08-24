package application.controllers.tasks;

import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
import application.models.UserModel;
import application.models.repositories.TaskRepository;
import application.models.repositories.UserRepository;
import application.services.security.SecurityContextUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksGetController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

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
            UUID id = userRepository.findByEmail(userModel.getEmail()).orElseThrow(NotFoundDataException::new).getId();
            if (title == null)
                task = taskRepository.findByUserId(id, pagination);
            else
                task = taskRepository.findByUserIdAndTitleContaining(id, title, pagination);

            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
