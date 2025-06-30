package com.example.LibraryMan.controller;

import com.example.LibraryMan.dto.*;
import com.example.LibraryMan.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(201).body(bookService.createBook(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> search(@RequestParam String query) {
        return ResponseEntity.ok(bookService.searchBooks(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBookStatus(@PathVariable Long id,
                                                         @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.updateBookStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.softDeleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publishedYear
    ) {
        return ResponseEntity.ok(bookService.getBooks(page, size, author, publishedYear));
    }
}
