package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.BookDTO;
import com.example.bookshifter.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private BookService service;

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBook(@RequestParam String query){
        return ResponseEntity.ok(service.searchProducts(query));
    }
}

/*/home/search?query=Harry*/
