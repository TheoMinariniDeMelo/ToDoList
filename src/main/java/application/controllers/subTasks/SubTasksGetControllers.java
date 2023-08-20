package application.controllers.subTasks;

import application.models.SubModel;
import application.repositories.SubTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksGetControllers {
    @Autowired
    SubTaskRepository subTaskRepository;

    @GetMapping("")
    protected ResponseEntity<List<SubModel>> getTask(@RequestParam(value = "user") UUID id,
                                                     @RequestParam(value = "title") String title,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "offset") int offset
    ) {
        try {
            List<SubModel> task;
            if (title == null) task = Optional.of(subTaskRepository.findByTaskId(id))
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            else
                task = Optional.of(subTaskRepository.findByTaskIdAndTitleWithPagination(id, title, page, offset)).orElseThrow(ChangeSetPersister.NotFoundException::new);

            return ResponseEntity.ok().body(task);
        } catch (ChangeSetPersister.NotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }
}
