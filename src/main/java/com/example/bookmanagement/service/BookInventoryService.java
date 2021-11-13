package com.example.bookmanagement.service;

import com.example.bookmanagement.domain.BookInventory;

import java.util.List;

public interface BookInventoryService {
    List<BookInventory> getAll();
    List<String> getListBookIdByInventoryNumber(int inventoryNum);
    String buyBook(String bookId, int quantity);
    BookInventory getInventoryByid(int id);
}
