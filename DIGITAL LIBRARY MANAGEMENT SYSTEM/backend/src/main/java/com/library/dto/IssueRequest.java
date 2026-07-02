package com.library.dto;

import jakarta.validation.constraints.NotNull;

public record IssueRequest(@NotNull Long bookId) {
}
