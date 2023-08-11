package controllers;

import jakarta.transaction.Transactional;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatchMappingController {

    @Transactional
    @PatchMapping("/users")
    public void patchUserController(@RequestBody UserModel user) {

    }

    @Transactional
    @PatchMapping("/tasks")
    public void patchTaskController(@RequestBody ListPrimary task) {

    }

    @Transactional
    @PatchMapping("/subtasks")
    public void patchSubTaskController(@RequestBody ListSecondary subTask) {

    }
}
