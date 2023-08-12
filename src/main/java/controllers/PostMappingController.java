package controllers;

import dto.ListPrimaryDto;
import dto.ListSecondaryDto;
import dto.UserModelDto;
import exceptions.ErrorPersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import repositories.ListPrimaryRepository;
import repositories.ListSecondaryRepository;
import repositories.UserRepository;

import java.util.Objects;
import java.util.UUID;

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
            if (!Objects.isNull(userRepository.findByEmail(user.getEmail())))
                throw new ErrorPersistenceException("The User already exist");
            BeanUtils.copyProperties(userDto, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage() + " /Error, not possible persistence data");
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> postTaskController(@RequestBody @Valid ListPrimaryDto taskDto) {
        ListPrimary task = new ListPrimary();
        try {
            if (userRepository.findById(taskDto.user_id()).isEmpty())
                throw new ErrorPersistenceException("The User not exist");
            BeanUtils.copyProperties(taskDto, task);
            return ResponseEntity.status(HttpStatus.CREATED).body(listPrimaryRepository.save(task));
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage() + "Error, not possible persistence data");
        }
    }


    @PostMapping("/subtasks")
    public ResponseEntity<Object> postSubTaskController(@RequestBody @Valid ListSecondaryDto subTaskDto) {
        ListSecondary subTask = new ListSecondary();
        try {
            if (listPrimaryRepository.findById(subTaskDto.task_id()).isEmpty())
                throw new ErrorPersistenceException("The task not exist");
            BeanUtils.copyProperties(subTaskDto, subTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(listSecondaryRepository.save(subTask));
        } catch (RuntimeException error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage() + "Error, not possible persistence data");
        }
    }
}
