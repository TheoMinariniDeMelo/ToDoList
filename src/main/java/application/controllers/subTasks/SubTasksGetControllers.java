package application.controllers.subTasks;

import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.services.controller.repositoriesByAspects.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    protected ResponseEntity<Page<SubModel>> getSubTask(@RequestParam(value = "user") UUID id, @RequestParam(value = "title") String title,
                                                        @RequestParam(value = "page") int page, @RequestParam(value = "offset") int offset
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(page, offset);
            Page<SubModel> task;
            if (title == null) {
                task = get.findByTaskIdWithPagination(id, pageRequest);
            } else {
                task = get.findByTaskIdAndTitleWithPagination(id, title, pageRequest);
            }
            return ResponseEntity.ok().body(task);
        } catch (NotFoundDataException error) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    protected ResponseEntity<SubModel> getSubTaskById(@RequestParam(value = "task") UUID id) {
        return ResponseEntity.ok().body(get.findSubTaskById(id).get());
    }
}
