package controllers;

import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatchMappingController {

    @PatchMapping("/users")
    public void patchUserController(@RequestBody UserModel user){

    }

    @PatchMapping("/tasks")
    public void patchTaskController(@RequestBody ListPrimary task){

    }

    @PatchMapping("/subtasks")
    public void patchSubTaskController(@RequestBody ListSecondary subTask){

    }
}
