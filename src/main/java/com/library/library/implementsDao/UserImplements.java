package com.library.library.implementsDao;

import com.library.library.dao.UserDAO;
import com.library.library.model.Book;
import com.library.library.model.User;
import com.library.library.repository.BookRepository;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserImplements implements UserDAO {
    private UserRepository repository;
    private BookRepository bookRepository;

    @Autowired
    public UserImplements(UserRepository repository,BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository=bookRepository;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public String deleteUser(long id) {
        Optional<User> user=repository.findById(id);
        if (user.isPresent()){
            repository.delete(user.get());
        } else throw new NoSuchElementException();
        return "User with ID: "+id+" was deleted";
    }

    @Override
    public User updateUser(long id, User user) {
        User updateUser=repository.findById(id).get();
        updateUser.setName(user.getName());
        return repository.save(updateUser);
    }

    @Override
    public User getUserById(long id) {
        return repository.findById(id).get();
    }


    @Override
    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public String takeBook(long book_id,long user_id) {
        Book book=bookRepository.findById(book_id).get();
        User user=repository.findById(user_id).get();
        List<User>users= (List<User>) repository.findAll();

        boolean isPresent=users.stream().anyMatch(u-> u.getBooks().stream().anyMatch(b->b.getId()==book.getId()));
        if (isPresent){
            return "The Book is not available!!!";
        }

        user.getBooks().add(book);
        repository.save(user);
        return "Book: "+book.getName()+" was taken";
    }

    @Override
    public String returnBook(long book_id, long user_id) {
        Book book=bookRepository.findById(book_id).get();
        User user=repository.findById(user_id).get();
        user.getBooks().remove(book);
        repository.save(user);
        return "Book: "+book.getName()+" was return";
    }
}

