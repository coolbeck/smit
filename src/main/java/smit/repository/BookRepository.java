package smit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smit.entity.Book;

import java.util.List;

/**
 * Created by Admin on 18/06/2017.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(String status);
}

