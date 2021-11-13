package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.BookInventory;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class BookRepository {

    private List<Book> books;
    Faker faker = new Faker(new Locale("EN"));

    public BookRepository() {
        books = new ArrayList<>();
    }

    public void createFakeData() {
        for (int i = 1; i <= 1000; i++) {
            com.github.javafaker.Book book = faker.book();
            books.add(Book.builder()
                    .id(String.valueOf(i))
                    .title(book.title())
                    .author(book.author())
                    .build());
        }
    }

    public List<Book> findAll() {
        return books;
    }

    public List<Book> getBooksContainKey(String keyword) {
        List<Book> booksContainKey = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                booksContainKey.add(book);
            }
        }
        return booksContainKey;
    }

    public Book getBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }


}
