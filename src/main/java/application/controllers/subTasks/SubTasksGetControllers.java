package application.controllers.subTasks;

import application.models.SubModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/subTasks")
public class SubTasksGetControllers {
    @Autowired
    Get get;

    @GetMapping("")
    protected ResponseEntity<List<SubModel>> getSubTask(@RequestParam(value = "user") UUID id, @RequestParam(value = "title") String title,
                                                        @RequestParam(value = "page") int page, @RequestParam(value = "offset") int offset
    ) {
        try {
            List<SubModel> task;
            if (title == null) {
                task = Optional.of(get.findByTaskId(id)).orElseThrow(ChangeSetPersister.NotFoundException::new);
            } else {
                task = Optional.of(get.findByTaskIdAndTitleWithPagination(id, title, page, offset)).orElseThrow(ChangeSetPersister.NotFoundException::new);
            }
            return ResponseEntity.ok().body(task);
        } catch (ChangeSetPersister.NotFoundException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    protected ResponseEntity<SubModel> getSubTaskById(@RequestParam(value = "task") UUID id) {
        return ResponseEntity.ok().body(get.findSubTaskById(id).get());
    }
}
