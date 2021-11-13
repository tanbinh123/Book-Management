package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.domain.BookInventory;
import com.example.bookmanagement.dto.ResponseDTO;
import com.example.bookmanagement.service.BookInventoryService;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-inventories")
public class BookInventoryController {
    @Autowired
    BookInventoryService bookInventoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createFakeData(){
        bookInventoryService.createFakeData();
        return ResponseEntity.ok().body(ResponseUtils.responseOK(null));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllBookInventories(){
        return ResponseEntity.ok().body(ResponseUtils.responseOK(bookInventoryService.getAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getBookInventoryeById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(ResponseUtils.responseOK(bookInventoryService.getInventoryByid(id)));
    }

    @PostMapping("/buy")
    public ResponseEntity<ResponseDTO> buyBook(@RequestParam(required = false, name = "quantity", defaultValue = "1") int quantity,
                                               @RequestParam(required = true, name = "bookId") String bookId){
        return ResponseEntity.ok().body(ResponseUtils.responseOK(bookInventoryService.buyBook(bookId,quantity)));
    }
}
