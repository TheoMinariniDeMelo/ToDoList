package application.controllers.subTasks;

import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.TaskModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Post;
import application.dto.subTask.SubTaskDto;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subTasks")
public class SubTasksPostControllers {
    @Autowired
    Get get;
    @Autowired
    Post post;

    @PostMapping("")
    protected ResponseEntity<SubModel> createSubTask(@RequestBody @Valid SubTaskDto subModelDto) {
        try {
            TaskModel taskModel = get.findTaskById(subModelDto.task()).orElseThrow(NotFoundDataException::new);
            SubModel subModel = new SubModel();
            BeanUtils.copyProperties(subModelDto, subModel);
            subModel.setTask(taskModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveSubTask(subModel));
        } catch (NotFoundDataException | PersistenceException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
