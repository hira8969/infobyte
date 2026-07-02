package com.library.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FineCalculatorTest {

    @Test
    void calculatesFineForOverdueDays() {
        FineCalculator calculator = new FineCalculator(5);

        long overdueDays = calculator.overdueDays(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));

        assertThat(overdueDays).isEqualTo(4);
        assertThat(calculator.amount(overdueDays)).isEqualByComparingTo("20");
    }
}
