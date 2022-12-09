package com.example.cursoapiudemy.controller;

import com.example.cursoapiudemy.service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServices bookServices;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookServices.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bookServices.findById(id));
    }

}
