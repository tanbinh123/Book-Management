package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.annotation.Benchmark;
import com.example.bookmanagement.domain.BookInventory;
import com.example.bookmanagement.event.ImportBookEvent;
import com.example.bookmanagement.exception.BookBusinessException;
import com.example.bookmanagement.repository.BookInventoryRepository;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.bookmanagement.constant.Constant.*;

@Service
public class BookInventoryServiceImpl implements BookInventoryService {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    BookInventoryRepository inventoryRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    @Benchmark
    public List<BookInventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<String> getListBookIdByInventoryNumber(int inventoryNum) {
        return inventoryRepository.getListBookIdByInventoryNumber(inventoryNum);
    }

    @Override
    @Benchmark
    public String buyBook(String bookId, int quantity) {
        if(Objects.isNull(bookRepository.getBookById(bookId))){
            throw new BookBusinessException(BOOK_DOES_NOT_EXIST);
        }
        int amount = inventoryRepository.getLastInventoryByBookId(bookId).getAmount();
        if(amount>=quantity){
            return addInventory(bookId, quantity, BUY).toString();
        }else {
            applicationEventPublisher.publishEvent(new ImportBookEvent(this, bookId, quantity));
            return "This book is out of stock. We will import more books then contact you as soon as possible";
        }
    }

    @EventListener
    public void onBookOutOfStock(ImportBookEvent importBookEvent){
        addInventory(importBookEvent.getBookId(), importBookEvent.getQuantity(), IMPORT);
    }

    @Override
    @Benchmark
    public BookInventory getInventoryByid(int id) {
        return inventoryRepository.findById(id);
    }

    @Benchmark
    @Override
    public void createFakeData() {
        inventoryRepository.createFakeData();
    }

    @Scheduled(fixedDelay = 60000)
    public void importBook(){
        List<String> listBookId = inventoryRepository.getListBookIdByInventoryNumber(1);
        for (String bookId : listBookId){
            addInventory(bookId, 5, IMPORT);
        }
    }

    @Benchmark
    private String addInventory(String bookId, int quantity, String op) {
        BookInventory inventory = inventoryRepository.getLastInventoryByBookId(bookId);
        int amount = inventory.getAmount();
        if (op.equals(BUY)) {
                inventory.setAmount(amount - quantity);
        } else if(op.equals(IMPORT)){
            inventory.setAmount(amount+quantity);
        }
        inventory.setUpdateDate(LocalDateTime.now());
        return inventoryRepository.createInventory(inventory);
    }
}
