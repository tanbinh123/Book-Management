package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.Book;

import java.util.List;

public interface BookService {
    void createFakeData();
    List<Book> getAllBooksSortedByTitle();
    List<Book> getBooksContainKey(String keyword);
    List<Book> getBooksByInventoryNum(int inventoryNum);
}
