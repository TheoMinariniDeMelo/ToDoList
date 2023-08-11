package controllers;

import jakarta.transaction.Transactional;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
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
