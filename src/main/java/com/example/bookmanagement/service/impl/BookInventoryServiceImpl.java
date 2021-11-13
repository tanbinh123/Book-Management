package com.example.bookmanagement.service.impl;

import com.example.bookmanagement.annotation.Benchmark;
import com.example.bookmanagement.constant.Constant;
import com.example.bookmanagement.domain.Book;
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
            throw new BookBusinessException(Constant.ERROR.BOOK_DOES_NOT_EXIST);
        }
        int amount = inventoryRepository.getLastInventoryByBookId(bookId).getAmount();
        if(amount>=quantity){
            return addInventory(bookId, quantity, Constant.INVENTORY_TYPE.BUY).toString();
        }else {
            applicationEventPublisher.publishEvent(new ImportBookEvent(this, bookId, quantity));
            return "This book is out of stock. We will import more books then contact you as soon as possible";
        }
    }

    @EventListener
    private void onBookOutOfStock(ImportBookEvent importBookEvent){
        addInventory(importBookEvent.getBookId(), importBookEvent.getQuantity(), Constant.INVENTORY_TYPE.IMPORT);
    }

    @Override
    @Benchmark
    public BookInventory getInventoryByid(int id) {
        return inventoryRepository.findById(id);
    }

    @Scheduled(fixedDelay = 10000)
    public void importBook(){
        List<String> listBookId = inventoryRepository.getListBookIdByInventoryNumber(1);
        for (String bookId : listBookId){
            addInventory(bookId, 5, Constant.INVENTORY_TYPE.IMPORT);
        }
    }

    @Benchmark
    private String addInventory(String bookId, int quantity, String op) {
        BookInventory inventory = inventoryRepository.getLastInventoryByBookId(bookId);
        int amount = inventory.getAmount();
        if (op.equals(Constant.INVENTORY_TYPE.BUY)) {
                inventory.setAmount(amount - quantity);
        } else if(op.equals(Constant.INVENTORY_TYPE.IMPORT)){
            inventory.setAmount(amount+quantity);
        }
        inventory.setUpdateDate(LocalDateTime.now());
        return inventoryRepository.createInventory(inventory);
    }
}
