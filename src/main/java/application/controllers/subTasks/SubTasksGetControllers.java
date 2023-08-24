package application.controllers.subTasks;

import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.repositories.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksGetControllers {
    @Autowired
    SubTaskRepository subTaskRepository;

    @GetMapping("")
    protected ResponseEntity<Page<SubModel>> getSubTask(@RequestParam(value = "user") UUID id, @RequestParam(value = "title") String title,
                                                        @RequestParam(value = "page") int page, @RequestParam(value = "offset") int offset
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(page, offset);
            Page<SubModel> task;
            if (title == null) {
                task = subTaskRepository.findByTaskId(id, pageRequest);
            } else {
                task = subTaskRepository.findByTaskIdAndTitleWithPagination(id, title, pageRequest);
            }
            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    protected ResponseEntity<SubModel> getSubTaskById(@RequestParam(value = "task") UUID id) {
        return ResponseEntity.ok().body(subTaskRepository.findById(id).get());
    }
}
