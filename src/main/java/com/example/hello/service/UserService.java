package com.example.hello.service;

import java.util.List;
import java.util.Optional;

import com.example.hello.entity.User;

public interface UserService {

    User createUser(User user); // Đăng ký user mới

    User updateUser(Long id, User user); // Cập nhật thông tin

    boolean deleteUser(Long id); // Xóa người dùng theo id

    Optional<User> getUserById(Long id); // Lấy thông tin 1 user

    List<User> getAllUsers(); // Lấy danh sách tất cả user

    Optional<User> findByUsername(String username); // Tìm user theo username

    Optional<User> login(String username, String password); // Xác thực đăng nhập

    boolean existsByUsername(String username); // Kiểm tra username đã tồn tại

    boolean existsByEmail(String email); // Kiểm tra email đã tồn tại
}
