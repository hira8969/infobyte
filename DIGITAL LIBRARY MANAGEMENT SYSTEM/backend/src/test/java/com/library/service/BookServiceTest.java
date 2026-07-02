package com.library.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.library.dto.BookDTO;
import com.library.exception.BadRequestException;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void rejectsDuplicateIsbn() {
        BookDTO dto = new BookDTO(null, "Clean Code", "Robert Martin", "ISBN-1", "Programming",
                5, 5, "Prentice Hall", 2008, "Software craftsmanship");
        when(bookRepository.existsByIsbn("ISBN-1")).thenReturn(true);

        assertThatThrownBy(() -> bookService.create(dto)).isInstanceOf(BadRequestException.class);
    }
}
