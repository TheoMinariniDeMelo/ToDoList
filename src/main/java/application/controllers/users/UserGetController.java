package application.controllers.users;

import application.models.UserModel;
import application.services.controller.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserGetController {
    @Autowired
    Get get;

    @GetMapping("")
    protected ResponseEntity<UserModel> getUserByEmail() {
        return null;
    }

}
