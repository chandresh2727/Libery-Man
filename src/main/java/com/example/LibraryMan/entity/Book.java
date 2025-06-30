package com.example.LibraryMan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;

    @Min(1500)
    private int publishedYear;

    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    private boolean deleted = false;

    public enum AvailabilityStatus {
        Available, Borrowed
    }

}
