package com.example.bookshifter.controllers;

import com.example.bookshifter.dto.BookDTO;
import com.example.bookshifter.dto.BookRequestDTO;

import com.example.bookshifter.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @CrossOrigin("*")
    @PostMapping("/")
public String getBook(@RequestParam(name = "isbn") Long isbn, @RequestParam(name = "fatecId") Long fatecId, @RequestBody BookRequestDTO dto){
        service.saveBookByIsbn(isbn, fatecId, dto);
        return "Livro adicionado!";
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(service.findAllBooks());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBook(@RequestParam String query){
        return ResponseEntity.ok(service.searchProducts(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }


    @CrossOrigin(origins="*")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        service.deleteBook(id);
        return "Livro deletado com sucesso!";
    }
}
