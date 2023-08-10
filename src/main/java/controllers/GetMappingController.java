package controllers;

import Repositorys.ListPrimaryRepository;
import models.ListPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GetMappingController {
    @Autowired
    ListPrimaryRepository listPrimaryRepository;

    @GetMapping("product")
    ResponseEntity<Page<ListPrimary>> getAllPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int offset) {

        PageRequest pageRequest = PageRequest.of(page, offset);
        Page<ListPrimary> productPage = listPrimaryRepository.findAll(pageRequest);
        return ResponseEntity.ok(productPage);

    }
    @GetMapping("product/{id}")
    ResponseEntity<Optional<ListPrimary>> getListID(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(listPrimaryRepository.findById(id));
    }

}
