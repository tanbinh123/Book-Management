package com.example.bookmanagement.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookInventory {
    private int id; //số tự động tăng
    private String bookId;
    private int amount; //số sách còn lại trong kho
    private LocalDateTime updateDate; //ngày, giờ cập nhật lại
}
