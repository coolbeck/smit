package smit.service;

import smit.entity.Book;

import java.util.List;

/**
 * Created by Admin on 19/06/2017.
 */
public interface BookService {
    List<Book> getAllAvailable();
    List<Book> getAllUnavailable();
    Book getByID(long id);
    Book save(Book book);
    void delete(long id);
    String healthcheck();
}
