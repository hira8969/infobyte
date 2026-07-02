package com.library.dto;

import java.math.BigDecimal;

public record DashboardStats(
        long totalBooks,
        long totalUsers,
        long issuedBooks,
        long pendingReturns,
        BigDecimal totalPendingFines,
        long reservations
) {
}
