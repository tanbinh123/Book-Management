package com.example.bookmanagement.controller;

import com.example.bookmanagement.domain.Book;
import com.example.bookmanagement.dto.ResponseDTO;
import com.example.bookmanagement.service.BookService;
import com.example.bookmanagement.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createFakeData(){
        bookService.createFakeData();
        return ResponseEntity.ok().body(ResponseUtils.responseOK(null));
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAllBooks(){
         return ResponseEntity.ok().body(ResponseUtils.responseOK(bookService.getAllBooksSortedByTitle()));
    }

    @GetMapping("/books-contain-{keyword}")
    public ResponseEntity<ResponseDTO> getAllBooks(@PathVariable("keyword") String keyword){
        return ResponseEntity.ok().body(ResponseUtils.responseOK(bookService.getBooksContainKey(keyword)));
    }

    @GetMapping("/out-of-stock")
    public ResponseEntity<ResponseDTO> getListOutOfStock(){
        return ResponseEntity.ok().body(ResponseUtils.responseOK(bookService.getBooksByInventoryNum(0)));
    }
}
