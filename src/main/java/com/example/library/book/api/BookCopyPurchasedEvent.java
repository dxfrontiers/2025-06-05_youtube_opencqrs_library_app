package com.example.library.book.api;

import java.util.UUID;

public record BookCopyPurchasedEvent(
        String isbn,
        UUID id,
        Double price
) {
}
