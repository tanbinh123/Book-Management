package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.BookInventory;
import com.example.bookmanagement.service.BookInventoryService;
import com.example.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-inventories")
public class BookInventoryController {
    @Autowired
    BookInventoryService bookInventoryService;

    @GetMapping
    public List<BookInventory> getAllBookInventories(){
        return bookInventoryService.getAll();
    }

    @GetMapping("{id}")
    public BookInventory getBookInventorieById(@PathVariable("id") int id){
        return bookInventoryService.getInventoryByid(id);
    }

    @PostMapping("/{bookId}/buy")
    public String buyBook(@RequestParam(required = false, name = "quantity", defaultValue = "1") int quantity,
                        @PathVariable("bookId") String bookId){
        return bookInventoryService.buyBook(bookId,quantity);
    }
}
