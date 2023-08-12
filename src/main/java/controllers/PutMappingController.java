package controllers;

import jakarta.transaction.Transactional;
import models.TaskModel;
import models.SubTaskModel;
import models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.PUT)
public class PutMappingController {
    @Transactional
    @PutMapping("/users")
    public void putUserController(@RequestBody UserModel user) {}

    @Transactional
    @PutMapping("/tasks")
    public void putTaskController(@RequestBody TaskModel task) {}

    @Transactional
    @PutMapping("/subtasks")
    public void putSubTaskController(@RequestBody SubTaskModel subTask) {}
}
