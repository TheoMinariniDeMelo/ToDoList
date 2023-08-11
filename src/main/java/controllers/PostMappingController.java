package controllers;


import jakarta.transaction.Transactional;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostMappingController {
    
    @PostMapping("/users")
    public void postUserController(@RequestBody UserModel user) {

    }

    @PostMapping("/tasks")
    public void postTaskController(@RequestBody ListPrimary task) {

    }

    @PostMapping("/subtasks")
    public void postSubTaskController(@RequestBody ListSecondary subTask) {

    }
}
