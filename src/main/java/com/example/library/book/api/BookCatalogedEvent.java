package com.example.library.book.api;

public record BookCatalogedEvent(
        String isbn,
        String author,
        String title,
        Long pages
) {
}
