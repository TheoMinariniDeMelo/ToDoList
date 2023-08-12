package controllers;


import models.ListPrimary;

import models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import models.ListSecondary;
import org.springframework.web.bind.annotation.GetMapping;
import repositories.ListPrimaryRepository;
import repositories.ListSecondaryRepository;
import repositories.UserRepository;
/*import repositories.ListPrimaryRepository;*/

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@RestController
public class GetMappingController {

    @Autowired
    ListPrimaryRepository listPrimaryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ListSecondaryRepository listSecondaryRepository;

    @GetMapping("/users")
    public ResponseEntity getUserController(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int offset) {

        PageRequest pages = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll(pages));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUserIdController(@PathVariable UUID id) {
        try {
            var user = Optional.of(userRepository.findById(id));
           return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity
            <Page<ListPrimary>> getPage(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int offset) {
        PageRequest pageRequest = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(listPrimaryRepository.findAll(pageRequest));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable(value = "id") UUID id) {
        try {
            var user = Optional.of(listPrimaryRepository.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }

    @GetMapping("/tasks/subtasks")
    public ResponseEntity getSubTaskController(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int offset) {
        PageRequest pageRequest = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(listSecondaryRepository.findAll(pageRequest));
    }

    @GetMapping("/tasks/subtasks/{id}")
    public ResponseEntity getSubTaskIdController(@PathVariable("Id") UUID id) {
        try {
            var user = Optional.of(listSecondaryRepository.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body(user.orElseThrow());
        } catch (NoSuchElementException error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: not possible found user on id provided");
        }
    }
}
