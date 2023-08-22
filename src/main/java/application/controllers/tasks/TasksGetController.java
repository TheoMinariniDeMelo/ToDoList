package application.controllers.tasks;

import application.exceptions.NotFoundDataException;
import application.models.TaskModel;
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

    @GetMapping("")
    protected ResponseEntity<List<TaskModel>> getTask(
            @RequestParam(value = "user") UUID id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "offSet", defaultValue = "0") int offSet
    ) {
        try {
            List<TaskModel> task;
            PageRequest pagination = PageRequest.of(page, offSet);
            if (title == null) task = Optional.of(taskRepository.findByUserId(id, pagination))
                    .orElseThrow(NotFoundDataException::new);
            else
                task = Optional.of(taskRepository.findByUserIdAndTitle(id, title, page, offSet)).orElseThrow(NotFoundDataException::new);
            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
