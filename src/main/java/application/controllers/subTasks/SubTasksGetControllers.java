package application.controllers.subTasks;

import application.exceptions.IncorrectCredentials;
import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.repositories.SubTaskRepository;
import application.models.repositories.TaskRepository;
import application.services.security.SecurityContextUserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksGetControllers {
    @Autowired
    SubTaskRepository subTaskRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/source")
    protected ResponseEntity<Page<SubModel>> getSubTask(@RequestParam(value = "task") UUID id, @RequestParam(value = "title") String title,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(page, pageSize);
            var email = taskRepository.findById(id).orElseThrow(NotFoundDataException::new).getUser().getEmail();
            if (!Objects.equals(email, SecurityContextUserHolder.securityUserHolder().getEmail()))
                throw new IncorrectCredentials("error");
            Page<SubModel> task;
            if (title == null) {
                task = subTaskRepository.findByTaskId(id, pageRequest);
            } else {
                task = subTaskRepository.findByTaskIdAndTitleWithPagination(id, title, pageRequest);
            }
            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException | IncorrectCredentials error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/source/{id}")
    protected ResponseEntity<SubModel> getSubTaskById(@PathVariable(value = "task") UUID id) {
        return ResponseEntity.ok().body(subTaskRepository.findById(id).get());
    }
}
