package com.library.library.controller;

import com.library.library.dao.UserDAO;
import com.library.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class UserController {
    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody User user){
        return new ResponseEntity(userDAO.createUser(user),HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity getUserById(@PathVariable long id){
        return new ResponseEntity(userDAO.getUserById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id){
        userDAO.deleteUser(id);
        return new ResponseEntity("User was deleted",HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable long id,@RequestBody User user){
        return  new ResponseEntity(userDAO.updateUser(id, user),HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity getAllUsers(){
        return  new ResponseEntity(userDAO.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/takeBook/{book_id}/{user_id}")
    public ResponseEntity takeBook(@PathVariable long book_id,@PathVariable long user_id){
        return new ResponseEntity(userDAO.takeBook(book_id, user_id),HttpStatus.OK);
    }

    @PostMapping("/returnBook/{book_id}/{user_id}")
    public ResponseEntity returnBook(@PathVariable long book_id,@PathVariable long user_id){
        return new ResponseEntity(userDAO.returnBook(book_id, user_id),HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity error(){
        return new ResponseEntity("This user in not exist",HttpStatus.BAD_REQUEST);
    }
}
