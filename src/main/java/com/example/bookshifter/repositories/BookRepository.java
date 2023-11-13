package com.example.bookshifter.repositories;

import com.example.bookshifter.entities.Book;
import com.example.bookshifter.projections.BookMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,  Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM tb_book book WHERE book.title LIKE CONCAT('%', :query, '%')" +
            "Or book.description LIKE CONCAT('%',:query, '%')" +
            "Or book.publisher LIKE CONCAT('%', :query, '%')"
        )
    List<Book> searchAllByQuery(String query);


    @Query(nativeQuery = true, value = "SELECT title, publisher, medium_cover_url as mediumCoverUrl from tb_book book ")

    List<BookMinProjection> findAllByMin();

    @Query(nativeQuery = true, value = "SELECT * FROM tb_book book where book.fatec =:id")
    List<Book> findByFatecId(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_book book WHERE book.owner_id =:id")
    List<Book> findByOwner(Long id);
}
