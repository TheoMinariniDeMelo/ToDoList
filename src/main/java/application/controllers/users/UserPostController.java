package application.controllers.users;

import application.models.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import application.services.controller.Get;
import application.services.controller.Post;
import application.dto.LoginDto;
import application.dto.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.services.security.PasswordEncoderBASE64;

@RestController
@RequestMapping("users")
public class UserPostController {
    @Autowired
    private Post post;

    @Autowired
    private Get get;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto login) {
        try {
            UserModel userModel = get.findByEmail(login.email()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            var usernamePassword = new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);
            return ResponseEntity.ok().body(auth);
        } catch (ChangeSetPersister.NotFoundException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto register) {
        try {
            if (get.findByEmail(register.email()).isPresent()) throw new Exception("E-mail already registered");
            UserModel user = new UserModel();
            BeanUtils.copyProperties(register, user);
            user.setPassword(PasswordEncoderBASE64.passwordEncoder(user.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveUser(user));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }
}
