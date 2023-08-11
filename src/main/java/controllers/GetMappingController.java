package controllers;

import Repositories.ListPrimaryRepository;
import models.ListPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import models.ListPrimary;
import models.ListSecondary;
import models.UserModel;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GetMappingController {

    @Autowired
    ListPrimaryRepository listPrimaryRepository;


    @GetMapping("/users")
    public void getUserController(@RequestBody UserModel user) {
        ResponseEntity.ok("okok");
    }

    @GetMapping("/tasks")
    public ResponseEntity
            <Page<ListPrimary>> getPage(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int offset) {
        PageRequest pageRequest = PageRequest.of(page, offset);
        return ResponseEntity.status(HttpStatus.OK).body(listPrimaryRepository.findAll(pageRequest));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Optional<ListPrimary>> getTaskById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(listPrimaryRepository.findById(id));
    }

    @GetMapping("/tasks/subtasks")
    public void getSubTaskController(@RequestBody ListSecondary subTask) {

    }

}
