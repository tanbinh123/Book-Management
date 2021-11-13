package com.example.bookmanagement.repository;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.BookInventory;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookInventoryRepository {

    @Autowired
    BookRepository bookRepository;

    List<BookInventory> bookInventories;
    private Map<String, List<BookInventory>> mapBookInventories;
    Faker faker = new Faker(new Locale("EN"));

    public BookInventoryRepository() {
        bookInventories = new ArrayList<>();
        mapBookInventories = new HashMap<>();
    }

    public void createFakeData() {
        for (int i = 1; i <= 1000; i++) {
            BookInventory inventory = BookInventory.builder()
                    .id(bookInventories.size())
                    .bookId(String.valueOf(i))
                    .amount(faker.random().nextInt(0, 100))
                    .updateDate(LocalDateTime.now())
                    .build();
            bookInventories.add(inventory);
            List<BookInventory> bookInventories = new ArrayList<>();
            bookInventories.add(inventory);
            mapBookInventories.put(String.valueOf(i), bookInventories);
        }
    }

    public List<BookInventory> findAll() {
        return bookInventories;
    }
    public BookInventory findById(int id) {
        for (BookInventory bookInventory: bookInventories) {
            if(bookInventory.getId()==id)
                return bookInventory;
        }
        return null;
    }
    public BookInventory getLastInventoryByBookId(String bookId){
        List<BookInventory> bookInventories = mapBookInventories.get(bookId);
        return bookInventories.get(bookInventories.size()-1);
    }


    public List<String> getListBookIdByInventoryNumber(int inventoryNum) {
        List<String> listBookId = new ArrayList<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book:allBooks) {
            String bookId = book.getId();
            List<BookInventory> bookInventories = mapBookInventories.get(bookId);
            if(bookInventories.get(bookInventories.size()-1).getAmount()==inventoryNum){
                listBookId.add(bookId);
            }
        }
        return listBookId;
    }

    public String createInventory(BookInventory bookInventory){
        BookInventory inventory = BookInventory.builder()
                .id(bookInventories.get(bookInventories.size() - 1).getId() + 1)
                .bookId(bookInventory.getBookId())
                .amount(bookInventory.getAmount())
                .updateDate(bookInventory.getUpdateDate())
                .build();
        bookInventories.add(inventory);
        mapBookInventories.get(inventory.getBookId()).add(inventory);
        String result = "New inventory: " + inventory.toString();
        System.out.println(result);
        return result;
    }
}
