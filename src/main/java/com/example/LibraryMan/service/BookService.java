package com.example.LibraryMan.service;

import com.example.LibraryMan.dto.BookRequest;
import com.example.LibraryMan.dto.BookResponse;
import com.example.LibraryMan.entity.Book;
import com.example.LibraryMan.factory.BookFactory;
import com.example.LibraryMan.notification.NotificationStrategy;
import com.example.LibraryMan.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookFactory bookFactory;

    @Qualifier("log")
    private NotificationStrategy notificationStrategy;

//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    public BookResponse createBook(BookRequest request) {
        if (bookRepository.existsByIsbnAndDeletedFalse(request.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }

        Book book = bookFactory.createFromRequest(request);
        return convertToResponse(bookRepository.save(book));
    }

    public List<BookResponse> searchBooks(String query) {
        List<Book> books = bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseAndDeletedFalse(query, query);
        return books.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public BookResponse updateBookStatus(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Book.AvailabilityStatus previousStatus = book.getAvailabilityStatus();
        BeanUtils.copyProperties(request, book);
        Book updated = bookRepository.save(book);

        if (previousStatus == Book.AvailabilityStatus.Borrowed
                && updated.getAvailabilityStatus() == Book.AvailabilityStatus.Available) {
            notifyWishlistedUsersAsync(updated);
        }

        return convertToResponse(updated);
    }

    @Async
    public void notifyWishlistedUsersAsync(Book book) {
        List<Long> userIds = List.of(101L, 102L); // Mocked users
        for (Long userId : userIds) {
            notificationStrategy.notify(userId, book.getTitle());
        }
    }

    public void softDeleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setDeleted(true);
        bookRepository.save(book);
    }

    private BookResponse convertToResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setPublishedYear(book.getPublishedYear());
        response.setAvailabilityStatus(book.getAvailabilityStatus());
        return response;
    }

    public Page<BookResponse> getBooks(int page, int size, String author, Integer year) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books;

        if (author != null && year != null) {
            books = bookRepository.findByDeletedFalseAndAuthorContainingIgnoreCaseAndPublishedYear(author, year, pageable);
        } else if (author != null) {
            books = bookRepository.findByDeletedFalseAndAuthorContainingIgnoreCase(author, pageable);
        } else {
            books = bookRepository.findByDeletedFalse(pageable);
        }

        return books.map(this::convertToResponse);
    }

}
