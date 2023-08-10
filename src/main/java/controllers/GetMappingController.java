package controllers;

import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetMappingController {
    @GetMapping("/users")
    public void getUserController(@RequestBody UserModel user){

    }

    @GetMapping("/tasks")
    public void getTaskController(@RequestBody ListPrimary task){

    }

    @GetMapping("/subtasks")
    public void getSubTaskController(@RequestBody ListSecondary subTask){

    }
}
