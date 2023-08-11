package controllers;


import dto.ListPrimaryDto;
import dto.ListSecondaryDto;
import dto.UserModelDto;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repositories.ListPrimaryRepository;
import repositories.ListSecondaryRepository;
import repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostMappingController {

    @Autowired
    ListPrimaryRepository listPrimaryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ListSecondaryRepository listSecondaryRepository;


    @PostMapping("/users")
    public void postUserController(@RequestBody @Valid UserModelDto userDto) {
        UserModel user = new UserModel();
        try {
            BeanUtils.copyProperties(userDto, user);
            ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
        } catch (RuntimeException error) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, not possible persistence data");
        }
    }

    @PostMapping("/tasks")
    public void postTaskController(@RequestBody @Valid ListPrimaryDto taskDto) {
        ListPrimary task = new ListPrimary();
        try {
            BeanUtils.copyProperties(taskDto, task);
            ResponseEntity.status(HttpStatus.CREATED).body(listPrimaryRepository.save(task));
        } catch (RuntimeException error) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, not possible persistence data");
        }
    }

    @PostMapping("/subtasks")
    public void postSubTaskController(@RequestBody @Valid ListSecondaryDto subTaskDto) {
        ListSecondary subTask = new ListSecondary();
        try {
            BeanUtils.copyProperties(subTaskDto, subTask);
            ResponseEntity.status(HttpStatus.CREATED).body(listSecondaryRepository.save(subTask));
        } catch (RuntimeException error) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, not possible persistence data");
        }
    }
}
