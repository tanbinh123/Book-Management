package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.annotation.Benchmark;
import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.BookInventory;
import com.example.bookmanagement.repository.BookInventoryRepository;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookInventoryService;
import com.example.bookmanagement.service.BookService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookInventoryService bookInventoryService;

    @Override
    public void createFakeData() {
        bookRepository.createFakeData();
    }

    @Override
    @Benchmark
    public List<Book> getAllBooksSortedByTitle(){
        return sortBooksByTitleThenAuthor(bookRepository.findAll());
    }

    @Override
    @Benchmark
    public List<Book> getBooksContainKey(String keyword) {
        return sortBooksByTitleThenAuthor(bookRepository.getBooksContainKey(keyword));
    }

    @Override
    @Benchmark
    public List<Book> getBooksByInventoryNum(int inventoryNum) {
        List<Book> books = new ArrayList<>();
        List<String> listBookId = bookInventoryService.getListBookIdByInventoryNumber(inventoryNum);
        for (String bookId: listBookId) {
            books.add(bookRepository.getBookById(bookId));
        }
        return books;
    }

    private List<Book> sortBooksByTitleThenAuthor(List<Book> books){
        Comparator<Book> comparatorByTitle = Comparator.comparing(Book::getTitle)
                .thenComparing(Book::getAuthor);
        return books.stream().sorted(comparatorByTitle).collect(Collectors.toList());
    }


}
