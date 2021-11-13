package com.example.bookmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    private String id;
    private String title;
    private String author;

    //List<BookInventory> bookInventories = new ArrayList<>();
}
