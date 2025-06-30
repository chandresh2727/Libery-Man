package com.example.LibraryMan.dto;

import com.example.LibraryMan.entity.Book;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class BookRequest {
    @NotBlank
    private String title;
    @NotBlank private String author;
    @NotBlank private String isbn;
    @Min(1500) private int publishedYear;
    @NotNull private Book.AvailabilityStatus availabilityStatus;

}
