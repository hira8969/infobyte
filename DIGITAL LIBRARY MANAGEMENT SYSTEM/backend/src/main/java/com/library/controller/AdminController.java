package com.library.controller;

import com.library.dto.BookDTO;
import com.library.dto.DashboardStats;
import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.AdminService;
import com.library.service.BookService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final BookService bookService;

    public AdminController(AdminService adminService, BookService bookService) {
        this.adminService = adminService;
        this.bookService = bookService;
    }

    @GetMapping("/dashboard")
    public DashboardStats dashboard() {
        return adminService.stats();
    }

    @GetMapping("/users")
    public List<User> users() {
        return adminService.users();
    }

    @PatchMapping("/users/{id}/active")
    public User setActive(@PathVariable Long id, @RequestParam boolean active) {
        return adminService.setActive(id, active);
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody BookDTO dto) {
        return bookService.create(dto);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO dto) {
        return bookService.update(id, dto);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
