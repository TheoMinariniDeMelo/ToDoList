package controllers;

import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutMappingController {

    @PutMapping("/users")
    public void putUserController(@RequestBody UserModel user){

    }

    @PutMapping("/tasks")
    public void putTaskController(@RequestBody ListPrimary task){

    }

    @PutMapping("/subtasks")
    public void putSubTaskController(@RequestBody ListSecondary subTask){

    }
}
