package controllers;

import dto.AuthenticationDto;
import dto.UserDTO;
import exceptions.NotFoundRequestException;
import exceptions.PersistenceException;
import jakarta.validation.Valid;
import models.SubTaskModel;
import models.TaskModel;
import models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import services.DeleteMethods.Delete;
import services.GetMethods.Get;
import services.PostMethods.BCrypt;
import services.PostMethods.Post;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class User {
    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    Get get;
    @Autowired
    Post post;
    @Autowired
    Delete delete;

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var token = new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.password());
        return ResponseEntity.ok().body(this.authenticationManager.authenticate(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> postUserController(@RequestBody @Valid UserDTO userDto) {
        UserModel user = new UserModel();
        try {
            boolean emailExists = Objects.isNull(get.findByEmail(user.getEmail()));
            if (!emailExists) throw new PersistenceException("The User already exist");

            BeanUtils.copyProperties(userDto, user);

            user.setPassword(BCrypt.Encoder(user.getPassword()));

            return ResponseEntity.status(HttpStatus.CREATED).body(post.saveUser(user));
        } catch (PersistenceException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + " /Error, not possible persistence data");
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllUsers() {
        List<UserModel> users = get.findAll();
        users.forEach(user -> user.add(linkTo(methodOn(User.class).getUserById(user.getId())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "id") UUID id) {
        try {
            UserModel user = Optional.of(get.findById(id)).orElseThrow(
                    () -> new NotFoundRequestException("Not Found User with the provided id")
            );
            user.add(linkTo(methodOn(User.class).getAllUsers())
                    .withRel(IanaLinkRelations.COLLECTION)
            );
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<Object> getTasksForUser(@PathVariable(value = "id") UUID id) {
        try {
            if (!get.existsById(id)) throw new NotFoundRequestException("User not found");

            List<TaskModel> tasks = get.findByUserId(id);
            tasks.forEach(task -> {
                task.add(linkTo(methodOn(Task.class).getTaskById(task.getId())).withSelfRel());
                task.getUser().add(linkTo(methodOn(User.class).getUserById(task.getUser().getId())).withSelfRel());
            });

            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        } catch (NotFoundRequestException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskController(@PathVariable(name = "id") UUID id) throws NotFoundRequestException {
        try {
            UserModel user = Optional.of(get.findById(id)).orElseThrow(() -> new NotFoundRequestException("Not found task by id provided"));
            delete.deleteTask(user.getId());
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
