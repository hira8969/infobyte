package com.library.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FineCalculator {
    private final long finePerDay;

    public FineCalculator(@Value("${app.library.fine-per-day}") long finePerDay) {
        this.finePerDay = finePerDay;
    }

    public long overdueDays(LocalDate dueDate, LocalDate returnDate) {
        if (!returnDate.isAfter(dueDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    public BigDecimal amount(long overdueDays) {
        return BigDecimal.valueOf(overdueDays * finePerDay);
    }
}
