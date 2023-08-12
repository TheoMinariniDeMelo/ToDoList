package controllers;

import dto.ListPrimaryDto;
import dto.ListSecondaryDto;
import dto.UserModelDto;
import exceptions.ErrorPersistenceException;
import jakarta.validation.Valid;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ListPrimaryRepository;
import repositories.ListSecondaryRepository;
import repositories.UserRepository;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(method = RequestMethod.POST)
public class PostMappingController {

    @Autowired
    ListPrimaryRepository listPrimaryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ListSecondaryRepository listSecondaryRepository;

    @PostMapping("/users")
    public ResponseEntity<Object> postUserController(@RequestBody @Valid UserModelDto userDto) {
        UserModel user = new UserModel();
        try {
            boolean emailExists = Objects.isNull(userRepository.findByEmail(user.getEmail()));
            if (!emailExists){
                throw new ErrorPersistenceException("The User already exist");
            }

            BeanUtils.copyProperties(userDto, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (RuntimeException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + " /Error, not possible persistence data");
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> postTaskController(@RequestBody @Valid ListPrimaryDto taskDto) {
        ListPrimary task = new ListPrimary();
        try {
            boolean userExists = userRepository.existsById(taskDto.user_id());
            if (!userExists){
                throw new ErrorPersistenceException("The User not exist");
            }

            BeanUtils.copyProperties(taskDto, task);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(listPrimaryRepository.save(task));
        } catch (RuntimeException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + "Error, not possible persistence data");
        }
    }


    @PostMapping("/subtasks")
    public ResponseEntity<Object> postSubTaskController(@RequestBody @Valid ListSecondaryDto subTaskDto) {
        ListSecondary subTask = new ListSecondary();
        try {
            boolean taskExists = userRepository.existsById(subTaskDto.task_id());

            if (taskExists){
                throw new ErrorPersistenceException("The task not exist");
            }

            BeanUtils.copyProperties(subTaskDto, subTask);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(listSecondaryRepository.save(subTask));
        } catch (RuntimeException error) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(error.getMessage() + "Error, not possible persistence data");
        }
    }
}
