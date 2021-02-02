package com.library.library.controller;

import com.library.library.dao.BookDAO;
import com.library.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class BookController {
    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @PostMapping("/createBook")
    public ResponseEntity createUser(@RequestBody Book book){
        return new ResponseEntity(bookDAO.createBook(book), HttpStatus.OK);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity getUserById(@PathVariable long id){
        return new ResponseEntity(bookDAO.getBookById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id){
        bookDAO.deleteBook(id);
        return new ResponseEntity("Book was deleted",HttpStatus.OK);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity updateUser(@PathVariable long id,@RequestBody Book book){
        return  new ResponseEntity(bookDAO.updateBook(id, book),HttpStatus.OK);
    }

    @GetMapping("/allBooks")
    public ResponseEntity getAllUsers(){
        return  new ResponseEntity(bookDAO.getAllBooks(),HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity error(){
        return new ResponseEntity("This book in not exist",HttpStatus.BAD_REQUEST);
    }
}
