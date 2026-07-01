package com.onlinereservation.util;

import java.time.Year;
import java.util.Optional;

public final class PNRGenerator {
    private PNRGenerator() {
    }

    public static String generateNextPnr(Optional<String> latestPnr) {
        int currentYear = Year.now().getValue();
        int nextNumber = latestPnr
                .map(pnr -> pnr.substring(pnr.length() - 4))
                .map(Integer::parseInt)
                .orElse(0) + 1;

        return String.format("PNR%d%04d", currentYear, nextNumber);
    }
}
