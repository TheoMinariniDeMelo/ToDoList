package application.services.controller.models;

import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.TaskModel;
import application.models.repositories.SubTaskRepository;
import application.models.repositories.TaskRepository;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    Get get;
    @Autowired
    Post post;

    public TaskModel updateTaskTitleAndDescription(UUID taskId, String newTitle, String newDescription) {
        TaskModel task = get.findTaskById(taskId).orElseThrow(NotFoundDataException::new);
        task.setTitle(newTitle);
        task.setDescribe(newDescription);
        return post.saveTask(task);
    }

    public SubModel updateSubTaskTitleAndDescription(UUID subTaskId, String newTitle, String newDescription) {
        Optional<SubModel> optionalSubTask = get.findSubTaskById(subTaskId);
        if (optionalSubTask.isPresent()) {
            SubModel subTask = optionalSubTask.get();
            subTask.setTitle(newTitle);
            subTask.setDescribe(newDescription);
            return subTaskRepository.save(subTask);
        }
        return null;
    }

    public SubModel moveSubTaskToNewTask(UUID subTaskId, UUID newTaskId) {
        Optional<SubModel> optionalSubTask = get.findSubTaskById(subTaskId);
        Optional<TaskModel> optionalNewTask = get.findTaskById(newTaskId);

        if (optionalSubTask.isPresent() && optionalNewTask.isPresent()) {
            SubModel subTask = optionalSubTask.get();
            TaskModel newTask = optionalNewTask.get();

            subTask.setTask(newTask);
            return post.saveSubTask(subTask);
        }
        return null;
    }
}
