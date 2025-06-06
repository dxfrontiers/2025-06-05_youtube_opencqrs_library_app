package com.example.library.book.api;

import com.opencqrs.framework.command.Command;

public record CatalogBookCommand(
        String isbn,
        String author,
        String title,
        Long pages
) implements Command {

    @Override
    public String getSubject() {
        return "/books/" + isbn;
    }

    @Override
    public SubjectCondition getSubjectCondition() {
        return SubjectCondition.PRISTINE;
    }
}
