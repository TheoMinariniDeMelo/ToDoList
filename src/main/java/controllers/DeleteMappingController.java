package controllers;

import jakarta.transaction.Transactional;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(method = RequestMethod.DELETE)
public class DeleteMappingController {
    @Transactional
    @DeleteMapping("/users")
    public void deleteUserController(@RequestBody UserModel user) {
    }

    @Transactional
    @DeleteMapping("/tasks")
    public void deleteTaskController(@RequestBody ListPrimary task) {

    }

    @Transactional
    @DeleteMapping("/tasks/subtasks")
    public void deleteSubTaskController(@RequestBody ListSecondary subTask) {

    }
}
