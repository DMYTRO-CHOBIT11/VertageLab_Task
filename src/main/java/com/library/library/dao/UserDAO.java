package com.library.library.dao;

import com.library.library.model.User;

import java.util.List;

public interface UserDAO {
    User createUser(User user);
    String deleteUser(long id);
    User updateUser(long id,User user);
    User getUserById(long id);
    List<User> getAllUsers();
    String takeBook(long book_id,long user_id);
    String returnBook(long book_id,long user_id);

}
