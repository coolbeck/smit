package smit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smit.entity.Book;
import smit.repository.BookRepository;

import java.util.List;

/**
 * Created by Admin on 19/06/2017.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAllAvailable(){
        return repository.findByStatus("available");
    }

    public List<Book> getAllUnavailable(){
        return repository.findByStatus("unavailable");
    }

    public Book getByID(long id){
        return repository.findOne(id);
    }

    public Book save(Book book){
        return repository.saveAndFlush(book);
    }

    public void delete(long id){
        repository.delete(id);
    }

    public String healthcheck(){
        return "healthy";
    }
}
