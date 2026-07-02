package com.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookDTO(
        Long id,
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank @Size(max = 40) String isbn,
        @NotBlank String category,
        @NotNull @Min(0) Integer quantity,
        @NotNull @Min(0) Integer availableQuantity,
        String publisher,
        Integer publicationYear,
        @Size(max = 2000) String description
) {
}
