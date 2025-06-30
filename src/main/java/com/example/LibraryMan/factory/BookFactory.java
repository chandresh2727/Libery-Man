package com.example.LibraryMan.factory;

import com.example.LibraryMan.dto.BookRequest;
import com.example.LibraryMan.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFactory {

    public Book createFromRequest(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublishedYear(request.getPublishedYear());
        book.setAvailabilityStatus(request.getAvailabilityStatus());
        book.setDeleted(false);
        return book;
    }

}
