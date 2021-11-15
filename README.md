# Techmaster
1. Sinh ra 1000 đầu sách và tạo BookInventory với amount ngẫu nhiên từ 0 đến 100 quyển sách.
  Tạo 100 đầu sách: [POST] http://localhost:8080/books/create
  ![image](https://user-images.githubusercontent.com/62837633/141715537-0573f6fe-9b42-4e90-9a13-50d135bd0f89.png)
  Tạo bookInventory: [POST] http://localhost:8080/book-inventories/create
  ![image](https://user-images.githubusercontent.com/62837633/141715555-7a8d40bc-3672-4d38-8507-569c62a7abaf.png)
2. Liệt kê các đầu sách sắp xếp theo tiêu chí title từ A-Z
  [GET] http://localhost:8080/books
  ![image](https://user-images.githubusercontent.com/62837633/141715603-424e132c-de6d-4079-ada5-8c6e17b3f5c1.png)
  ![image](https://user-images.githubusercontent.com/62837633/141715654-e85c5c39-14e3-4705-a34d-c270488968c2.png)
3. Tìm đầu sách có title chứa keyword nào đó
  [GET] http://localhost:8080/books/books-contain-{keyword}
  ví dụ tìm đầu sách có title chứa "cat" (không phân biệt chữ hoa/thường): http://localhost:8080/books/books-contain-cat
  ![image](https://user-images.githubusercontent.com/62837633/141715706-934862f8-4d0b-4c4e-94e5-eb2a02196f84.png)
4. Liệt kê các đầu sách hiện đang hết amount = 0;
  [GET] http://localhost:8080/books/out-of-stock
  ![image](https://user-images.githubusercontent.com/62837633/141716422-7973dd22-dd4b-4331-80a3-b44e2eeea2c1.png)
  Hiển thị inventories (xem amount có = 0 không):
  ![image](https://user-images.githubusercontent.com/62837633/141716456-4c2af802-9762-4285-8a51-a0b3fd463138.png)
  ![image](https://user-images.githubusercontent.com/62837633/141716481-c7d53bd0-2ddd-4b90-94cb-d60fcbd2a09e.png)
5. Giả lập lệnh mua sách buy_book, mặc định số lượng mua là 1 quyển, nhưng có thể mua nhiều hơn 1 quyển.
  [POST] http://localhost:8080/book-inventories/buy?quantity=57&bookId=827
  Không truyền vào số lượng mua (mặc định là 1) => data trả về: thông tin tồn kho sau khi khách mua hàng
  ![image](https://user-images.githubusercontent.com/62837633/141716543-e781b47f-1349-42c6-93e3-3677df5ea49c.png)
  Truyền vào số lượng mua:
  ![image](https://user-images.githubusercontent.com/62837633/141716644-aa462d06-748f-4d69-985a-012b5a1f1d88.png)
6. Nếu số lượng mua lớn số lượng sách có trong kho hãy tạo một event để đặt hàng thêm sách nhập về kho: 
  Sử dụng Spring event; Publisher, Listener bên trong class ```package com.example.bookmanagement.service.impl;```
  Khi số lượng sách mua lớn hơn lượng trong kho, báo cho khách hàng biết, đồng thời nhập sách
  ![image](https://user-images.githubusercontent.com/62837633/141716672-950a7f37-9a07-4cdc-b193-68d3dfc49a6d.png)
  (kết quả xem ở console)
  ![image](https://user-images.githubusercontent.com/62837633/141716802-117de16a-429b-40ae-a916-920288b26023.png)
7. Hãy tạo một schedule routine cứ 1 phút chạy 1 lần để tìm ra các đầu sách chỉ còn 1 quyển trong kho để tiến hàng nhập hàng bổ xung, số lượng là 5 quyển mỗi lần bổ sung.
  Sử dụng ```@Scheduled(fixedDelay = 60000)``` với method ```importBook``` trong ```package com.example.bookmanagement.service.impl;```
  Xem kết quả ở console
  ![image](https://user-images.githubusercontent.com/62837633/141716843-98a6674c-ccdf-4157-aeb3-a230ed651b21.png)
8. Dùng Spring Boot AOP để viết một advice ứng với annotation @Benchmark để đo đạc thời gian thực thi các lệnh REST (log ở console)
  ![image](https://user-images.githubusercontent.com/62837633/141716900-f1a2ebc8-b121-4529-a338-5cfe4c1355ea.png)
  ![image](https://user-images.githubusercontent.com/62837633/141716943-813bbc2a-336e-48b8-a87d-f10134a54e9c.png)
