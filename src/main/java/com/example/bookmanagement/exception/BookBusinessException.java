package com.example.bookmanagement.exception;

import lombok.Data;

@Data
public class BookBusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public BookBusinessException(String code) {
        this.code = code;
    }
}
