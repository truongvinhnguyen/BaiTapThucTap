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
