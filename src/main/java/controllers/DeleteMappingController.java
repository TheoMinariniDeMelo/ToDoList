package controllers;

import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteMappingController {

    @DeleteMapping("/users")
    public void deleteUserController(@RequestBody UserModel user){

    }

    @DeleteMapping("/tasks")
    public void deleteTaskController(@RequestBody ListPrimary task){

    }

    @DeleteMapping("/subtasks")
    public void deleteSubTaskController(@RequestBody ListSecondary subTask){

    }
}
