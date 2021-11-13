package com.example.bookmanagement.event;

import org.springframework.context.ApplicationEvent;

public class ImportBookEvent extends ApplicationEvent {
    private String bookId;
    private int quantity;

    public ImportBookEvent(Object source, String bookId, int quantity) {
        super(source);
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
