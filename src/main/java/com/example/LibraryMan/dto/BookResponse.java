package com.example.LibraryMan.dto;

import com.example.LibraryMan.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private Book.AvailabilityStatus availabilityStatus;

}
