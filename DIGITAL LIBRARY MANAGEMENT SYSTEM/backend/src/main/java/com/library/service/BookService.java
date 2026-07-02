package com.library.service;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import com.library.exception.BadRequestException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> list(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> search(String keyword, Pageable pageable) {
        String query = keyword == null ? "" : keyword;
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrIsbnContainingIgnoreCase(
                query, query, query, query, pageable);
    }

    public Book get(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Transactional
    public Book create(BookDTO dto) {
        if (bookRepository.existsByIsbn(dto.isbn())) {
            throw new BadRequestException("ISBN already exists");
        }
        Book book = new Book();
        apply(dto, book);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, BookDTO dto) {
        Book book = get(id);
        if (bookRepository.existsByIsbnAndIdNot(dto.isbn(), id)) {
            throw new BadRequestException("ISBN already exists");
        }
        apply(dto, book);
        return bookRepository.save(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.delete(get(id));
    }

    private void apply(BookDTO dto, Book book) {
        if (dto.availableQuantity() > dto.quantity()) {
            throw new BadRequestException("Available quantity cannot exceed total quantity");
        }
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn());
        book.setCategory(dto.category());
        book.setQuantity(dto.quantity());
        book.setAvailableQuantity(dto.availableQuantity());
        book.setPublisher(dto.publisher());
        book.setPublicationYear(dto.publicationYear());
        book.setDescription(dto.description());
    }
}
