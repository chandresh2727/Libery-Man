package com.example.LibraryMan.repository;

import com.example.LibraryMan.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    boolean existsByIsbnAndDeletedFalse(String isbn);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseAndDeletedFalse(String title, String author);

    Page<Book> findByDeletedFalseAndAuthorContainingIgnoreCaseAndPublishedYear(String author, int year, Pageable pageable);

    Page<Book> findByDeletedFalseAndAuthorContainingIgnoreCase(String author, Pageable pageable);

    Page<Book> findByDeletedFalse(Pageable pageable);

}
