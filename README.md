# Mô tả nghiệp vụ hệ thống User – Task – Project

## 1. Tổng quan
Hệ thống dùng để quản lý công việc theo Project.
Mỗi công việc (Task) có thể được gán cho một User thực hiện.

## 2. Thành phần chính

### User
Là người sử dụng hệ thống và chịu trách nhiệm thực hiện Task.

### Project
Là nhóm các Task liên quan đến cùng một mục tiêu.

### Task
Là đơn vị công việc cụ thể:
- Thuộc một Project
- Có thể được gán cho một User
- Có trạng thái thực hiện (TODO, IN_PROGRESS, DONE, CANCELLED)

## 3. Luồng nghiệp vụ chính
- Tạo Project
- Tạo Task trong Project
- Gán Task cho User
- Cập nhật trạng thái Task
- Xóa Task khi không còn cần thiết

## 4. Công nghệ sử dụng

- Java 17
- Spring Boot
- Spring Security + JWT
- Hibernate / JPA
- SQL Server
- Swagger (OpenAPI)

---

## 5. Hướng dẫn cài đặt và chạy project

Để chạy hệ thống, cần cài đặt sẵn JDK 17, Maven và SQL Server.

Trước tiên, cấu hình kết nối database trong file: src/main/resources/application-dev.properties

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=baitapthuctap;encrypt=true;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=123456

Sau khi cấu hình xong, tiến hành build project bằng lệnh: mvn clean package

Sau khi build thành công, file .jar sẽ được tạo tại thư mục: target/BaiTapThucTap-0.0.1-SNAPSHOT.jar

Chạy project bằng lệnh: java -jar target/BaiTapThucTap-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

Khi hệ thống chạy thành công, server sẽ hoạt động tại: http://localhost:8080
6. Xác thực và phân quyền
Hệ thống sử dụng JWT (JSON Web Token) để xác thực người dùng.
Người dùng cần đăng nhập để nhận token thông qua API: POST /api/auth/login

Với dữ liệu:
{
"username": "admin123",
"password": "123456"
}

Sau khi đăng nhập thành công, hệ thống trả về token. Token này cần được gửi kèm trong header của các request:

Authorization: Bearer <token>

Hệ thống có 2 vai trò chính:

USER: xem task của mình và cập nhật trạng thái
MANAGER: tạo project, quản lý task và phân công công việc
7. Danh sách API chính
   Auth
   POST /api/auth/register
   POST /api/auth/login
   Task
   GET /api/tasks
   POST /api/tasks/addTask
   PUT /api/tasks/updateTask/{id}
   DELETE /api/tasks/delete/{id}
   PUT /api/tasks/{taskId}/assign/{userId}
   GET /api/tasks/user/{userId}
   GET /api/tasks/project/{projectId}
   Project
   POST /api/projects/create
8. Test hệ thống

Hệ thống tích hợp Swagger để test API trực tiếp trên trình duyệt.

Sau khi chạy project, truy cập:

http://localhost:8080/swagger-ui/index.html

Tại đây, có thể thực hiện các bước:

Gọi API đăng nhập để lấy token

Nhấn nút "Authorize" và nhập token theo dạng:

Bearer <token>
Thực hiện test các API khác như Task và Project

