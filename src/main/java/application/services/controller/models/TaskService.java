package application.services.controller.models;

import application.exceptions.NotFoundDataException;
import application.models.SubModel;
import application.models.TaskModel;
import application.models.repositories.SubTaskRepository;
import application.models.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubTaskRepository subTaskRepository;

    public TaskModel updateTaskTitleAndDescription(UUID taskId, String newTitle, String newDescription) {
        TaskModel task = taskRepository.findById(taskId).orElseThrow(NotFoundDataException::new);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        return taskRepository.save(task);
    }

    public SubModel updateSubTaskTitleAndDescription(UUID subTaskId, String newTitle, String newDescription) {
        Optional<SubModel> optionalSubTask = subTaskRepository.findById(subTaskId);
        if (optionalSubTask.isPresent()) {
            SubModel subTask = optionalSubTask.get();
            subTask.setTitle(newTitle);
            subTask.setDescription(newDescription);
            return subTaskRepository.save(subTask);
        }
        return null;
    }

    public SubModel moveSubTaskToNewTask(UUID subTaskId, UUID newTaskId) {
        Optional<SubModel> optionalSubTask = subTaskRepository.findById(subTaskId);
        Optional<TaskModel> optionalNewTask = taskRepository.findById(newTaskId);

        if (optionalSubTask.isPresent() && optionalNewTask.isPresent()) {
            SubModel subTask = optionalSubTask.get();
            TaskModel newTask = optionalNewTask.get();

            subTask.setTask(newTask);
            return subTaskRepository.save(subTask);
        }
        return null;
    }
}
