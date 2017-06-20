package smit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import smit.entity.Book;
import smit.repository.BookRepository;
import smit.service.BookService;

import java.util.List;

/**
 * Created by Admin on 18/06/2017.
 */


@RestController
@RequestMapping("/service")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAvailableBooks", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAvailableBooks(){
        return bookService.getAllAvailable();
    }

    @RequestMapping(value = "/getUnavailableBooks", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getUnavailableBooks(){
        return bookService.getAllUnavailable();
    }

    @RequestMapping(value = "/saveNewBook/{name}", method = RequestMethod.POST)
    @ResponseBody
    public Book saveNewBook(@PathVariable("name") String book_name){
        Book new_book = new Book();
        new_book.setName(book_name);
        new_book.setStatus("available");
        return bookService.save(new_book);
    }

    @RequestMapping(value = "/updateStatus/{id}/{status}", method = RequestMethod.POST)
    @ResponseBody
    public Book updateStatusBook(@PathVariable("id") long book_id, @PathVariable("status") String book_status){
        Book book = bookService.getByID(book_id);
        book.setStatus(book_status);
        return bookService.save(book);
    }

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    @ResponseBody
    public String healthCheck(){
        return bookService.healthcheck();
    }

}
