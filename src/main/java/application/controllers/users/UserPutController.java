package application.controllers.users;

import application.dto.user.UpdateDto;
import application.models.UserModel;
import application.services.controller.repositoriesByAspects.Get;
import application.services.controller.repositoriesByAspects.Put;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class UserPutController {
    @Autowired
    private Get get;
    @Autowired
    private Put put;
    @Autowired
    private AuthenticationManager authenticationManager;


    @PutMapping("/update")
    protected ResponseEntity updateUser(@RequestBody @Valid UpdateDto updateDto) {
        try {
            String email = ((UserModel) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
            var token = new UsernamePasswordAuthenticationToken(email, updateDto.password());
            authenticationManager.authenticate(token);
            return null;
        } catch (Exception error) {
            return null;
        }
    }


}

