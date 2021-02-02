package com.library.library.dao;

import com.library.library.model.Book;


public interface BookDAO {
    Book createBook(Book book);
    String deleteBook(long id);
    Book updateBook(long id,Book book);
    Book getBookById(long id);
    Iterable<Book>getAllBooks();

}
