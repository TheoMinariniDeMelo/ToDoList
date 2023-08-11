package controllers;

import jakarta.transaction.Transactional;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PutMappingController {

    @Transactional
    @PutMapping("/users")
    public void putUserController(@RequestBody UserModel user){

    }
    @Transactional
    @PutMapping("/tasks")
    public void putTaskController(@RequestBody ListPrimary task){

    }
    @Transactional
    @PutMapping("/subtasks")
    public void putSubTaskController(@RequestBody ListSecondary subTask){

    }
}
