﻿# Techmaster
1. Sinh ra 1000 đầu sách và tạo BookInventory với amount ngẫu nhiên từ 0 đến 100 quyển sách.
  Tạo 100 đầu sách: [POST] http://localhost:8080/books/create
  Tạo bookInventory: [POST] http://localhost:8080/book-inventories/create
2. Liệt kê các đầu sách sắp xếp theo tiêu chí title từ A-Z
  [GET] http://localhost:8080/books
3. Tìm đầu sách có title chứa keyword nào đó
  [GET] http://localhost:8080/books/books-contain-{keyword}
  ví dụ tìm đầu sách có title chứa "cat" (không phân biệt chữ hoa/thường): http://localhost:8080/books/books-contain-cat
4. Liệt kê các đầu sách hiện đang hết amount = 0;
  [GET] http://localhost:8080/books/out-of-stock
5. Giả lập lệnh mua sách buy_book, mặc định số lượng mua là 1 quyển, nhưng có thể mua nhiều hơn 1 quyển.
  [POST] http://localhost:8080/book-inventories/buy?quantity=57&bookId=827
6. Nếu số lượng mua lớn số lượng sách có trong kho hãy tạo một event để đặt hàng thêm sách nhập về kho: 
  Sử dụng Spring event; Publisher, Listener bên trong class ```package com.example.bookmanagement.service.impl;```
7. Hãy tạo một schedule routine cứ 1 phút chạy 1 lần để tìm ra các đầu sách chỉ còn 1 quyển trong kho để tiến hàng nhập hàng bổ xung, số lượng là 5 quyển mỗi lần bổ sung.
  Sử dụng ```@Scheduled(fixedDelay = 60000)``` với method ```importBook``` trong ```package com.example.bookmanagement.service.impl;```
8. Dùng Spring Boot AOP để viết một advice ứng với annotation @Benchmark để đo đạc thời gian thực thi các lệnh REST
  
